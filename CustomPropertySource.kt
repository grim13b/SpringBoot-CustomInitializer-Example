class CustomPropertySource(name: String, source: CustomSource)
    : PropertySource<CustomSource>(name, source) {

    companion object {
        const val SPLIT_CHARACTER = "/"
    }

    override fun getProperty(name: String): Any? {
        return if (name.startsWith(SPLIT_CHARACTER)) {
            source.getProperty(name)
        } else null
    }
}

