package data

import ru.mephi.exceptions.StatusResponseMatchersException
import ru.mephi.exceptions.BodyResponseMatchersException

class ResponseActions(
    private val code: Int,
    private val body: String?
) {
    val response: Response = Response(code, body)

    fun andExpect(func: ResponseMatchers.() -> Unit): ResponseActions {
        ResponseMatchers(response).func()
        return this
    }

    fun andDo(callback: (Response) -> Unit): ResponseActions {
        callback(response)
        return this
    }

    inner class ResponseMatchers(
        private val response: Response
    ) {
        fun status(func: StatusResponseMatchers.() -> Unit) {
            StatusResponseMatchers(response).func()
        }
        fun body(func: BodyResponseMatchers.() -> Unit) {
            BodyResponseMatchers(response).func()
        }
    }

    inner class BodyResponseMatchers(
        private val response: Response
    ) {
        fun isNotNull() {
            if (response.body == null) throw BodyResponseMatchersException("Body Response is  null, not null expected")
        }
        fun isNull() {
            if (response.body != null) throw BodyResponseMatchersException("Body Response is not null, null expected")
        }
    }

    inner class StatusResponseMatchers(
        private val response: Response
    ) {

        fun isOk() {
            if (response.code != 200) throw StatusResponseMatchersException("Expected status 200 but got ${response.code}")
        }

        fun isBadRequest() {
            if (response.code != 400) throw StatusResponseMatchersException("Expected status 400 but got ${response.code}")
        }

        fun isInternalServerError() {
            if (response.code != 500) throw StatusResponseMatchersException("Expected status 500 but got ${response.code}")
        }
    }
}
