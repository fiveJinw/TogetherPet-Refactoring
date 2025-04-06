import org.gradle.api.Plugin
import org.gradle.api.Project
import androidx.room.gradle.RoomExtension
import com.android.build.gradle.LibraryExtension
import com.multi.module.convention.libs
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            project.extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner = "com.example.HiltTestRunner"
                }
                packaging {
                    resources.excludes.add("META-INF/gradle/incremental.annotation.processors")
                }

                dependencies {
                    "androidTestImplementation"(libs.findLibrary("androidx.test.runner").get())
                    "androidTestImplementation"(libs.findLibrary("androidx.test.rules").get())
                    "androidTestImplementation"(libs.findLibrary("androidx.junit").get())
                    "androidTestImplementation"(libs.findLibrary("androidx.espresso.core").get())
                    "androidTestImplementation"(libs.findLibrary("androidx.espresso.intents").get())
                    "androidTestImplementation"(libs.findLibrary("androidx.fragment.testing").get())
                    "androidTestImplementation"(
                        libs.findLibrary("androidx.navigation.testing").get()
                    )
                    "androidTestImplementation"(libs.findLibrary("hilt.android.testing").get())
                    "androidTestAnnotationProcessor"(
                        libs.findLibrary("hilt.android.compiler").get()
                    )
                }
            }
        }
    }
}