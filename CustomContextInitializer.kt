class CustomContextInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
    private fun getSsmClient(): SsmClient {
        val credentialsProvider = AwsCredentialsProviderChain.builder()
            .credentialsProviders(
                EnvironmentVariableCredentialsProvider.create(),
                SystemPropertyCredentialsProvider.create(),
                ProfileCredentialsProvider.create(),
                ContainerCredentialsProvider.builder().build())
            .build()
        return SsmClient.builder()
            .credentialsProvider(credentialsProvider)
            .region(Region.AP_NORTHEAST_1)
            .build()
    }
    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        val environment = applicationContext.environment
        val propertySources = environment.propertySources
        
        // これが今回最大のお仕事をする部分です。
        // applicationContext にある PropertySource 群の「先頭」に SSM ParameterStore から取得してくる PropertySource を追加します。 
        propertySources.addFirst(
            CustomPropertySource(
                "ssmpsPropertySource",
                CustomSource(getSsmClient())
            )
        )
    }
}
