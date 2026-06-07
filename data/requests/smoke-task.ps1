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

$BaseUrl = Resolve-BaseUrl -ExplicitBaseUrl $BaseUrl -EnvironmentName $Environment

$runSuffix = Get-Random -Minimum 100000 -Maximum 999999
$taskTitle = "Relationship smoke task $runSuffix"
$taskDescription = "Created from PowerShell smoke script $runSuffix"
$taskVersionNumber = $runSuffix
$taskNotes = @(
    "task note one from PowerShell smoke script $runSuffix",
    "task note two from PowerShell smoke script $runSuffix"
)

$taskPayload = @{
    title = $taskTitle
    description = $taskDescription
    versions = @(
        @{ version = $taskVersionNumber }
    )
    notes = @(
        @{ note = $taskNotes[0] },
        @{ note = $taskNotes[1] }
    )
}

Write-Host "Using base URL: $BaseUrl"
Write-Host "Creating task with run suffix $runSuffix and version $taskVersionNumber..."
$task = Invoke-JsonPost -Url "$BaseUrl/task/save" -Body $taskPayload

Write-Host "Fetching task $($task.id)..."
$fetchedTask = Invoke-RestMethod -Method Get -Uri "$BaseUrl/task/fetch/$($task.id)"

Write-Host ''
Write-Host 'Resolved IDs:'
Write-Host "  task.id = $($task.id)"
Write-Host "  runSuffix = $runSuffix"
Write-Host "  taskVersionNumber = $taskVersionNumber"
Write-Host ''
Write-Host 'Fetched task response:'
$fetchedTask | ConvertTo-Json -Depth 10
