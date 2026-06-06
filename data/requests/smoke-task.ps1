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

$taskPayload = @{
    title = 'Relationship smoke task'
    description = 'Created from PowerShell smoke script'
    versions = @(
        @{ version = 1 }
    )
    notes = @(
        @{ note = 'task note one from PowerShell smoke script' },
        @{ note = 'task note two from PowerShell smoke script' }
    )
}

Write-Host "Creating task..."
$task = Invoke-JsonPost -Url "$BaseUrl/task/save" -Body $taskPayload

Write-Host "Fetching task $($task.id)..."
$fetchedTask = Invoke-RestMethod -Method Get -Uri "$BaseUrl/task/fetch/$($task.id)"

Write-Host ''
Write-Host 'Created IDs:'
Write-Host "  task.id = $($task.id)"
Write-Host ''
Write-Host 'Fetched task response:'
$fetchedTask | ConvertTo-Json -Depth 10
