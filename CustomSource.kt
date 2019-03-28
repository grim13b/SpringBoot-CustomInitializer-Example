class CustomSource(private val ssmClient: SsmClient) {
    fun getProperty(propertyName: String): Any? {
        try {
            return ssmClient.getParameter(
                GetParameterRequest.builder()
                    .name(propertyName)
                    .withDecryption(true)
                    .build())
                .parameter()
                .value()
        } catch (e: ParameterNotFoundException) {
            // 指定されたParameterが無い場合に飛んできます。適宜処理してください。
        } catch (e: Exception) {
            // 適宜処理してください。
        }

        return null
    }
}
