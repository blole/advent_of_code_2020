import java.math.BigDecimal
import java.math.BigInteger

inline fun <reified T: Any> MatchResult.get(i: Int): T =
    when (T::class) {
        String::class -> groupValues[i] as T
        Int::class -> groupValues[i].toInt() as T
        Long::class -> groupValues[i].toLong() as T
        Float::class -> groupValues[i].toFloat() as T
        Double::class -> groupValues[i].toDouble() as T
        BigInteger::class -> groupValues[i].toBigInteger() as T
        BigDecimal::class -> groupValues[i].toBigDecimal() as T
        Char::class -> (groupValues[i].also { assert(it.length == 1) }.first() as T)
        else -> throw NotImplementedError("missing regex conversion for ${T::class}")
    }

inline fun <reified A : Any, R> String.destructure(
    regex: String,
    transform: (a: A) -> R
): R = Regex(regex).matchEntire(this)!!.run { transform(get(1)) }

inline fun <reified A : Any, reified B : Any, R> String.destructure(
    regex: String,
    transform: (a: A, b: B) -> R
): R = Regex(regex).matchEntire(this)!!.run { transform(get(1), get(2)) }

inline fun <reified A : Any, reified B : Any, reified C : Any, R> String.destructure(
    regex: String,
    transform: (a: A, b: B, c: C) -> R
): R = Regex(regex).matchEntire(this)!!.run { transform(get(1), get(2), get(3)) }

inline fun <reified A : Any, reified B : Any, reified C : Any, reified D : Any, R> String.destructure(
    regex: String,
    transform: (a: A, b: B, c: C, d: D) -> R
): R = Regex(regex).matchEntire(this)!!.run { transform(get(1), get(2), get(3), get(4)) }

inline fun <reified A : Any, reified B : Any, reified C : Any, reified D : Any, reified E : Any, R> String.destructure(
    regex: String,
    transform: (a: A, b: B, c: C, d: D, e: E) -> R
): R = Regex(regex).matchEntire(this)!!.run { transform(get(1), get(2), get(3), get(4), get(5)) }
