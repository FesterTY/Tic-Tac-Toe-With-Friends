package com.example.tic_tac_toe

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : Activity() {

    private val gameManager = GameManager(this, this)
    private val gridCount = 9
    private var gridButtons = mutableListOf<ImageButton>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 1..gridCount) {
            gridButtons.add(findViewById(resources.getIdentifier("ttt_box$i", "id", packageName)))
            gridButtons[i-1].setOnClickListener(tttBoxClicked())
        }
    }

    private fun tttBoxClicked(): View.OnClickListener {
        return View.OnClickListener {
            while(it.tag == "not_pressed") {
                Player(if (gameManager.currentPlayer == 1) 'x' else 'o').markSymbol(it as ImageButton)
                if (gameManager.checkGameOver(gridButtons)) {
                    gameManager.turnText.setTextColor(PlayerInfo.defaultTextColor)
                } else {
                    gameManager.switchTurn()
                }
            }
        }
    }
}
