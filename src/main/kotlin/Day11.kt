class Day11(io: Kattio) {
    private val initialRoom = io.lines().toList().also { it.map(String::length).distinct().single() }

    fun List<String>.pad(): List<String> {
        val wall = "*".repeat(first().length + 2)
        return listOf(wall) + map { "*$it*" } + wall
    }

    operator fun List<String>.get(pos: Pos) = this[pos.y][pos.x]

    fun step(room: List<String>, occupiedLimit: Int, neighbors: (List<String>, Pos) -> List<Char>) =
        room.withIndex().drop(1).dropLast(1).map { (y, row) ->
            row.withIndex().drop(1).dropLast(1).map { (x, char) ->
                val occupiedNeighbors = neighbors(room, Pos(x, y)).count { it == '#' }
                when {
                    char == 'L' && occupiedNeighbors == 0 -> '#'
                    char == '#' && occupiedNeighbors >= occupiedLimit -> 'L'
                    else -> char
                }
            }.joinToString("")
        }.pad()

    fun steady(occupiedLimit: Int, neighbors: (List<String>, Pos) -> List<Char>) =
        generateSequence(initialRoom.pad()) { step(it, occupiedLimit, neighbors) }
            .zipWithNext()
            .takeWhile { (a, b) -> a != b }
            .last().second
            .sumBy { row ->
                row.count { it == '#' }
            }

    fun a(): Int = steady(occupiedLimit = 4) { room, seatPos ->
        Dir.values().map { dir -> room[seatPos+dir] }
    }

    fun b(): Int = steady(occupiedLimit = 5) { room, seatPos ->
        Dir.values().map { dir ->
            generateSequence(seatPos + dir) { it + dir }
                .map { room[it] }
                .first { it != '.' }
        }
    }
}
