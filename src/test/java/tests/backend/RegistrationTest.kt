package tests.backend

import org.junit.Test
import utils.network.ApplicationRestClient

/**
 * Example of backend test for registration
 * */
class RegistrationTest {

    @Test
    fun performLogin() {
        val hashMap = HashMap<String, String>()

        println(ApplicationRestClient()
                .setData(hashMap)
                .userRegistration(hashMap))
    }
}