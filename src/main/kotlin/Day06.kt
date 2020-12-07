class Day06(io: Kattio) {
    val answerGroups = io.lines()
        .joinToString(" ")
        .split("  ")
        .map { group -> group.split(" ").map { it.toSet() } }

    fun a() = answerGroups.sumBy { it.reduce(Set<Char>::plus).size }

    fun b() = answerGroups.sumBy { it.reduce(Set<Char>::intersect).size }
}
