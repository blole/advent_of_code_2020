import Tokenizer.Companion.tokenize
import java.io.*
import java.math.BigInteger

class Tokenizer(val line: String) {
    private var start = 0

    private fun nextTokenRange(delimiters: String? = null): IntRange? {

        var i = start

        if (delimiters == null) {
            while (i < line.length && line[i].isWhitespace()) i++
        } else {
            while (i < line.length && line[i] in delimiters) i++
        }

        val from = i

        if (delimiters == null) {
            while (i < line.length && !line[i].isWhitespace()) i++
        } else {
            while (i < line.length && line[i] !in delimiters) i++
        }

        return (from until i).takeIf { !it.isEmpty() }
    }

    fun hasMoreTokens(delimiters: String? = null): Boolean = nextTokenRange(delimiters) != null

    fun peekToken(delimiters: String? = null): String? {
        if (start == 0 && delimiters == "" && line.length == 0)
            return "".also { start = 1 }

        val nextTokenRange = nextTokenRange(delimiters) ?: return null
        return line.substring(nextTokenRange)
    }

    fun nextToken(delimiters: String? = null): String? {
        if (start == 0 && delimiters == "" && line.length == 0)
            return "".also { start = 1 }

        val nextTokenRange = nextTokenRange(delimiters) ?: return null
        start = nextTokenRange.last + 1
        return line.substring(nextTokenRange)
    }

    companion object {
        fun String.tokenize(): Tokenizer = Tokenizer(this)
    }
}

class Kattio(i: InputStream = System.`in`, o: OutputStream = System.out) : PrintWriter(BufferedOutputStream(o)) {
    private val reader: BufferedReader = BufferedReader(InputStreamReader(i))

    private var tokenizer: Tokenizer? = null
        get() {
            if (field == null)
                field = reader.readLine()?.tokenize()
            return field
        }

    private fun nextToken(delimiters: String? = null): String? {
        while (tokenizer != null) {
            val token = tokenizer!!.nextToken(delimiters)
            if (token != null)
                return token
            else
                tokenizer = null
        }
        return null
    }

    val hasMoreTokens: Boolean get() = tokenizer?.hasMoreTokens() ?: false

    fun intOrNull():    Int?        = nextToken()?.toInt()
    fun longOrNull():   Long?       = nextToken()?.toLong()
    fun bigintOrNull(): BigInteger? = nextToken()?.toBigInteger()
    fun doubleOrNull(): Double?     = nextToken()?.toDouble()
    fun wordOrNull():   String?     = nextToken()
    fun lineOrNull():   String?     = nextToken("")

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
