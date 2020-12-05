class Day05(io: Kattio) {
    val seatIds = io.lines().map { seatCode ->
        seatCode
            .replace(Regex("[BR]"), "1")
            .replace(Regex("[FL]"), "0")
            .toInt(2)
    }.toSet()

    fun a() = seatIds.maxOrNull()
    fun b() = seatIds.filter { it+1 !in seatIds }.minOrNull()!! + 1
}
