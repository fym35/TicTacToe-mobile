package com.example.tictactoe

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PopupWindow : AppCompatActivity() {
    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popup_window)
        val winner = intent.getStringExtra("winner")
        writeWinner(winner!!)
        val button = findViewById<Button>(R.id.restart)
        button.setOnClickListener {
            this.finish()
        }
    }
    fun writeWinner(winner:String){
        var result = if (winner == "game-over"){
            "Remíza"
        } else if(winner == "crosses"){
            "Vyhrály křížky"
        }else{
            "Vyhrála kolečka"
        }
        findViewById<TextView>(R.id.winner).text = result
    }
}