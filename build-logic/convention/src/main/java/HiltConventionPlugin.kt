import com.multi.module.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            with(pluginManager) {
                apply("org.jetbrains.kotlin.kapt")
            }

            dependencies{
                "kapt"(libs.findLibrary("hilt.compiler").get())
            }

            pluginManager.withPlugin("org.jetbrain.kotlin.jvm"){
                dependencies{
                    "implementation"(libs.findLibrary("hilt.core").get())
                }
            }

            pluginManager.withPlugin("com.android.base"){
                with(pluginManager){
                    apply("dagger.hilt.android.plugin")
                }
                dependencies {
                    "implementation"(libs.findLibrary("hilt.android").get())
                }
            }
        }
    }
}