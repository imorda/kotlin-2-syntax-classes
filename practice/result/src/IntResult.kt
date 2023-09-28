sealed interface IntResult {
    fun getOrDefault(default: Int): Int = when (this) {
        is Ok -> value
        is Error -> default
    }

    fun getOrNull(): Int? = when (this) {
        is Ok -> value
        is Error -> null
    }

    fun getStrict(): Int = when (this) {
        is Ok -> value
        is Error -> throw NoResultProvided(reason)
    }

    data class Ok(val value: Int) : IntResult
    data class Error(val reason: String) : IntResult
}

class NoResultProvided(message: String) : NoSuchElementException(message)

fun safeRun(callable: () -> Int): IntResult = try {
    IntResult.Ok(callable())
} catch (e: Exception) {
    IntResult.Error(e.message ?: "No message")
}
