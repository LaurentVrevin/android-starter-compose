package com.laurentvrevin.bootstrap

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.util.Properties
import java.util.Scanner

abstract class BootstrapTask : DefaultTask() {

    @TaskAction
    fun bootstrap() {
        val targetDirProperty = project.findProperty("targetDir")?.toString()
        val rootDir = if (targetDirProperty != null) File(targetDirProperty) else project.rootDir

        val starterPropertiesFile = File(rootDir, "starter.properties")
        val completionFile = File(rootDir, ".bootstrap-complete")

        if (completionFile.exists()) {
            throw GradleException("Project has already been bootstrapped.")
        }
        if (!starterPropertiesFile.exists()) {
            throw GradleException("starter.properties not found in ${rootDir.absolutePath}")
        }

        val scanner = Scanner(System.`in`)

        // Try to get properties from -P, otherwise prompt user
        var projectNameInput = project.findProperty("projectName")?.toString()
        if (projectNameInput == null) {
            print("Enter Gradle Project Name (e.g. Wheris): ")
            projectNameInput = scanner.nextLine()
        }

        var appDisplayNameInput = project.findProperty("appDisplayName")?.toString()
        if (appDisplayNameInput == null) {
            print("Enter Application Display Name (e.g. Wheris): ")
            appDisplayNameInput = scanner.nextLine()
        }

        var packageNameInput = project.findProperty("packageName")?.toString()
        if (packageNameInput == null) {
            print("Enter Android Package Name (e.g. com.laurentvrevin.wheris): ")
            packageNameInput = scanner.nextLine()
        }

        val dryRun = project.findProperty("dryRun")?.toString() == "true"
        var confirm = project.findProperty("confirm")?.toString() == "true"

        if (projectNameInput.isNullOrBlank() || appDisplayNameInput.isNullOrBlank() || packageNameInput.isNullOrBlank()) {
            throw GradleException("Project Name, Display Name and Package Name cannot be empty.")
        }

        val projectName: String = projectNameInput
        val appDisplayName: String = appDisplayNameInput
        val packageName: String = packageNameInput

        val logic = BootstrapLogic(rootDir, dryRun)

        try {
            logic.validatePackageName(packageName)
        } catch (e: IllegalArgumentException) {
            throw GradleException(e.message ?: "Invalid parameters")
        }

        val starterProperties = Properties().apply {
            starterPropertiesFile.inputStream().use { load(it) }
        }

        val oldProjectName = starterProperties.getProperty("starter.projectName") ?: ""
        val oldDisplayName = starterProperties.getProperty("starter.displayName") ?: ""
        val oldPackageName = starterProperties.getProperty("starter.packageName") ?: ""
        val oldThemeName = starterProperties.getProperty("starter.themeName") ?: ""
        val newThemeName = "Theme.$projectName"

        println("\n🚀 Bootstrapping project...")
        println("   Target Directory: ${rootDir.absolutePath}")
        println("   Project Name: $oldProjectName -> $projectName")
        println("   Display Name: $oldDisplayName -> $appDisplayName")
        println("   Package Name: $oldPackageName -> $packageName")
        println("   Theme Name:   $oldThemeName -> $newThemeName")
        if (dryRun) println("   [DRY RUN MODE - No changes will be applied]")

        if (!confirm && !dryRun) {
            print("\n⚠️  Confirm applying these changes? (y/N): ")
            val response = scanner.nextLine()
            confirm = response.trim().lowercase() == "y"
        }

        if (!confirm && !dryRun) {
            println("❌ Bootstrap cancelled by user.")
            return
        }

        val transformations = listOf(
            oldProjectName to projectName,
            oldDisplayName to appDisplayName,
            oldPackageName to packageName,
            oldPackageName.replace(".", "/") to packageName.replace(".", "/"),
            oldThemeName to newThemeName
        )

        logic.applyTransformations(transformations)
        logic.movePackageDirectories(oldPackageName, packageName)

        if (!dryRun) {
            completionFile.writeText("Bootstrapped on ${java.time.LocalDateTime.now()}\n")
            starterPropertiesFile.delete()
            println("✅ Project successfully bootstrapped!")
        }
    }
}
