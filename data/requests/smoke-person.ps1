param(
    [string]$BaseUrl,
    [string]$Environment = 'dev'
)

$ErrorActionPreference = 'Stop'

function Resolve-BaseUrl {
    param(
        [string]$ExplicitBaseUrl,
        [string]$EnvironmentName
    )

    if ($ExplicitBaseUrl)
    {
        return $ExplicitBaseUrl
    }

    $envFilePath = Join-Path $PSScriptRoot 'http-client.env.json'
    if (-not (Test-Path $envFilePath))
    {
        throw "Environment file not found: $envFilePath"
    }

    $envConfig = Get-Content $envFilePath -Raw | ConvertFrom-Json
    $environmentConfig = $envConfig.$EnvironmentName
    if (-not $environmentConfig)
    {
        throw "Environment '$EnvironmentName' not found in $envFilePath"
    }

    if (-not $environmentConfig.baseUrl)
    {
        throw "Environment '$EnvironmentName' does not define baseUrl in $envFilePath"
    }

    return [string]$environmentConfig.baseUrl
}

function Invoke-JsonPost {
    param(
        [string]$Url,
        [hashtable]$Body
    )

    return Invoke-RestMethod -Method Post -Uri $Url -ContentType 'application/json' -Body ($Body | ConvertTo-Json -Depth 10)
}

function Find-ExistingEntity {
    param(
        [string]$FetchUrl,
        [scriptblock]$Matcher,
        [string]$EntityName
    )

    $fetched = Invoke-RestMethod -Method Get -Uri $FetchUrl
    $items = if ($null -ne $fetched.content)
    {
        @($fetched.content)
    }
    else
    {
        @($fetched)
    }
    $match = $items | Where-Object $Matcher | Select-Object -First 1
    if (-not $match)
    {
        throw "$EntityName create failed and no existing matching entity was found at $FetchUrl"
    }

    Write-Host "$EntityName already exists. Reusing id $($match.id)."
    return $match
}

function New-OrGetEntity {
    param(
        [string]$EntityName,
        [string]$CreateUrl,
        [string]$FetchUrl,
        [hashtable]$Body,
        [scriptblock]$Matcher
    )

    try
    {
        Write-Host "Creating $EntityName..."
        return Invoke-JsonPost -Url $CreateUrl -Body $Body
    }
    catch
    {
        Write-Host "$EntityName create failed: $($_.Exception.Message)"
        Write-Host "Falling back to fetch existing $EntityName..."
        return Find-ExistingEntity -FetchUrl $FetchUrl -Matcher $Matcher -EntityName $EntityName
    }
}

$BaseUrl = Resolve-BaseUrl -ExplicitBaseUrl $BaseUrl -EnvironmentName $Environment

Write-Host "Using base URL: $BaseUrl"
$runSuffix = Get-Random -Minimum 100000 -Maximum 999999
$tagName = 'person-smoke-tag'
$currentVersionNumber = 1
$lastKnownVersionNumber = 2
$personFirstName = "Ada-$runSuffix"
$personTitle = "Engineer-$runSuffix"
$personBirthDate = '1990-05-17'
$personNotes = @(
    "created from PowerShell smoke script $runSuffix",
    "second note created from PowerShell smoke script $runSuffix"
)

$tag = New-OrGetEntity -EntityName 'tag' -CreateUrl "$BaseUrl/tag/save" -FetchUrl "$BaseUrl/tag/fetch" -Body @{
    name = $tagName
} -Matcher { $_.name -eq $tagName }

$currentVersion = New-OrGetEntity -EntityName 'current version' -CreateUrl "$BaseUrl/version/save" -FetchUrl "$BaseUrl/version/fetch" -Body @{
    version = $currentVersionNumber
} -Matcher { $_.version -eq $currentVersionNumber }

$lastKnownVersion = New-OrGetEntity -EntityName 'last known version' -CreateUrl "$BaseUrl/version/save" -FetchUrl "$BaseUrl/version/fetch" -Body @{
    version = $lastKnownVersionNumber
} -Matcher { $_.version -eq $lastKnownVersionNumber }

$personPayload = @{
    firstName = $personFirstName
    title = $personTitle
    birthDate = $personBirthDate
    tag = $tag.id
    currentVersion = $currentVersion.id
    lastKnownVersion = $lastKnownVersion.id
    notes = @(
        @{ note = $personNotes[0] },
        @{ note = $personNotes[1] }
    )
}

Write-Host "Creating person with run suffix $runSuffix..."
$person = Invoke-JsonPost -Url "$BaseUrl/person/save" -Body $personPayload

Write-Host "Fetching person $($person.id)..."
$fetchedPerson = Invoke-RestMethod -Method Get -Uri "$BaseUrl/person/fetch/$($person.id)"

Write-Host ''
Write-Host 'Resolved IDs:'
Write-Host "  tag.id = $($tag.id)"
Write-Host "  currentVersion.id = $($currentVersion.id)"
Write-Host "  lastKnownVersion.id = $($lastKnownVersion.id)"
Write-Host "  person.id = $($person.id)"
Write-Host "  runSuffix = $runSuffix"
Write-Host ''
Write-Host 'Fetched person response:'
$fetchedPerson | ConvertTo-Json -Depth 10
