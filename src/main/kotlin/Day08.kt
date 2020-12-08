import DestructuringContextBuilder.Companion.whenRegex
import Opcode.*

fun <E> Iterable<E>.updated(index: Int, elem: E) = mapIndexed { i, existing ->  if (i == index) elem else existing }

enum class Opcode { NOP, JMP, ACC }
data class Op(val code: Opcode, val arg: Int)

data class Computer(
    val ram: List<Op>,
    var pc: Int = 0,
    var acc: Int = 0,
) {
    fun step() {
        val (code, arg) = ram[pc]
        when (code) {
            NOP -> {}
            JMP -> pc += arg - 1
            ACC -> acc += arg
        }
        pc++
    }
}

class Day08(io: Kattio) {
    private val instructions = io.lines()
        .map {
            whenRegex<Op>(it) {
                """nop ([+-]\d+)""" then { arg: Int -> Op(NOP, arg) }
                """jmp ([+-]\d+)""" then { arg: Int -> Op(JMP, arg) }
                """acc ([+-]\d+)""" then { arg: Int -> Op(ACC, arg) }
            }
        }
        .toList()

    fun a() = Computer(instructions).run {
        val seen = mutableSetOf<Int>()
        while (pc !in seen) {
            seen += pc
            step()
        }
        acc
    }

    fun b() = instructions
        .withIndex()
        .filter { (_, op) -> op.code in listOf(NOP, JMP) }
        .mapNotNull { (i, op) ->
            val newOp = when (op.code) {
                NOP -> Op(JMP, op.arg)
                JMP -> Op(NOP, op.arg)
                else -> throw IllegalStateException()
            }
            val tweakedInstructions = instructions.updated(i, newOp)

            Computer(tweakedInstructions).run {
                val seen = mutableSetOf<Int>()
                while (pc in ram.indices && pc !in seen) {
                    seen += pc
                    step()
                }

                if (pc == ram.size)
                    acc
                else
                    null
            }
        }
        .single()
}
