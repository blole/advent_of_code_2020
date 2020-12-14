import DestructuringContextBuilder.Companion.whenRegex

fun String.replaces(regex: Regex, iterable: Iterable<Any>) = iterable.iterator().run { replace(regex) { next().toString() } }

data class Mask(val andMask: Long, val orMask: Long) {
    constructor(mask: String): this(
        andMask = mask.replace('X','1').toLong(2),
        orMask = mask.replace('X','0').toLong(2),
    )

    fun mask(value: Long) = (value or orMask) and andMask
}

class Day14(io: Kattio) {
    val input = io.lines().toList()

    fun String.possibleValues(): Sequence<Mask> {
        val bitCount = count { it == 'X' }
        val xRange = 0 until (1 shl bitCount)

        return xRange
            .asSequence()
            .map { bits ->
                val replacementBits = bits.toString(2).padStart(bitCount, '0').asIterable()
                Mask(
                    andMask = replace('0', '1').replaces(Regex("X"), replacementBits).toLong(2),
                    orMask = replaces(Regex("X"), replacementBits).toLong(2),
                )
            }
    }

    fun a(): Long {
        val mem = mutableMapOf<Int, Long>()
        lateinit var mask: Mask

        input.forEach { line ->
            whenRegex(line) {
                """mask = ([X01]{36})""" then { s: String -> mask = Mask(s) }
                """mem\[(\d+)\] = (\d+)""" then { index: Int, value: Long ->
                    mem[index] = mask.mask(value)
                }
            }
        }
        return mem.values.sum()
    }

    fun b(): Long {
        val mem = mutableMapOf<Long, Long>()
        lateinit var mask: String

        input.forEach { line ->
            whenRegex(line) {
                """mask = ([X01]{36})""" then { s: String -> mask = s }
                """mem\[(\d+)\] = (\d+)""" then { index: Long, value: Long ->
                    for (possibleMask in mask.possibleValues())
                        mem[possibleMask.mask(index)] = value
                }
            }
        }
        return mem.values.sum()
    }
}
