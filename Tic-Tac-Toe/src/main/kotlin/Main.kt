import java.lang.Exception

fun result(mutList2D: MutableList<MutableList<Char>>, mutList2D2: MutableList<MutableList<Char>>): Int {
    var x = false
    var o = false
    var isCompleted = true
    var countX = 0
    var countO = 0

    for (list in mutList2D) {
        if (list.contains(' ')) isCompleted = false
        for (el in list) {
            if (el == 'X') countX++ else if (el == 'O') countO++
        }
    }

    for (list in mutList2D) {
        if (list.contains('X') && !list.contains('O') && !list.contains(' ')) x = true
        else if (list.contains('O') && !list.contains('X') && !list.contains(' ')) o = true
    }

    for (list in mutList2D2) {
        if (list.contains('X') && !list.contains('O') && !list.contains(' ')) x = true
        else if (list.contains('O') && !list.contains('X') && !list.contains(' ')) o = true
    }

    if (mutList2D[0][0] == 'X' && mutList2D[1][1] == 'X' && mutList2D[2][2] == 'X' ||
        mutList2D[0][2] == 'X' && mutList2D[1][1] == 'X' && mutList2D[2][0] == 'X') x = true
    if (mutList2D[0][0] == 'O' && mutList2D[1][1] == 'O' && mutList2D[2][2] == 'O' ||
        mutList2D[0][2] == 'O' && mutList2D[1][1] == 'O' && mutList2D[2][0] == 'O') o = true

    if (!x && !o && !isCompleted) return 0
    if (!x && !o && isCompleted) println("Draw")
    else if (x && !o) println("X wins")
    else if (o && !x) println("O wins")
    return 1

}

fun main() {
    val mutList2D = MutableList(3) {
        MutableList(3) { ' ' }
    }

    println("\n---------")
    for (list in mutList2D) {
        println("| ${list[0]} ${list[1]} ${list[2]} |")
    }
    println("---------")
    var count = 0

    while (mutList2D[0].contains(' ') || mutList2D[1].contains(' ') || mutList2D[2].contains(' ')) {
        print("Enter the coordinates: ")
        var x = 0
        var y = 0
        try {
            val (a, b) = readLine()!!.split(" ").map { it.toInt() }
            x = a
            y = b
        } catch (e: Exception) {
            println("\nYou should enter numbers!")
            continue
        }

        if (x !in 1..3 || y !in 1..3) {
            println("\nCoordinates should be from 1 to 3!")
            continue
        }
        if (mutList2D[x - 1][y - 1] == ' ') {
            if (count % 2 == 0) mutList2D[x - 1][y - 1] = 'X' else mutList2D[x - 1][y - 1] = 'O'
            count++
        } else {
            println("\nThis cell is occupied! Choose another one!")
            continue
        }

        println("\n---------")
        for (list in mutList2D) {
            println("| ${list[0]} ${list[1]} ${list[2]} |")
        }
        println("---------")

        val mutList2D2 = mutableListOf(
            mutableListOf(mutList2D[0][0], mutList2D[1][0], mutList2D[2][0]),
            mutableListOf(mutList2D[0][1], mutList2D[1][1], mutList2D[2][1]),
            mutableListOf(mutList2D[0][2], mutList2D[1][2], mutList2D[2][2])
        )
        if (result(mutList2D, mutList2D2) == 1) break
    }
}