package com.example.tic_tac_toe

import android.util.Log
import android.widget.ImageButton

class Player(_symbol: Char) {

    private var symbol: Char = _symbol

    fun markSymbol(currentButton: ImageButton) {
        when (symbol) {
            'x' -> currentButton.setImageResource(R.drawable.x)
            'o' -> currentButton.setImageResource(R.drawable.o)
            else -> Log.e("com.example.tic_tac_toe","Symbol is neither x nor o")
        }
    }

}