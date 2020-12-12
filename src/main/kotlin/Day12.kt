import DestructuringContextBuilder.Companion.whenRegex

data class ShipA(var pos: Pos = Pos(0,0), var dir: Dir = Dir.E) {
    fun sail(leg: String) {
        whenRegex<Unit>(leg) {
            """N(\d+)""" then { i: Int -> pos += Dir.N * i }
            """E(\d+)""" then { i: Int -> pos += Dir.E * i }
            """S(\d+)""" then { i: Int -> pos += Dir.S * i }
            """W(\d+)""" then { i: Int -> pos += Dir.W * i }
            """L(\d+)""" then { i: Int -> dir = dir.turnDegrees(i) }
            """R(\d+)""" then { i: Int -> dir = dir.turnDegrees(-i) }
            """F(\d+)""" then { i: Int -> pos += dir * i }
        }
    }
}

data class ShipB(var pos: Pos = Pos(0,0), var waypoint: Pos = Pos(10,-1)) {
    fun sail(leg: String) {
        whenRegex<Unit>(leg) {
            """N(\d+)""" then { i: Int -> waypoint += Dir.N * i }
            """E(\d+)""" then { i: Int -> waypoint += Dir.E * i }
            """S(\d+)""" then { i: Int -> waypoint += Dir.S * i }
            """W(\d+)""" then { i: Int -> waypoint += Dir.W * i }
            """L(\d+)""" then { i: Int -> waypoint = waypoint.turnDegrees(i) }
            """R(\d+)""" then { i: Int -> waypoint = waypoint.turnDegrees(-i) }
            """F(\d+)""" then { i: Int -> pos += waypoint * i }
        }
    }
}

class Day12(io: Kattio) {
    private val legs = io.lines()

    fun a() = ShipA().apply { legs.forEach { leg -> sail(leg) } }.pos.manhattan
    fun b() = ShipB().apply { legs.forEach { leg -> sail(leg) } }.pos.manhattan
}
