class IntMatrix(val rows: Int, val columns: Int) {
    init {
        require(rows >= 0 && columns >= 0) { "Arrays with negative size are unavailable yet :`(" }
    }

    private var backingMatrix: IntArray = IntArray(rows * columns)

    constructor(rows: Int, columns: Int, initializer: (Int, Int) -> Int) : this(rows, columns) {
        backingMatrix = IntArray(rows * columns) { initializer(it / columns, it % columns) }
    }

    operator fun get(i: Int, j: Int): Int {
        validate(i, j)
        return backingMatrix[i * columns + j]
    }

    operator fun set(i: Int, j: Int, value: Int) {
        validate(i, j)
        backingMatrix[i * columns + j] = value
    }

    private fun validate(i: Int, j: Int) {
        require(i >= 0 && j >= 0 && i < rows && j < columns) { "Invalid index value given" }
    }
}
