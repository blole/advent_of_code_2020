class Day06(io: Kattio) {
    val answerGroups = io.lines()
        .joinToString(" ")
        .split("  ")
        .map { group -> group.split(" ").map { it.toSet() } }

    fun a() = answerGroups
        .sumBy { group ->
            group
                .reduce(Set<Char>::plus)
                .size
        }

    fun b() = answerGroups
        .sumBy { group ->
            group
                .reduce(Set<Char>::plus)
                .filter { possibleAnswer ->
                    group.all { possibleAnswer in it }
                }
                .size
        }
}
