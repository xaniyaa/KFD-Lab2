package ru.mephi

import data.ResponseActions

class Client {
    fun perform(code: Int, body: String?) = ResponseActions(code, body)
}
