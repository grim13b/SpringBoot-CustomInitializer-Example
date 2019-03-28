@SpringBootApplication
class Application
fun main(args: Array<String>) {
    // SpringApplication.run(*args)
    SpringApplicationBuilder(Application::class.java)
        // ここで起動時に追加で登録する Initializer を渡す
        .initializers(CustomContextInitializer())
        .run(*args)
}
