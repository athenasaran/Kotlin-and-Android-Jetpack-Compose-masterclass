plugins {
    `kotlin-dsl`
}

group = "br.com.config.buildlogic"

dependencies {
    compileOnly(libs.build.logic.android.gradlePlugin)
    compileOnly(libs.build.logic.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationConventionPlugin") {
            id = "config.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibraryConventionPlugin") {
            id = "config.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryComposeConventionPlugin") {
            id = "config.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidApplicationComposeConventionPlugin")  {
            id = "config.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
    }
}