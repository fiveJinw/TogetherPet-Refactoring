import com.multi.module.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class TestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            dependencies{
                "testImplementation"(libs.findLibrary("junit").get())
                "testImplementation"(libs.findLibrary("androidx.arch.core").get())
                "testImplementation"(libs.findLibrary("kotlinx.coroutines.test").get())
                "implementation"(libs.findLibrary("io.mockk").get())
            }
        }
    }
}