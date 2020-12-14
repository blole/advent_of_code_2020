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

    @Test
    fun day02() {
        assertEquals(2, Day02(string("1-3 a: abcde\n1-3 b: cdefg\n2-9 c: ccccccccc")).a())
        assertEquals(591, Day02(file(2)).a())
        assertEquals(1, Day02(string("1-3 a: abcde\n1-3 b: cdefg\n2-9 c: ccccccccc")).b())
        assertEquals(335, Day02(file(2)).b())
    }

    @Test
    fun day03() {
        val example = """
            ..##.......
            #...#...#..
            .#....#..#.
            ..#.#...#.#
            .#...##..#.
            ..#.##.....
            .#.#.#....#
            .#........#
            #.##...#...
            #...##....#
            .#..#...#.#
        """.trimIndent()
        assertEquals(7, Day03(string(example)).a())
        assertEquals(156, Day03(file(3)).a())
        assertEquals(336, Day03(string(example)).b())
        assertEquals(3521829480, Day03(file(3)).b())
    }

    @Test
    fun day04() {
        val example = """
            ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
            byr:1937 iyr:2017 cid:147 hgt:183cm

            iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
            hcl:#cfa07d byr:1929

            hcl:#ae17e1 iyr:2013
            eyr:2024
            ecl:brn pid:760753108 byr:1931
            hgt:179cm

            hcl:#cfa07d eyr:2025 pid:166559648
            iyr:2011 ecl:brn hgt:59in
        """.trimIndent()
        assertEquals(2, Day04(string(example)).a())
        assertEquals(247, Day04(file(4)).a())

        val allInvalidB = """
            eyr:1972 cid:100
            hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926

            iyr:2019
            hcl:#602927 eyr:1967 hgt:170cm
            ecl:grn pid:012533040 byr:1946

            hcl:dab227 iyr:2012
            ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277

            hgt:59cm ecl:zzz
            eyr:2038 hcl:74454a iyr:2023
            pid:3556412378 byr:2007
        """.trimIndent()
        val allValidB = """
            pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980
            hcl:#623a2f

            eyr:2029 ecl:blu cid:129 byr:1989
            iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm

            hcl:#888785
            hgt:164cm byr:2001 iyr:2015 cid:88
            pid:545766238 ecl:hzl
            eyr:2022

            iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719
        """.trimIndent()
        assertEquals(0, Day04(string(allInvalidB)).b())
        assertEquals(4, Day04(string(allValidB)).b())
        assertEquals(145, Day04(file(4)).b())
    }

    @Test
    fun day05() {
        assertEquals(567, Day05(string("BFFFBBFRRR")).a())
        assertEquals(119, Day05(string("FFFBBBFRRR")).a())
        assertEquals(820, Day05(string("BBFFBBFRLL")).a())
        assertEquals(842, Day05(file(5)).a())
        assertEquals(617, Day05(file(5)).b())
    }

    @Test
    fun day06() {
        val example = """
            abc

            a
            b
            c

            ab
            ac

            a
            a
            a
            a

            b
        """.trimIndent()
        assertEquals(11, Day06(string(example)).a())
        assertEquals(6930, Day06(file(6)).a())
        assertEquals(6, Day06(string(example)).b())
        assertEquals(3585, Day06(file(6)).b())
    }

    @Test
    fun day07() {
        val example = """
            light red bags contain 1 bright white bag, 2 muted yellow bags.
            dark orange bags contain 3 bright white bags, 4 muted yellow bags.
            bright white bags contain 1 shiny gold bag.
            muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
            shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
            dark olive bags contain 3 faded blue bags, 4 dotted black bags.
            vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
            faded blue bags contain no other bags.
            dotted black bags contain no other bags.
        """.trimIndent()
        assertEquals(4, Day07(string(example)).a())
        assertEquals(238, Day07(file(7)).a())
        val example2 = """
            shiny gold bags contain 2 dark red bags.
            dark red bags contain 2 dark orange bags.
            dark orange bags contain 2 dark yellow bags.
            dark yellow bags contain 2 dark green bags.
            dark green bags contain 2 dark blue bags.
            dark blue bags contain 2 dark violet bags.
            dark violet bags contain no other bags.
        """.trimIndent()
        assertEquals(32, Day07(string(example)).b())
        assertEquals(126, Day07(string(example2)).b())
        assertEquals(82930, Day07(file(7)).b())
    }

    @Test
    fun day08() {
        val example = """
            nop +0
            acc +1
            jmp +4
            acc +3
            jmp -3
            acc -99
            acc +1
            jmp -4
            acc +6
        """.trimIndent()
        assertEquals(5, Day08(string(example)).a())
        assertEquals(1818, Day08(file(8)).a())
        assertEquals(8, Day08(string(example)).b())
        assertEquals(631, Day08(file(8)).b())
    }

    @Test
    fun day09() {
        val example = """
            35
            20
            15
            25
            47
            40
            62
            55
            65
            95
            102
            117
            150
            182
            127
            219
            299
            277
            309
            576
        """.trimIndent()
        assertEquals(127, Day09(string(example), 5).a())
        assertEquals(1398413738, Day09(file(9), 25).a())
        assertEquals(62, Day09(string(example), 5).b())
        assertEquals(169521051, Day09(file(9), 25).b())
    }

    @Test
    fun day10() {
        val example1 = "16 10 15 5 1 11 7 19 6 12 4"
        val example2 = "28 33 18 42 31 14 46 20 48 47 24 23 49 45 19 38 39 11 1 32 25 35 8 17 7 9 4 2 34 10 3"
        assertEquals(35, Day10(string(example1)).a())
        assertEquals(220, Day10(string(example2)).a())
        assertEquals(2432, Day10(file(10)).a())
        assertEquals(8, Day10(string(example1)).b())
        assertEquals(19208, Day10(string(example2)).b())
        assertEquals(453551299002368, Day10(file(10)).b())
    }

    @Test
    fun day11() {
        val example = """
            L.LL.LL.LL
            LLLLLLL.LL
            L.L.L..L..
            LLLL.LL.LL
            L.LL.LL.LL
            L.LLLLL.LL
            ..L.L.....
            LLLLLLLLLL
            L.LLLLLL.L
            L.LLLLL.LL
        """.trimIndent()
        assertEquals(37, Day11(string(example)).a())
        assertEquals(2277, Day11(file(11)).a())
        assertEquals(26, Day11(string(example)).b())
        assertEquals(2066, Day11(file(11)).b())
    }

    @Test
    fun day12() {
        val example = """
            F10
            N3
            F7
            R90
            F11
        """.trimIndent()
        assertEquals(25, Day12(string(example)).a())
        assertEquals(1133, Day12(file(12)).a())
        assertEquals(286, Day12(string(example)).b())
        assertEquals(61053, Day12(file(12)).b())
    }

    @Test
    fun day13() {
        assertEquals(295, Day13(string("939 7,13,x,x,59,x,31,19")).a())
        assertEquals(2092, Day13(file(13)).a())
        assertEquals(3417.B, Day13(string("0 17,x,13,19")).b())
        assertEquals(754018.B, Day13(string("0 67,7,59,61")).b())
        assertEquals(779210.B, Day13(string("0 67,x,7,59,61")).b())
        assertEquals(1261476.B, Day13(string("0 67,7,x,59,61")).b())
        assertEquals(1202161486.B, Day13(string("0 1789,37,47,1889")).b())
        assertEquals(702970661767766.B, Day13(file(13)).b())
    }

    @Test
    fun day14() {
        val example = """
            mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
            mem[8] = 11
            mem[7] = 101
            mem[8] = 0
        """.trimIndent()
        assertEquals(165, Day14(string(example)).a())
        assertEquals(15172047086292, Day14(file(14)).a())
        val example2 = """
            mask = 000000000000000000000000000000X1001X
            mem[42] = 100
            mask = 00000000000000000000000000000000X0XX
            mem[26] = 1
        """.trimIndent()
        assertEquals(208, Day14(string(example2)).b())
        assertEquals(4197941339968, Day14(file(14)).b())
    }
}
