data class PasswordLine(val range: IntRange, val char: Char, val password: String) {
    fun isValidA() = password.count { it == char } in range
    fun isValidB() = (password[range.first-1] == char) != (password[range.last-1] == char)
}

class Day02(io: Kattio) {
    val passwords = io.lines()
        .map {
            it.destructure("(\\d+)-(\\d+) (.): (.+)") { low: Int, high: Int, char: Char, password: String ->
                PasswordLine(
                    range = low..high,
                    char = char,
                    password = password
                )
            }!!
        }

    fun a() = passwords.count { it.isValidA() }
    fun b() = passwords.count { it.isValidB() }
}
