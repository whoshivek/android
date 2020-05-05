package com.example.zerokaata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var board : Array<Array<Button>>
    var check = Array(3){ IntArray(3)}
    var PLAYER = true
    var turn =0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf(arrayOf(button , button2 , button3), arrayOf(button4 , button5, button6), arrayOf(button7,button8,button9))
        for (i in board)
        {
            for (button in i)
            {
             button.setOnClickListener(this)
        }
        }

        initializecheck()
        
        reset.setOnClickListener(
            {
                initializecheck()
                textv.text ="PLAYER X TURN"
                PLAYER = true
                turn =0

            }
        )

}

    override fun onClick(v: View) {

        when(v.id)
        {

            R.id.button -> {
                updatetext(row = 0 , col = 0 , palyer = PLAYER)


            }
            R.id.button2 -> {
                updatetext(row = 0 , col = 1 , palyer = PLAYER)
            }
            R.id.button3 -> {
                updatetext(row = 0 , col = 2 , palyer = PLAYER)
            }
            R.id.button4 -> {
                updatetext(row = 1 , col = 0 , palyer = PLAYER)
            }
            R.id.button5 -> {
                updatetext(row = 1 , col = 1, palyer = PLAYER)
            }
            R.id.button6 -> {
                updatetext(row = 1 , col = 2 , palyer = PLAYER)
            }
            R.id.button7 -> {
                updatetext(row = 2 , col = 0 , palyer = PLAYER)
            }
            R.id.button8 -> {
                updatetext(row = 2 , col = 1 , palyer = PLAYER)
            }
            R.id.button9 -> {
                updatetext(row = 2 , col =2 , palyer = PLAYER)
            }
        }


           PLAYER =!PLAYER
        turn++

        if(PLAYER)
            displaytext("PLAYER X TURN")
        else
            displaytext("PLAYER O TURN")
        if (turn ==9)
            displaytext("DRAW")

        checkwinner()


    }

    private fun checkwinner() {
        for (i in 0..2) {
            //HOR
            if (check[i][0] == check[i][1] && check[i][0] == check[i][2]) {
                if (check[i][0] == 1) {
                    displaytext("X WINS")
                    break
                }
                if (check[i][0] == 0) {
                    displaytext("O WINS")
                    break
                }
            }

            // VER
            if (check[0][i] == check[1][i] && check[0][i] == check[2][i]) {
                if (check[0][i] == 1)
                    displaytext("X WINS")
                if (check[0][i] == 0)
                    displaytext("O WINS")
            }
        }
            //DIAG
            if(check[0][0]==check[1][1] && check[0][0]==check[2][2])
            {
                if (check[0][0]==1)
                    displaytext("X WINS")
                if (check[0][0]==0)
                    displaytext("O WINS")
            }
        if(check[0][2]==check[1][1] && check[0][2]==check[2][0])
        {
            if (check[0][2]==1)
                displaytext("X WINS")
            if (check[0][2]==0)
                displaytext("O WINS")
        }





    }

    private fun initializecheck() {
        for (i in  board)
            for (button in i) {

                button.isEnabled = true
                button.text =""
            }
        for (i in 0..2)
            for (j in 0..2)
            {
                check[i][j]=-1
            }
    }

    private fun displaytext(s: String) {
        textv.text = s
        if(s.contains("WINS"))
        {
           disablebutton()
        }

    }

    private fun disablebutton() {
        for (i in  board)
            for (button in i)
                button.isEnabled = false
    }


    private fun updatetext(row: Int, col: Int, palyer: Boolean) {

        val ch = if (PLAYER) "X" else "O"
        val v = if (PLAYER) 1 else 0
        board[row][col].apply {
            isEnabled = true
           setText(ch)
            isEnabled=false
        }
        check[row][col]= v


    }


}


