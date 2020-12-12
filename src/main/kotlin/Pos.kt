import kotlin.math.abs
import kotlin.math.sign

data class Pos(val x: Int, val y: Int) {
    operator fun plus(o: Pos) = Pos(x+o.x, y+o.y)
    operator fun minus(o: Pos) = Pos(x-o.x, y-o.y)
    operator fun plus(o: Dir) = this+o.pos
    operator fun times(o: Int) = Pos(x*o, y*o)
    operator fun unaryMinus() = Pos(-x, -y)
    val manhattan get() = abs(x) + abs(y)

    val neighbors get() = Dir.values().map { this + it }

    fun turnDegrees(degrees: Int) = when (abs(degrees)%360) {
        0 -> this
        90 -> Pos(y, -x) * degrees.sign
        180 -> -this
        270 -> Pos(-y, x) * degrees.sign
        else -> throw IllegalArgumentException("illegal degrees $degrees")
    }
}

enum class Dir(val pos: Pos) {
    N (Pos( 0,-1)),
    NE(Pos( 1,-1)),
    E (Pos( 1, 0)),
    SE(Pos( 1, 1)),
    S (Pos( 0, 1)),
    SW(Pos(-1, 1)),
    W (Pos(-1, 0)),
    NW(Pos(-1,-1));

    val turnRight  get() = values()[(ordinal+2)%8]
    val turnAround get() = values()[(ordinal+4)%8]
    val turnLeft   get() = values()[(ordinal+6)%8]
    fun turnDegrees(degrees: Int) = values()[((ordinal - degrees/45)%8+8)%8]

    operator fun times(o: Int) = pos * o
}
