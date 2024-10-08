package ru.mephi

fun main() {
    val mockClient = Client()

    val response = mockClient.perform(200, "OK")
        .andDo { response ->
            println(response)
        }
        .andExpect{
            body {
                isNotNull()
            }
            status {
                isOk()
            }
        }.response
    println(response)
}