package com.example.tic_tac_toe

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton

/***************
* CODE WRITTEN BY FESTER - TEERARIT W.
 **************/

class MainActivity : Activity() {

    private val gameManager = GameManager(this, this)
    private val gridCount = 9
    private var gridButtons = mutableListOf<ImageButton>()
    private lateinit var restartButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gameManager.init()

        restartButton = findViewById(R.id.restart_button)
        attachButtonsListener(tttBoxClicked())
    }

    private fun tttBoxClicked(): View.OnClickListener {
        return View.OnClickListener {
            while(it.tag == "not_pressed") {
                Player(if (gameManager.currentPlayer == 1) 'x' else 'o').markSymbol(it as ImageButton)

                if (gameManager.checkGameOver(gridButtons)) {
                    gameManager.turnText.setTextColor(PlayerInfo.defaultTextColor)
                    restartButton.visibility = View.VISIBLE
                    // Detach all listeners from buttons
                    attachButtonsListener(null)
                } else {
                    gameManager.switchTurn()
                }
            }
        }
    }

    private fun attachButtonsListener(functionToAttach: View.OnClickListener?) {
        for (i in 1..gridCount) {
            gridButtons.add(findViewById(resources.getIdentifier("ttt_box$i", "id", packageName)))
            gridButtons[i-1].setOnClickListener(functionToAttach)
        }
    }
}
