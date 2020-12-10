class Day09(io: Kattio, val windowSize: Int) {
    private val data = io.longs().toList()

    fun List<Long>.hasPairSum(pairSum: Long): Boolean {
        val set = toHashSet()
        return set.any { pairSum - it in set }
    }

    fun a() = data
        .windowed(windowSize+1)
        .first { window ->
            !window
                .take(windowSize)
                .hasPairSum(window.last())
        }
        .last()

    fun b(): Long {
        val aResult = a()
        val cumsum = data.runningReduce(Long::plus)
        val cumsumSet = cumsum.toHashSet()
        val cumsumIndex = cumsum.withIndex().map { it.value to it.index }.toMap()

        return cumsum
            .single {
                it - aResult in cumsumSet && cumsumIndex[it-aResult]!! + 1 < cumsumIndex[it]!!
            }
            .let {
                val inRange = data.slice(cumsumIndex[it-aResult]!! + 1 .. cumsumIndex[it]!!)
                inRange.minOrNull()!! + inRange.maxOrNull()!!
            }
    }
}
