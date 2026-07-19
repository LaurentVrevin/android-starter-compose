param(
    [Parameter(Mandatory=$true)]
    [string]$NewPackageName,
    [Parameter(Mandatory=$true)]
    [string]$NewAppName
)

$OldPackageName = "com.laurentvrevin.androidstarter"
$OldAppName = "AndroidStarter"

Write-Host "🚀 Renaming project from $OldPackageName to $NewPackageName..."

# 1. Replace Package Name and App Name in all relevant files
Get-ChildItem -Recurse -Exclude *.png,*.jpg,*.ico,*.jar,.git,build,.gradle | Where-Object { ! $_.PSIsContainer } | ForEach-Object {
    $content = Get-Content $_.FullName -Raw
    if ($content -match $OldPackageName -or $content -match $OldAppName) {
        Write-Host "Updating $($_.FullName)"
        $content = $content -replace $OldPackageName, $NewPackageName
        $content = $content -replace $OldAppName, $NewAppName
        [System.IO.File]::WriteAllText($_.FullName, $content)
    }
}

Write-Host "✅ Renaming completed in files."
Write-Host "⚠️  IMPORTANT: You must manually rename the directory structure in your IDE to match the new package name."
Write-Host "For example: src/main/java/com/laurentvrevin/androidstarter -> src/main/java/com/example/newapp"
