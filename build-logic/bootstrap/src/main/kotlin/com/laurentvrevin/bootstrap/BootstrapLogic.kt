package com.laurentvrevin.bootstrap

import java.io.File

class BootstrapLogic(
    private val rootDir: File,
    private val dryRun: Boolean = false,
) {
    fun validatePackageName(packageName: String) {
        val segments = packageName.split(".")
        if (segments.size < 2) {
            throw IllegalArgumentException("Package name must have at least two segments.")
        }
        val regex = Regex("^[a-z][a-z0-9_]*(\\.[a-z][a-z0-9_]*)*$")
        if (!regex.matches(packageName)) {
            throw IllegalArgumentException("Invalid package name format.")
        }
        if (packageName == "com.example") {
            throw IllegalArgumentException("Package name cannot be com.example.")
        }
    }

    fun applyTransformations(transformations: List<Pair<String, String>>) {
        val excludedPaths = listOf(".git", ".gradle", ".idea", ".kotlin", "build", "out")
        val excludedExtensions = listOf("png", "jpg", "ico", "jar", "apk", "aab", "dex", "class")

        rootDir.walkTopDown()
            .onEnter { it.name !in excludedPaths }
            .filter { it.isFile && it.extension !in excludedExtensions }
            .filter { it.name != "starter.properties" && it.name != "gradlew" && it.name != "gradlew.bat" }
            .forEach { file ->
                var content = file.readText()
                var modified = false
                transformations.forEach { (old, new) ->
                    if (content.contains(old)) {
                        content = content.replace(old, new)
                        modified = true
                    }
                }
                if (modified) {
                    if (dryRun) {
                        println("   [DRY RUN] Would update: ${file.relativeTo(rootDir)}")
                    } else {
                        file.writeText(content)
                        println("   Updated: ${file.relativeTo(rootDir)}")
                    }
                }
            }
    }

    fun movePackageDirectories(
        oldPackage: String,
        newPackage: String,
    ) {
        val oldPath = oldPackage.replace(".", File.separator)
        val newPath = newPackage.replace(".", File.separator)

        // Find all directories that match the old package path
        rootDir.walkTopDown()
            .filter { it.isDirectory && it.path.endsWith(oldPath) }
            .filter { dir ->
                val path = dir.path.replace(File.separator, "/")
                path.contains("/src/main/java") || path.contains("/src/main/kotlin") ||
                    path.contains("/src/test/java") || path.contains("/src/test/kotlin") ||
                    path.contains("/src/androidTest/java") || path.contains("/src/androidTest/kotlin")
            }
            .toList() // Convert to list to avoid ConcurrentModificationException or walking issues while moving
            .forEach { dir ->
                val rootSrcDir = findRootSrcDir(dir)
                if (rootSrcDir != null) {
                    val targetDir = File(rootSrcDir, newPath)
                    if (dryRun) {
                        println("   [DRY RUN] Would move: ${dir.relativeTo(rootDir)} -> ${targetDir.relativeTo(rootDir)}")
                    } else {
                        if (targetDir.exists()) {
                            dir.listFiles()?.forEach { file ->
                                val destFile = File(targetDir, file.name)
                                if (destFile.exists()) {
                                    println("   ⚠️  Conflict: ${destFile.relativeTo(rootDir)} already exists. Skipping.")
                                } else {
                                    file.renameTo(destFile)
                                }
                            }
                            if (dir.listFiles()?.isEmpty() == true) {
                                dir.delete()
                            }
                        } else {
                            targetDir.mkdirs()
                            dir.listFiles()?.forEach { file ->
                                file.renameTo(File(targetDir, file.name))
                            }
                            dir.delete()
                        }
                        cleanEmptyParents(dir.parentFile, rootSrcDir)
                        println("   Moved: ${dir.relativeTo(rootDir)} -> ${targetDir.relativeTo(rootDir)}")
                    }
                }
            }
    }

    private fun findRootSrcDir(dir: File): File? {
        val markers = listOf("src/main/java", "src/main/kotlin", "src/test/java", "src/test/kotlin", "src/androidTest/java", "src/androidTest/kotlin")
        var current: File? = dir
        while (current != null && current != rootDir) {
            val path = current.path.replace(File.separator, "/")
            if (markers.any { path.endsWith(it) }) return current
            current = current.parentFile
        }
        return null
    }

    private fun cleanEmptyParents(
        dir: File?,
        root: File,
    ) {
        var current = dir
        while (current != null && current != root && current.isDirectory && (current.listFiles()?.isEmpty() ?: true)) {
            val toDelete = current
            current = current.parentFile
            toDelete.delete()
        }
    }
}
