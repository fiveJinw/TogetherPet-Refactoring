import org.gradle.api.Plugin
import org.gradle.api.Project
import androidx.room.gradle.RoomExtension
import com.multi.module.convention.libs
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class KakaoConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            dependencies{
                "implementation"(libs.findLibrary("kakao.sdk").get())
                "implementation"(libs.findLibrary("kakao.map.open").get())
            }
        }
    }
}