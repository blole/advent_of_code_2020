import java.io.*
import java.math.BigInteger
import java.util.*


class Kattio(i: InputStream = System.`in`, o: OutputStream = System.out) : PrintWriter(BufferedOutputStream(o)) {
    private val reader: BufferedReader = BufferedReader(InputStreamReader(i))

    private var tokenizer: StringTokenizer? = null
        get() {
            while (field == null || !field!!.hasMoreTokens()) {
                val line = reader.readLine() ?: return null
                field = StringTokenizer(line)
            }
            return field
        }

    private var token: String? = null
        get() {
            if (field == null)
                field = tokenizer?.nextToken()
            return field
        }

    private fun nextTokenOrNull(): String? = token.also { token = null }

    val hasMoreTokens: Boolean get() = token != null

    fun intOrNull():    Int?        = nextTokenOrNull()?.toInt()
    fun longOrNull():   Long?       = nextTokenOrNull()?.toLong()
    fun bigintOrNull(): BigInteger? = nextTokenOrNull()?.toBigInteger()
    fun doubleOrNull(): Double?     = nextTokenOrNull()?.toDouble()
    fun wordOrNull():   String?     = nextTokenOrNull()
    fun lineOrNull():   String?     = tokenizer?.nextToken("")?.also { token = null }

    fun int():    Int        = intOrNull()!!
    fun long():   Long       = longOrNull()!!
    fun bigint(): BigInteger = bigintOrNull()!!
    fun double(): Double     = doubleOrNull()!!
    fun word():   String     = wordOrNull()!!
    fun line():   String     = lineOrNull()!!

    fun ints():    Sequence<Int>        = generateSequence { intOrNull() }
    fun longs():   Sequence<Long>       = generateSequence { longOrNull() }
    fun bigints(): Sequence<BigInteger> = generateSequence { bigintOrNull() }
    fun doubles(): Sequence<Double>     = generateSequence { doubleOrNull() }
    fun words():   Sequence<String>     = generateSequence { wordOrNull() }
    fun lines():   Sequence<String>     = generateSequence { lineOrNull() }
}
