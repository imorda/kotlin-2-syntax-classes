class BankAccount(amount: Int) {
    init {
        validate(amount)
    }

    var balance: Int = amount
        private set(value) {
            validate(value)
            logTransaction(field, value)
            field = value
        }

    fun deposit(amount: Int) {
        validate(amount)
        balance += amount
    }

    fun withdraw(amount: Int) {
        validate(amount)
        balance -= amount
    }

    companion object {
        private fun validate(value: Int) {
            require(value > 0) { "Invalid amount given :`(" }
        }
    }
}
