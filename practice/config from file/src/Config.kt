import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class Config(config: String) {
    private val backingConfig: Map<String, String>

    init {
        val parsedMap = mutableMapOf<String, String>()
        requireNotNull(getResource(config)) { "Unable to retrieve config" }
            .bufferedReader().forEachLine {
                val line = it.split('=').map { it.trim() }
                require(line.size == 2) { "Invalid config syntax" }
                parsedMap[line[0]] = line[1]
            }
        backingConfig = parsedMap
    }

    operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): ReadOnlyProperty<Any?, *> {
        require(backingConfig.containsKey(property.name)) { "Invalid key!" }
        val cachedVal = backingConfig.getValue(property.name)
        return ReadOnlyProperty { _, _ -> cachedVal }
    }
}
