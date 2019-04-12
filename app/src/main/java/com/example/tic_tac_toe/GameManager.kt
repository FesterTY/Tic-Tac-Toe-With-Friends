package com.example.tic_tac_toe

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import java.lang.Exception
import java.lang.Math.sqrt

class GameManager(_context: Context, _activity: Activity) {

    private val context = _context
    private val activity = _activity

    lateinit var turnText: TextView

    val xColor = "#00abff"
    private val oColor = Color.DKGRAY

    var currentPlayer = 1

    fun init() {
        turnText = activity.findViewById(R.id.turn_text)
    }

    fun switchTurn() {
        currentPlayer = 1 - currentPlayer
        when (currentPlayer) {
            0 -> {
                turnText.setText(R.string.o_turn)
                turnText.setTextColor(oColor)
            }
            1 -> {
                turnText.setText(R.string.x_turn)
                turnText.setTextColor(Color.parseColor(xColor))
            }
        }
    }

    fun checkGameOver(buttons: MutableList<ImageButton>): Boolean {
        val gridSize = sqrt(buttons.size.toDouble()).toInt()

        return when {
            checkVertical(gridSize) -> true
            checkHorizontal(gridSize) -> true
            checkDiagonal(gridSize) -> true
            checkBoardFull(buttons) -> {
                turnText.setText(R.string.draw)
                true
            }
            else -> false
        }

    }

    // Check for vertical win (|)
    private fun checkVertical(gridSize: Int): Boolean {
        // Go through each column, increment the index of the box by 3, making it loops through vertically
        return loopThroughGrid(gridSize, gridSize)
    }

    // Check for horizontal win (--)
    private fun checkHorizontal(gridSize: Int): Boolean {
        // Increment the column by 3 every time. After the increment, go through the row one by one.
        return loopThroughGrid(gridSize, 1, gridSize*gridSize, 3)
    }

    private fun checkDiagonal(gridSize: Int): Boolean {
        return when {
            // forward diagonal
            // Only one column, but increment the row by gridSize+1 to get the forward diagonal row
            (loopThroughGrid(gridSize, gridSize+1, 1)) -> true
            // backward diagonal
            // Only one column, but increment the row by gridSize-1 to get the backward diagonal row
            (loopThroughGrid(gridSize, gridSize-1, 1, startingCol = 3)) -> true
            else -> false
        }
    }

    // Check whether or not board is full
    private fun checkBoardFull(buttons: MutableList<ImageButton>): Boolean {
        var pressedButtons = 0
        for (button in buttons) {
            if (button.tag.toString() == "x" || button.tag.toString() == "o") {
                pressedButtons++
            }
        }
        if (pressedButtons >= 9)
            return true

        return false
    }

    private fun loopThroughGrid(gridSize: Int, sizeToIncrementBy: Int, maximumColumnToLoop: Int=3, columnsToStep: Int=1, startingCol: Int=0): Boolean {
        for (col in 1..maximumColumnToLoop step columnsToStep) {
            // If startingCol isn't set, use col, if startingCol IS set, use startingCol
            var currentBoxNum = if(startingCol == 0) col else startingCol
            Log.i(activity.packageName, currentBoxNum.toString())
            val buttonsList = arrayListOf<String>()

            for (row in 1..gridSize) {
                try {
                    buttonsList += activity.findViewById<ImageButton>(context.resources.getIdentifier("ttt_box$currentBoxNum",
                        "id", context.packageName)).tag.toString()
                } catch(e: Exception) {
                    Log.w(activity.packageName, currentBoxNum.toString())
                }
                currentBoxNum += sizeToIncrementBy
            }

            // Once finish loop through the rows
            if (checkStraight(buttonsList)) {
                return true
            }
        }

        return false
    }

    private fun checkStraight(buttons: ArrayList<String>): Boolean {
        if ((buttons.count { it == buttons[0] } == buttons.size) && buttons[0] != PlayerInfo.defaultTag) {
            val winText = activity.resources.getString(R.string.win, buttons[0].toUpperCase())
            turnText.text = winText
            return true
        }
        return false
    }
}
