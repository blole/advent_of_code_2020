data class PasswordLine(val range: IntRange, val char: Char, val password: String) {
    fun isValidA() = password.count { it == char } in range
    fun isValidB() = (password[range.first-1] == char) != (password[range.last-1] == char)
}

class Day02(io: Kattio) {
    val passwords = io.words().chunked(3)
        .map { (range, char, password) ->
            PasswordLine(
                range = range.split('-').map { it.toInt() }.let { (low,high) -> low..high },
                char = char.first(),
                password = password
            )
        }

    fun a() = passwords.count { it.isValidA() }

    fun b() = passwords.count { it.isValidB() }
}
