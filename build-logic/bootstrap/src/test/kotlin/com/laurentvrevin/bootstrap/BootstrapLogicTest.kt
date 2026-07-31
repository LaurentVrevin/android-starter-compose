package com.laurentvrevin.bootstrap

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File

class BootstrapLogicTest {

    @get:Rule
    val tempFolder = TemporaryFolder()

    @Test
    fun `validateProjectName accepts valid PascalCase`() {
        val logic = BootstrapLogic(tempFolder.root)
        logic.validateProjectName("MyNewProject")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `validateProjectName rejects spaces`() {
        val logic = BootstrapLogic(tempFolder.root)
        logic.validateProjectName("My New Project")
    }

    @Test
    fun `validatePackageName accepts valid package`() {
        val logic = BootstrapLogic(tempFolder.root)
        logic.validatePackageName("com.test.app", "com.old.app")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `validatePackageName rejects same as source`() {
        val logic = BootstrapLogic(tempFolder.root)
        logic.validatePackageName("com.old.app", "com.old.app")
    }

    @Test
    fun `applyTransformations correctly handles regex and independent names`() {
        val settingsFile = tempFolder.newFile("settings.gradle.kts")
        settingsFile.writeText("rootProject.name = \"AndroidStarter\"")
        
        val stringsFile = tempFolder.newFile("strings.xml")
        stringsFile.writeText("<string name=\"app_name\">AndroidStarter</string>")
        
        val logic = BootstrapLogic(tempFolder.root)
        val regexTransformations = listOf(
            Regex("rootProject\\.name\\s*=\\s*\"AndroidStarter\"") to "rootProject.name = \"Wheris\"",
            Regex("<string name=\"app_name\">AndroidStarter</string>") to "<string name=\"app_name\">Wheris App</string>"
        )
        val stringTransformations = listOf(
            "AndroidStarter" to "Wheris"
        )
        
        logic.applyTransformations(stringTransformations, regexTransformations)
        
        val settingsContent = settingsFile.readText()
        val stringsContent = stringsFile.readText()
        
        assertTrue("Settings should have Wheris: $settingsContent", settingsContent.contains("rootProject.name = \"Wheris\""))
        assertTrue("Strings should have Wheris App: $stringsContent", stringsContent.contains("<string name=\"app_name\">Wheris App</string>"))
    }

    @Test(expected = IllegalStateException::class)
    fun `checkConflicts detects non-empty destination`() {
        val root = tempFolder.root
        val srcDir = File(root, "app/src/main/java").apply { mkdirs() }
        File(srcDir, "com/old/app").apply { mkdirs() }
        
        val targetDir = File(srcDir, "com/new/app").apply { mkdirs() }
        File(targetDir, "Existing.kt").writeText("data")
        
        val logic = BootstrapLogic(root)
        logic.checkConflicts("com.old.app", "com.new.app")
    }

    @Test
    fun `applyTransformations excludes build folders`() {
        val buildDir = tempFolder.newFolder("build")
        val file = File(buildDir, "should_not_be_touched.kt")
        file.writeText("AndroidStarter")
        
        val logic = BootstrapLogic(tempFolder.root)
        logic.applyTransformations(listOf("AndroidStarter" to "NewName"))
        
        assertEquals("AndroidStarter", file.readText())
    }

    @Test
    fun `movePackageDirectories works in multi-module paths`() {
        val root = tempFolder.root
        val appSrc = File(root, "app/src/main/java").apply { mkdirs() }
        val coreSrc = File(root, "core/src/main/kotlin").apply { mkdirs() }
        
        val oldAppDir = File(appSrc, "com/old/app").apply { mkdirs() }
        File(oldAppDir, "App.kt").writeText("content")
        
        val oldCoreDir = File(coreSrc, "com/old/app").apply { mkdirs() }
        File(oldCoreDir, "Core.kt").writeText("content")
        
        val logic = BootstrapLogic(root)
        logic.movePackageDirectories("com.old.app", "com.new.app")
        
        val newAppDir = File(appSrc, "com/new/app")
        val newCoreDir = File(coreSrc, "com/new/app")
        
        assertTrue("App target should exist", newAppDir.exists())
        assertTrue("Core target should exist", newCoreDir.exists())
        assertTrue(File(newAppDir, "App.kt").exists())
        assertTrue(File(newCoreDir, "Core.kt").exists())
        
        assertFalse("Old App dir should be deleted", oldAppDir.exists())
        assertFalse("Old Core dir should be deleted", oldCoreDir.exists())
        assertFalse("Old middle dir 'old' should be deleted", File(appSrc, "com/old").exists())
    }

    @Test
    fun `dryRun does not modify anything`() {
        val file = tempFolder.newFile("test.kt")
        file.writeText("AndroidStarter")
        
        val logic = BootstrapLogic(tempFolder.root, dryRun = true)
        logic.applyTransformations(listOf("AndroidStarter" to "NewName"))
        
        assertEquals("AndroidStarter", file.readText())
    }
}
