import java.net.URL

plugins {
    `java-library`
    id("io.izzel.taboolib") version "1.51"
    id("org.jetbrains.kotlin.jvm") version "1.6.10"
    id("org.jetbrains.dokka") version "1.6.10"
}
val api: String? by project
val order: String? by project

task("versionModify") {
    project.version = project.version.toString() + (order?.let { "-$it" } ?: "")
}

task("versionAddAPI") {
    if (api == null) return@task
    val origin = project.version.toString()
    project.version = "$origin-api"
}


task("releaseName") {
    println(project.name + "-" + project.version)
}

task("version") {
    println(project.version.toString())
}

tasks.dokkaJavadoc.configure {
    outputDirectory.set(File("C:\\Users\\Administrator\\Desktop\\Doc\\attsystem"))
    dokkaSourceSets {
        configureEach {
            externalDocumentationLink {
                url.set(URL("https://doc.skillw.com/pouvoir/"))
            }
        }
    }

}
taboolib {
    if (project.version.toString().contains("-api")) {
        options("skip-kotlin-relocate")
    }
    description {
        contributors {
            name("Glom_")
        }
        dependencies {
            name("Pouvoir")
            name("GermPlugin").optional(true)
            name("DragonCore").optional(true)
            name("SkillAPI").optional(true)
            name("Magic").optional(true)
            name("MythicMobs").optional(true)


        }
    }

    install("common")
    install("common-5")
    install("module-chat")
    install("module-nms-util")
    install("module-kether")
    install("module-nms")
    install("platform-bukkit")
    install("module-lang")
    install("module-configuration")

    install("module-metrics")
    classifier = null
    version = "6.0.10-31"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjvm-default=all")
    }
}

repositories {
    maven { url = uri("https://repo.janmm14.de/repository/public/") }
    maven { url = uri("https://jitpack.io") }
    maven { url = uri("https://mvn.lumine.io/repository/maven-public/") }

    mavenCentral()
}

dependencies {
    compileOnly("ink.ptms:nms-all:1.0.0")
    compileOnly("ink.ptms.core:v11901:11901-minimize:mapped")
    dokkaHtmlPlugin("org.jetbrains.dokka:kotlin-as-java-plugin:1.6.10")
    compileOnly("ink.ptms:nms-all:1.0.0")
    compileOnly("de.janmm14:aac-api:5.0.0")
    compileOnly("com.github.LoneDev6:API-ItemsAdder:3.0.0")
    compileOnly("io.lumine:Mythic-Dist:5.0.3")
    compileOnly(fileTree("libs"))
    compileOnly(kotlin("stdlib"))
    taboo("ink.ptms:um:1.0.0-beta-18")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}