class IntMatrix(val rows: Int, val columns: Int) {
    init {
        require(rows >= 0 && columns >= 0) { "Arrays with negative size are unavailable yet :`(" }
    }

    private var backingMatrix: Array<IntArray> = Array(rows) { IntArray(columns) }

    constructor(rows: Int, columns: Int, initializer: (Int, Int) -> Int) : this(rows, columns) {
        backingMatrix = Array(rows) { row -> IntArray(columns) { column -> initializer(row, column) } }
    }

    operator fun get(i: Int, j: Int): Int {
        validate(i, j)
        return backingMatrix[i][j]
    }

    operator fun set(i: Int, j: Int, value: Int) {
        validate(i, j)
        backingMatrix[i][j] = value
    }

    private fun validate(i: Int, j: Int) {
        require(i >= 0 && j >= 0 && i < rows && j < columns) { "Invalid index value given" }
    }
}
