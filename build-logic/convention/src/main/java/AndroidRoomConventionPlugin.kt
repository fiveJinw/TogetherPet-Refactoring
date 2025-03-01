import org.gradle.api.Plugin
import org.gradle.api.Project
import androidx.room.gradle.RoomExtension
import com.multi.module.convention.libs
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidRoomConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("androidx.room")
                apply("org.jetbrains.kotlin.kapt")
            }

            extensions.configure<RoomExtension>(){
                schemaDirectory("$projectDir/schemas")
            }

            dependencies{
                "implementation"(libs.findLibrary("room.ktx").get())
                "implementation"(libs.findLibrary("room.runtime").get())
                "kapt"(libs.findLibrary("room.compiler").get())
            }
        }
    }
}