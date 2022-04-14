package com.example.tictactoe

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*
import kotlin.random.Random
class MainActivity : AppCompatActivity() {
    lateinit var playerText:TextView
    var buttons = ArrayList<ImageButton>()
    var player = if(Random.nextBoolean()) {
        "crosses"
    }else{
        "circles"
    }
    private lateinit var image:ImageView
    // var player = if(Random.nextBoolean()) "crosses" else "circles"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var root = findViewById<LinearLayout>(R.id.root)
        playerText = TextView(applicationContext)
        playerText.text = player
        image = generatePlayerImage(player)
        root.addView(generatePlayerLayout())
        root.gravity = Gravity.CENTER
        initValues()
        for (i in 0..2){
            root.addView(createRow(i))
        }
    }
    fun createRow(y : Int) : LinearLayout{
        var row = LinearLayout(applicationContext)
        row.orientation = LinearLayout.HORIZONTAL
        var displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        row.minimumWidth = displayMetrics.widthPixels
        row.gravity = Gravity.CENTER
        for (i in 0..2){
            row.addView(generateButton(i, y))
            if(i <= 1) {
                row.addView(generateSpace(40))
            }
        }
        row.setPadding(20, 20, 20, 20)
        return row
    }
    fun generateButton(x : Int, y : Int) : ImageButton {
        var button = ImageButton(applicationContext)
        var params = getButtonParameters()
        button.layoutParams = ViewGroup.LayoutParams(params, params)
        button.setBackgroundColor(Color.LTGRAY)
        button.setPadding(20, 20, 20, 20)
        button.setOnClickListener {
            setValue(x, y, player)
            player = if(player == "crosses"){
                button.setBackgroundResource(R.drawable.cross)
                "circles"
            }else{
                button.setBackgroundResource(R.drawable.circle)
                "crosses"
            }
            refreshPlayerImage(player)
            button.isClickable = false
            var winner = checkForWinner()
            if (winner != "null"){
                openPopupWindow(winner)
            }
        }
        buttons.add(button)
        return button
    }
    fun openPopupWindow(winner:String){
        var intent = Intent(this, PopupWindow::class.java)
        intent.putExtra("winner", winner)
        startActivityForResult(intent, 2500)
    }
    fun setButtonsToDefault(){
        for(button in buttons){
            button.isClickable = true
            button.setBackgroundColor(Color.LTGRAY)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2500){
            setButtonsToDefault()
            eraseArray()
        }
    }
    fun getButtonParameters():Int{
        var displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        var width = displayMetrics.widthPixels
        var height = displayMetrics.heightPixels
        var min = Math.min(width, height)
        min = min / 3 - 40
        return min
    }
    fun generateSpace(width:Int = 20):Space{
        val space = Space(applicationContext)
        space.minimumWidth = width
        return space
    }
    fun generatePlayerImage(player:String):ImageView{
        val image = ImageView(applicationContext)
        if (player == "crosses"){
            image.setBackgroundResource(R.drawable.cross)
        }else{
            image.setBackgroundResource(R.drawable.circle)
        }
        var displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        var min = Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels)
        min /= 6
        image.layoutParams = ViewGroup.LayoutParams(min, min)
        return image
    }
    fun refreshPlayerImage(player: String){
        if (player == "crosses"){
            image.setBackgroundResource(R.drawable.cross)
        }else{
            image.setBackgroundResource(R.drawable.circle)
        }
    }
    fun generatePlayerLayout():LinearLayout{
        val playerRoot = LinearLayout(applicationContext)
        playerRoot.gravity = Gravity.CENTER
        playerRoot.orientation = LinearLayout.VERTICAL
        val text = TextView(applicationContext)
        text.text = "Hraje hráč: "
        text.textSize = 30.0F
        text.gravity = Gravity.CENTER
        text.setTextColor(Color.BLACK)
        playerRoot.addView(text)
        playerRoot.addView(image)
        return playerRoot
    }
}