#!/bin/bash

# Usage: ./scripts/rename_project.sh "com.new.package" "NewAppName"

NEW_PACKAGE=$1
NEW_APP_NAME=$2

if [ -z "$NEW_PACKAGE" ] || [ -z "$NEW_APP_NAME" ]; then
    echo "Usage: ./scripts/rename_project.sh \"com.new.package\" \"NewAppName\""
    exit 1
fi

OLD_PACKAGE="com.laurentvrevin.androidstarter"
OLD_APP_NAME="AndroidStarter"

echo "🚀 Renaming project from $OLD_PACKAGE to $NEW_PACKAGE..."

# Replace in files
find . -type f -not -path '*/.*' -not -path '*/build/*' -not -name "*.png" -not -name "*.jpg" -not -name "*.ico" -not -name "*.jar" | xargs sed -i "s/$OLD_PACKAGE/$NEW_PACKAGE/g"
find . -type f -not -path '*/.*' -not -path '*/build/*' -not -name "*.png" -not -name "*.jpg" -not -name "*.ico" -not -name "*.jar" | xargs sed -i "s/$OLD_APP_NAME/$NEW_APP_NAME/g"

echo "✅ Renaming completed in files."
echo "⚠️  IMPORTANT: You must manually rename the directory structure in your IDE to match the new package name."
