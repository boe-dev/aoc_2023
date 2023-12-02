
val words = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
val revWords = words.map { it.reversed() }
fun main() {

    fun part1(input: List<String>): Int {
        return input
            .map { it.filter { it.isDigit() } }     // filter list that we only have digits
            .map { "${it.first()}${it.last()}".toInt() }    // get the first and the last digit as Int
            .sum()      // sum together everything
    }

    fun part2(input: List<String>): Int {
        val result = input.sumOf {
            getNumber(it, words) *10  + getNumber(it.reversed(), revWords)
        }
        return result
    }



    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)

    val testInput2 = readInput("Day01_test2")
    check(part2(testInput2) == 281)


    val input = readInput("Day01")
    part1(input).println() //54667
    part2(input).println() //54203
}

val digits = List(10) {"$it"}

fun getNumber(line: String, words: List<String>): Int {
    val (wordIxd, word) = line.findAnyOf(words) ?: (Int.MAX_VALUE to "not found")
    val (digitIex, digit) = line.findAnyOf(digits) ?: (Int.MAX_VALUE to "not found")

    return if (digitIex < wordIxd) {
        digit.toInt()
    } else {
        word.digitWordToInt(words)
    }
}

private fun String.digitWordToInt(wordList: List<String> = words) : Int {
    val index = wordList.indexOf(this)
    if (index == -1) error("$this in not a digit word!")
    return index + 1
}
