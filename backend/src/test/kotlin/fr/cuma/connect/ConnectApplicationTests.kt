package fr.cuma.connect

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest
@Import(TestcontainersConfig::class)
class ConnectApplicationTests {

    @Test
    fun contextLoads() {
    }
}
