package infra.auth

import kotlinx.coroutines.runBlocking
import model.form.FormHeader
import model.form.FormStatus
import model.form.FormType
import model.form.LoginWithOtpForm
import model.form.LoginWithOtpForm.LoginWithOtpFormData
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

class AuthDBTest {

    private lateinit var authDB: AuthDB

    @Before
    fun initDB() {
        runBlocking {
            authDB = AuthDB.TestInstance()
        }
    }

    @Test
    fun `test register`() {
        runBlocking {
            val form = LoginWithOtpForm(
                header = FormHeader(
                    id = "1",
                    type = FormType.REQUEST_LOGIN_WITH_OTP,
                    status = FormStatus.WAITING_FOR_VERIFICATION,
                    validUntilEpoch = 0L
                ),
                body = LoginWithOtpFormData("email")
            )

            authDB.register(form)

            val result = authDB.getExistingFormByEmail("email")

            assertTrue { result.isSuccess }
            assertTrue { result.getOrNull() != null }
            assertTrue { result.getOrNull()?.header?.id == "1" }
        }
    }
}
