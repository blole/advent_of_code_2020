import kotlin.math.abs

data class Pos(val x: Int, val y: Int) {
    operator fun plus(o: Pos) = Pos(x+o.x, y+o.y)
    operator fun minus(o: Pos) = Pos(x-o.x, y-o.y)
    operator fun plus(o: Dir) = this+o.pos
    val manhattan get() = abs(x) + abs(y)

    val neighbors get() = Dir.values().map { this + it }
}

enum class Dir(val pos: Pos) {
    U (Pos( 0,-1)),
    UR(Pos( 1,-1)),
    R (Pos( 1, 0)),
    DR(Pos( 1, 1)),
    D (Pos( 0, 1)),
    DL(Pos(-1, 1)),
    L (Pos(-1, 0)),
    UL(Pos(-1,-1));

    val turnRight  get() = values()[(ordinal+2)%8]
    val turnAround get() = values()[(ordinal+4)%8]
    val turnLeft   get() = values()[(ordinal+6)%8]
}
