import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import java.io.*

class AocTests {
    private fun string(s: String) = Kattio(ByteArrayInputStream(s.toByteArray()))
    private fun file(i: Int) = Kattio(File("src/test/resources/in/%02d.txt".format(i)).inputStream())

    @Test
    fun day01() {
        assertEquals(514579, Day01(string("1721 979 366 299 675 1456")).a())
        assertEquals(802011, Day01(file(1)).a())
        assertEquals(241861950, Day01(string("1721 979 366 299 675 1456")).b())
        assertEquals(248607374, Day01(file(1)).b())
    }
}
