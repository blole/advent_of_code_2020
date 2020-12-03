class Day03(io: Kattio) {
    val trees = io.lines().toList()

    fun a() = trees
        .withIndex()
        .count { (y, row) ->
            row[y*3 % row.length] == '#'
        }

    fun b() = listOf(
        1 to 1,
        3 to 1,
        5 to 1,
        7 to 1,
        1 to 2,
    )
        .map { (xv, yv) ->
            trees
                .withIndex()
                .count { (y, row) ->
                    y % yv == 0 && row[y/yv*xv % row.length] == '#'
                }
        }
        .map(Int::toLong)
        .reduce(Long::times)
}
