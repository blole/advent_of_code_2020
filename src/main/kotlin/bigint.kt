import java.math.BigInteger

val Int.B get() = toBigInteger()
val Long.B get() = toBigInteger()

fun BigInteger.lcm(o: BigInteger) = this / gcd(o) * o
