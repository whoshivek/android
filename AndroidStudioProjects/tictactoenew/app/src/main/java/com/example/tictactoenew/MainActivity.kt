package com.example.tictactoenew

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener
{
    lateinit var board: Array<Array<Button>>
    var turn = 0
    var boardstatus = Array(3) { IntArray(3) }
    var PLAYER = true

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        board = arrayOf(
            arrayOf(button, button2, button3),
            arrayOf(button4, button5, button6),
            arrayOf(button7, button8, button9)
        )
        for (i in board) {
            for (button in i) {
                button.setOnClickListener(this)
            }
        }

        intialiseboard()

        reset.setOnClickListener(
            {
                PLAYER = true
                turn = 0
                intialiseboard()
                textv.text ="X TURN"

            }
        )





    }

    private fun intialiseboard() {
        for(i in 0..2)
        {
            for (j in 0..2)
            {
                boardstatus[i][j] == -1

            }
        }
        for (i in board)
        {
            for(button in i)
            {
                button.isEnabled = true
                button.text=""

            }
        }
    }

    override fun onClick(v: View) {
        when(v.id)
        {
            R.id.button ->
            {
                   displayvalue(row=0 , col = 0 , player=PLAYER)
            }
            R.id.button2 ->
            {
                displayvalue(row=0 , col = 1 , player=PLAYER)
            }
            R.id.button3 ->
            {
                displayvalue(row=0 , col = 2 , player=PLAYER)
            }
            R.id.button4 ->
            {
                displayvalue(row=1 , col = 0 , player=PLAYER)
            }
            R.id.button5 ->
            {
                displayvalue(row=1 , col = 1 , player=PLAYER)
            }
            R.id.button6 ->
            {
                displayvalue(row=1 , col = 2 , player=PLAYER)
            }
            R.id.button7 ->
            {
                displayvalue(row=2 , col = 0 , player=PLAYER)
            }
            R.id.button8 ->
            {
                displayvalue(row=2 , col = 1 , player=PLAYER)
            }
            R.id.button9 ->
            {
                displayvalue(row=2 , col = 2, player=PLAYER)
            }
        }

        PLAYER = !PLAYER
        turn++

        if(PLAYER)
        {
            updatetext("X TURN")
        }
       else
        {
            updatetext("O TURN")
        }
        if(turn==9)
        {
            updatetext("DRAW")
        }

       // checkWinner()
    }

    private fun checkWinner() {
        for (i in 0..2) {
            if (boardstatus[i][0] == boardstatus[i][1] && boardstatus[i][0] == boardstatus[i][2]) {
                if (boardstatus[i][0] == 1) {
                    result("Player X winner")
                    break
                } else if (boardstatus[i][0] == 0) {
                    result("Player 0 winner")
                    break
                }
            }
        }
    }


    private fun result(s: String) {
        textv.text= s
        if (s.contains("winner"))
        {
            disablebutton()
        }
           else
        {

        }

    }

    private fun disablebutton() {
for(i in board)
{
    for (button in i)
    {
        button.isEnabled = false
    }
}
    }

    private fun updatetext(s: String) {
           textv.text = s
    }


    private fun displayvalue(row: Int, col: Int, player: Boolean) {

        val t= if(PLAYER) "X" else "O"
        val v = if(PLAYER) 1 else 0

        board[row][col].apply {

            isEnabled= true
            setText(t)

        }
        boardstatus[row][col] = v

    }
}

