package com.example.scale1.domain.models

sealed class ResultUseCase<out T> {
    data class Success<out T>(val data: T) : ResultUseCase<T>()
    data class Failure(val exception: Exception) : ResultUseCase<Nothing>()
}