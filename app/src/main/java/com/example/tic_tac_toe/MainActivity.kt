package com.example.tic_tac_toe

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton

class MainActivity : Activity() {

    private val gridCount = 9

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 1..gridCount) {
            findViewById<ImageButton>(resources.getIdentifier("ttt_box$i", "id", packageName)).setOnClickListener(tttBoxClicked())
        }
    }

    private fun tttBoxClicked(): View.OnClickListener {
        return View.OnClickListener {
            Player('x').markSymbol(it as ImageButton)
        }
    }
}
