fun <T> generateSequenceStartingWith(start: List<T>, nextFunction: (T) -> T) =
    start.dropLast(1).asSequence() + generateSequence(start.last(), nextFunction)

class Day15(io: Kattio) {
    val startingNumbers = io.ints(",").toList()
    val sequence: Sequence<Int> get() {
        val start = startingNumbers.withIndex().toList()
        val seen: MutableMap<Int, Int> = start
            .dropLast(1)
            .map { it.value to it.index }
            .toMutableMap()

        return generateSequenceStartingWith(start) { (index, prev) ->
            val next = if (seen.contains(prev)) index - seen[prev]!! else 0
            seen[prev] = index
            IndexedValue(index + 1, next)
        }
            .map { it.value }
    }

    fun a() = sequence.drop(2019).first()
    fun b() = sequence.drop(29999999).first()
}
