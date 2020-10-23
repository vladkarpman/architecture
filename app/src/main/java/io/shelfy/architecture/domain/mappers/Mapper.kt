package io.shelfy.architecture.domain.mappers

interface Mapper<From, To> {

    fun map(from: From): To
}
