class Day13(io: Kattio) {
    val earliestTime = io.int()
    val busses = io.words(delimiters = " ,")
        .withIndex()
        .filter { it.value != "x" }
        .map { (index, value) -> IndexedValue(index, value.toInt()) }

    fun a() = busses
        .map { it.value }
        .minByOrNull { it - earliestTime % it }!!
        .let { it * (it - earliestTime % it) }

    fun b() = busses
        .map { (index, value) -> Congruence(-index, value) }
        .crt()
        .residue
}
