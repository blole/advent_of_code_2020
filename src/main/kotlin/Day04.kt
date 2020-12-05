import Validated.Companion.notProvided
import Validated.Companion.validate

class Validated<T : Any> private constructor(val value: T?, val present: Boolean) {
    constructor(value: T): this(value, present = true)

    val valid get() = value != null

    companion object {
        fun <T : Any> invalid(): Validated<T> = Validated(value = null, present = true)
        fun <T : Any> notProvided(): Validated<T> = Validated(value = null, present = false)

        fun <T : Any> T?.validate(): Validated<T> = if (this != null) Validated(this) else invalid()
    }
}

data class Passport(
    var birthYear: Validated<Int> = notProvided(),
    var countryId: Validated<Int> = notProvided(),
    var eyeColor: Validated<String> = notProvided(),
    var expirationYear: Validated<Int> = notProvided(),
    var hairColor: Validated<String> = notProvided(),
    var height: Validated<Int> = notProvided(),
    var issueYear: Validated<Int> = notProvided(),
    var passportId: Validated<String> = notProvided(),
) {
    val importantFields get() = listOf(birthYear, eyeColor, expirationYear, hairColor, height, issueYear, passportId)
}

class Day04(io: Kattio) {
    val passports = io.lines().joinToString(" ").split("  ")
        .map { line ->
            Passport().apply {
                if ("iyr" in line) issueYear      = line.match1<Int>(".*iyr:(\\d{4})\\b.*")?.takeIf { it in 2010..2020 }.validate()
                if ("byr" in line) birthYear      = line.match1<Int>(".*byr:(\\d{4})\\b.*")?.takeIf { it in 1920..2002 }.validate()
                if ("eyr" in line) expirationYear = line.match1<Int>(".*eyr:(\\d{4})\\b.*")?.takeIf { it in 2020..2030 }.validate()
                if ("cid" in line) countryId      = line.match1<Int>(".*cid:(\\d*)\\b.*").validate()
                if ("pid" in line) passportId     = line.match1<String>(".*pid:(\\d{9})\\b.*").validate()
                if ("hcl" in line) hairColor      = line.match1<String>(".*hcl:#([0-9a-f]{6})\\b.*").validate()
                if ("ecl" in line) eyeColor       = line.match1<String>(".*ecl:(\\w*)\\b.*")?.takeIf { it in "amb blu brn gry grn hzl oth" }.validate()
                if ("hgt" in line) height         = line.match2<Int,String>(".*hgt:(\\d+)(cm|in)\\b.*")?.takeIf { (height: Int, unit: String) ->
                    when (unit) {
                        "cm" -> height in 150..193
                        "in" -> height in 59..76
                        else -> false
                    }
                }?.first.validate()
            }
        }

    fun a() = passports.count { passport -> passport.importantFields.all { it.present } }
    fun b() = passports.count { passport -> passport.importantFields.all { it.valid } }
}