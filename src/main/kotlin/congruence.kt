import java.math.BigInteger

@Suppress("DataClassPrivateConstructor")
data class Congruence private constructor(
    val residue: BigInteger,
    val modulus: BigInteger,
    private val dummy: Dummy
) {
    constructor(residue: BigInteger, modulus: BigInteger) : this(((residue%modulus)+modulus)%modulus, modulus, Dummy)
    constructor(residue: Int, modulus: Int) : this(residue.B, modulus.B)

    fun crt(o: Congruence) = Congruence(
        residue = o.modulus.modPow((-1).B, modulus) * o.modulus * residue
                + modulus.modPow((-1).B, o.modulus) * modulus * o.residue,
        modulus = modulus.lcm(o.modulus),
    )

    private companion object {
        private object Dummy
    }
}

fun Sequence<Congruence>.crt() = reduce { acc, x -> acc.crt(x) }
fun Iterable<Congruence>.crt() = reduce { acc, x -> acc.crt(x) }
