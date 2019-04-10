package com.example.tic_tac_toe

import android.app.Activity
import android.content.Context
import android.widget.ImageButton
import android.widget.Toast
import java.lang.Math.sqrt

class GameManager(_context: Context, _activity: Activity) {

    private val context = _context
    private val activity = _activity
    var currentPlayer = 0

    fun switchTurn() {
        currentPlayer = 1 - currentPlayer
    }

    fun checkGameOver(buttons: MutableList<ImageButton>): Boolean {
        val gridSize = Math.floor(sqrt(buttons.size.toDouble())).toInt()

        return when {
            checkVertical(gridSize) -> true
            checkHorizontal(gridSize) -> true
            checkBoardFull(buttons) -> true
            else -> false
        }

    }

    private fun checkVertical(gridSize: Int): Boolean {

        val sizeToIncrementBy = gridSize

        for (col in 1..gridSize) {
            var currentBoxNum = col
            val buttonsList = arrayListOf<String>()

            for (rows in 1..gridSize) {
                buttonsList += activity.findViewById<ImageButton>(context.resources.getIdentifier("ttt_box$currentBoxNum",
                    "id", context.packageName)).tag.toString()
                currentBoxNum += sizeToIncrementBy
            }

            // Once finish loop through the rows
            if(checkStraight(buttonsList)) {
                Toast.makeText(context, "${buttonsList[0]} is the winner by vertically!", Toast.LENGTH_SHORT).show()
                return true
            }
        }

        return false
    }

    private fun checkHorizontal(gridSize: Int): Boolean {

        val sizeToIncrementBy = 1

        for (row in 1..(gridSize*gridSize) step gridSize) {
            var currentBoxNum = row
            val buttonsList = arrayListOf<String>()

            for (col in 1..gridSize) {
                buttonsList += activity.findViewById<ImageButton>(context.resources.getIdentifier("ttt_box$currentBoxNum",
                    "id", context.packageName)).tag.toString()
                currentBoxNum += sizeToIncrementBy
            }

            // Once finish loop through the rows
            if(checkStraight(buttonsList)) {
                Toast.makeText(context, "${buttonsList[0]} is the winner by horizontally!", Toast.LENGTH_SHORT).show()
                return true
            }
        }

        return false
    }

    // Check whether or not board is full
    private fun checkBoardFull(buttons: MutableList<ImageButton>): Boolean {
        var pressedButtons = 0
        for (button in buttons) {
            if (button.tag.toString() == "x" || button.tag.toString() == "o") {
                pressedButtons++
            }
        }
        if (pressedButtons >= 9) {
            Toast.makeText(context, "Board is full! Cannot continue further.", Toast.LENGTH_LONG).show()
            return true
        }

        return false
    }

    private fun checkStraight(buttons: ArrayList<String>): Boolean {
        if ((buttons.count { it == buttons[0] } == buttons.size) && buttons[0] != PlayerInfo.defaultTag) {
            return true
        }
        return false
    }
}