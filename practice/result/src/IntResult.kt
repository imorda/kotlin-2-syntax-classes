sealed interface IntResult {
    fun getOrDefault(default: Int): Int
    fun getOrNull(): Int?
    fun getStrict(): Int


    class Ok(val value: Int) : IntResult {
        override fun getOrDefault(default: Int): Int = value
        override fun getOrNull(): Int = value

        override fun getStrict(): Int = value
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Ok

            return value == other.value
        }

        override fun hashCode(): Int {
            return value
        }
    }

    class Error(val reason: String) : IntResult {
        override fun getOrDefault(default: Int): Int = default
        override fun getOrNull(): Int? = null

        override fun getStrict(): Int = throw NoResultProvided(reason)
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Error

            return reason == other.reason
        }

        override fun hashCode(): Int {
            return reason.hashCode()
        }
    }
}

class NoResultProvided(message: String) : NoSuchElementException(message)

fun safeRun(callable: () -> Int): IntResult = try {
    IntResult.Ok(callable())
} catch (e: Exception) {
    IntResult.Error(e.message ?: "No message")
}
