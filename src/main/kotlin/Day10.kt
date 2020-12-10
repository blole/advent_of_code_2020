data class JoltAdapter(val rating: Int, var ways: Long = 0)

class Day10(io: Kattio) {
    private val adapters = io.ints().toList()
        .sorted()
        .let { listOf(0) + it + (it.last() + 3) }
        .map { JoltAdapter(it) }

    fun a() = adapters
        .zipWithNext { a, b -> b.rating - a.rating }
        .let { diffs -> diffs.count { it == 1 } * diffs.count { it == 3 } }

    fun b(): Long {
        adapters[0].ways = 1
        for ((i, adapter) in adapters.withIndex().drop(1)) {
            adapter.ways = adapters
                .slice(0 until i)
                .takeLastWhile { it.rating + 3 >= adapter.rating }
                .sumOf { it.ways }
        }
        return adapters.last().ways
    }
}
