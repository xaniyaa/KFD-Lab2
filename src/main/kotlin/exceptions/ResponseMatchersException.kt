package ru.mephi.exceptions

sealed class ResponseMatchersException(
    errorMessage: String
) : RuntimeException(errorMessage) {
}