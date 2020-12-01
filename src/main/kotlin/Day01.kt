class Day01(io: Kattio) {
    val expenseReport = io.ints().toSet()

    fun a() = expenseReport
        .first { 2020-it in expenseReport }
        .let { (2020-it) * it }

    fun b() = expenseReport
        .combinations(2)
        .first { (x, y) ->
            2020-x-y in expenseReport
        }
        .let { (x, y) -> (2020-x-y) * x * y }
}
