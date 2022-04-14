package com.example.tictactoe

import android.util.Log

val values = ArrayList<ArrayList<String>>(3)
fun initValues() {
    for (i in 0..2) {
        values.add(ArrayList<String>())
        for (j in 0..2) {
            values[i].add("null")
        }
    }
}

fun get(x: Int, y: Int): String {
    if (x <= 2 && y <= 2) {
        return values[x][y]
    }
    return "out of range"
}

fun setValue(x: Int, y: Int, value: String) {
    if (x <= 2 && y <= 2) {
        values[x][y] = value
    }
}

fun printBoard() {
    for (x in 0..2) {
        var buffer = ""
        for (y in 0..2) {
            buffer += "${values[x][y]} "
        }
        Log.i("App", buffer)
    }
}

fun isEnd(): Boolean {
    for (x in 0..2) {
        for (y in 0..2) {
            if (get(x, y) == "null") {
                return false
            }
        }
    }
    return true
}

fun checkLine(lineNumber: Int): String {
    var crosses = 0
    var circles = 0
    for (x in 0..2) {
        var value = get(x, lineNumber)
        if (value == "crosses") {
            crosses++
        } else if (value == "circles") {
            circles++
        }
    }
    if (crosses == 3) {
        return "crosses"
    } else if (circles == 3) {
        return "circles"
    }
    return "null"
}

fun checkAllLines(): String {
    for (y in 0..2) {
        if (checkLine(y) != "null") {
            return checkLine(y)
        }
    }
    return "null"
}

fun checkRow(row: Int): String {
    var crosses = 0
    var circles = 0
    for (y in 0..2) {
        var value = get(row, y)
        if (value == "crosses") {
            crosses++
        } else if (value == "circles") {
            circles++
        }
    }
    if (crosses == 3) {
        return "crosses"
    } else if (circles == 3) {
        return "circles"
    }
    return "null"
}

fun checkAllRows(): String {
    for (x in 0..2) {
        var winner = checkRow(x)
        if (winner == "crosses" || winner == "circles") {
            return winner
        }
    }
    return "null"
}

fun checkMainDiagonal(): String {
    var crosses = 0
    var circles = 0
    for (xy in 0..2) {
        var value = get(xy, xy)
        if (value == "crosses") {
            crosses++
        } else if (value == "circles") {
            circles++
        }
    }
    if (crosses == 3) {
        return "crosses"
    } else if (circles == 3) {
        return "circles"
    }
    return "null"
}


fun checkSecondDiagonal(): String {
    var crosses = 0
    var circles = 0
    for (xy in 0..2) {
        var value = get(2-xy, xy)
        if (value == "crosses") {
            crosses++
        } else if (value == "circles") {
            circles++
        }
    }
    if (crosses == 3) {
        return "crosses"
    } else if (circles == 3) {
        return "circles"
    }
    return "null"
}


fun checkForWinner(): String {
    if (checkAllLines() != "null") {
        return checkAllLines()
    }
    if (checkAllRows() != "null") {
        return checkAllRows()
    }
    if (checkMainDiagonal() != "null"){
        return checkMainDiagonal()
    }
    if (checkSecondDiagonal() != "null"){
        return checkSecondDiagonal()
    }

    if (isEnd()) {
        return "game-over"
    }
    return "null"
}

fun eraseArray(){
    for(x in 0..2){
        for (y in 0..2){
            values[x][y] = "null"
        }
    }
}



