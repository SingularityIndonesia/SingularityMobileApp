import kotlin.test.Test
import java.io.BufferedReader
import java.io.InputStreamReader

class ApplicationTest {

    @Test
    fun callRoot() {
        val process = ProcessBuilder(
            "curl", "-s", HOST_URL
        ).start()
        
        val reader = BufferedReader(InputStreamReader(process.inputStream))
        val result = reader.readText()
        
        println(result)
    }
    
    @Test
    fun callLogin() {
        val curl = arrayOf("curl", "-s", "-X", "POST", "$HOST_URL/auth/login", "-H", "Content-Type: application/json", "-d", "{\"email\": \"test@example.com\"}")
        val process = ProcessBuilder(*curl)
            .start()
        
        val reader = BufferedReader(InputStreamReader(process.inputStream))
        val result = reader.readText()
        
        println(result)
    }
}