param(
    [string]$BaseUrl = 'http://localhost:8080'
)

$ErrorActionPreference = 'Stop'

function Invoke-JsonPost {
    param(
        [string]$Url,
        [hashtable]$Body
    )

    return Invoke-RestMethod -Method Post -Uri $Url -ContentType 'application/json' -Body ($Body | ConvertTo-Json -Depth 10)
}

Write-Host "Creating tag..."
$tag = Invoke-JsonPost -Url "$BaseUrl/tag/save" -Body @{
    name = 'person-smoke-tag'
}

Write-Host "Creating current version..."
$currentVersion = Invoke-JsonPost -Url "$BaseUrl/version/save" -Body @{
    version = 1
}

Write-Host "Creating last known version..."
$lastKnownVersion = Invoke-JsonPost -Url "$BaseUrl/version/save" -Body @{
    version = 2
}

$personPayload = @{
    firstName = 'Ada'
    title = 'Engineer'
    birthDate = '1990-05-17'
    tag = $tag.id
    currentVersion = $currentVersion.id
    lastKnownVersion = $lastKnownVersion.id
    notes = @(
        @{ note = 'created from PowerShell smoke script' },
        @{ note = 'second note created from PowerShell smoke script' }
    )
}

Write-Host "Creating person..."
$person = Invoke-JsonPost -Url "$BaseUrl/person/save" -Body $personPayload

Write-Host "Fetching person $($person.id)..."
$fetchedPerson = Invoke-RestMethod -Method Get -Uri "$BaseUrl/person/fetch/$($person.id)"

Write-Host ''
Write-Host 'Created IDs:'
Write-Host "  tag.id = $($tag.id)"
Write-Host "  currentVersion.id = $($currentVersion.id)"
Write-Host "  lastKnownVersion.id = $($lastKnownVersion.id)"
Write-Host "  person.id = $($person.id)"
Write-Host ''
Write-Host 'Fetched person response:'
$fetchedPerson | ConvertTo-Json -Depth 10
