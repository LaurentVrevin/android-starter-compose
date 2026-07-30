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
    fun `validatePackageName accepts valid package`() {
        val logic = BootstrapLogic(tempFolder.root)
        logic.validatePackageName("com.test.app")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `validatePackageName rejects single segment`() {
        val logic = BootstrapLogic(tempFolder.root)
        logic.validatePackageName("myapp")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `validatePackageName rejects com example`() {
        val logic = BootstrapLogic(tempFolder.root)
        logic.validatePackageName("com.example")
    }

    @Test
    fun `applyTransformations replaces text in files`() {
        val file = tempFolder.newFile("Test.kt")
        file.writeText("package com.laurentvrevin.androidstarter\nclass MainActivity")
        
        val logic = BootstrapLogic(tempFolder.root)
        logic.applyTransformations(listOf("com.laurentvrevin.androidstarter" to "com.test.app"))
        
        assertEquals("package com.test.app\nclass MainActivity", file.readText())
    }

    @Test
    fun `movePackageDirectories moves files and deletes old folders`() {
        val srcDir = tempFolder.newFolder("src", "main", "java")
        val oldPackageDir = File(srcDir, "com/laurentvrevin/androidstarter").apply { mkdirs() }
        val ktFile = File(oldPackageDir, "App.kt").apply { writeText("content") }
        
        val logic = BootstrapLogic(tempFolder.root)
        logic.movePackageDirectories("com.laurentvrevin.androidstarter", "com.test.app")
        
        val newPackageDir = File(srcDir, "com/test/app")
        assertTrue(newPackageDir.exists())
        assertTrue(File(newPackageDir, "App.kt").exists())
        assertFalse(oldPackageDir.exists())
    }

    @Test
    fun `dryRun does not modify files`() {
        val file = tempFolder.newFile("Test.kt")
        val originalContent = "package com.laurentvrevin.androidstarter"
        file.writeText(originalContent)
        
        val logic = BootstrapLogic(tempFolder.root, dryRun = true)
        logic.applyTransformations(listOf("com.laurentvrevin.androidstarter" to "com.test.app"))
        
        assertEquals(originalContent, file.readText())
    }
}
