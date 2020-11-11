package example.serialization

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import java.io.File

@Serializable
data class Model(
        val id: Int,
        val name: String
)

fun main(args: Array<String>) {
    val yamlFile = File("build/example.yaml")
    val list = listOf(
            Model(1,"one"),
            Model(2,"two"),
            Model(3,"three"),
    )

    val yaml = Yaml.default.encodeToString<List<Model>>(list);
    yamlFile.writeText(yaml, Charsets.UTF_8)
    println(yaml)

    val fileContent = yamlFile.readText(Charsets.UTF_8)
    val decoded = Yaml.default.decodeFromString<List<Model>>(fileContent)
    println(decoded)
    assert(list.equals(decoded))
}

