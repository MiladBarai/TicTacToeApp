package com.onlyi.and.ai.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun ticTacToeButtonPress(view:View){

        //Convert view to button
        val pressedButton = view as Button

        var pressedCell = 0
        when(pressedButton.id){
            R.id.button1 -> pressedCell = 1
            R.id.button2 -> pressedCell = 2
            R.id.button3 -> pressedCell = 3
            R.id.button4 -> pressedCell = 4
            R.id.button5 -> pressedCell = 5
            R.id.button6 -> pressedCell = 6
            R.id.button7 -> pressedCell = 7
            R.id.button8 -> pressedCell = 8
            R.id.button9 -> pressedCell = 9
        }

        Log.d("button", "pressed cell: $pressedCell")
        insertSelection(pressedCell, pressedButton)
    }

    var activePlayer = 0
    var turnCount = 0
    var playerSelections = arrayOf(ArrayList<Int>(), ArrayList<Int>())
    val board = IntArray(9)

    fun insertSelection(pressedCell:Int, pressedButton:Button){

        turnCount++

        var nextPlayer:Int

        if(activePlayer == 0){
            pressedButton.setBackgroundResource(R.drawable.x)
            nextPlayer = 1
        } else {
            pressedButton.setBackgroundResource(R.drawable.o)
            nextPlayer = 0
        }

        playerSelections[activePlayer].add(pressedCell)
        pressedButton.isEnabled = false

        val (start, end) = checkIfWin(playerSelections[activePlayer])

        if(start != -1 && end != -1){
            //draw a line from start to end
            Toast.makeText(baseContext, "${turnText.text} won!", Toast.LENGTH_LONG).show()
            clearBoard()
        } else if (turnCount == 9){
            Toast.makeText(baseContext, "Tie!", Toast.LENGTH_LONG).show()
            clearBoard()
        } else {
            activePlayer = nextPlayer
        }

        if(activePlayer == 0){
            turnText.text = "X"
        } else{
            turnText.text = "O"
        }


    }

    fun clearBoard(){
        val buttons = ArrayList<Button>()

        //Reset values
        playerSelections = arrayOf(ArrayList<Int>(), ArrayList<Int>())
        activePlayer = 0
        turnCount = 0
        turnText.text = "X"

        buttons.add(button1)
        buttons.add(button2)
        buttons.add(button3)
        buttons.add(button4)
        buttons.add(button5)
        buttons.add(button6)
        buttons.add(button7)
        buttons.add(button8)
        buttons.add(button9)

        for(button in buttons){
            button.isEnabled = true
            button.setBackgroundResource(0)
        }
    }

    val diagonal1List = intArrayOf(1,5,9)
    val diagonal2List = intArrayOf(3,5,7)
    fun checkIfWin(playerSelections:ArrayList<Int>): Pair<Int,Int>{

        var rowSum = intArrayOf(0,0,0)
        var columnSum = intArrayOf(0,0,0)
        var diagonal1 = 0
        var diagonal2 = 0

        var startIndex:Int = -1
        var endIndex:Int = -1

        for (i in playerSelections){
            //Check row
            if(++rowSum[(i-1)/3] == 3){
                startIndex = ((i-1)/3)+1
                endIndex = startIndex + 2
                break
            }

            //Check column
            if(++columnSum[(i-1)%3] == 3){
                startIndex = ((i-1)%3)+1
                endIndex = startIndex + 6
                break
            }

            //Check upperleft to lower right diagonal
            if(i in diagonal1List && ++diagonal1 == 3){
                startIndex = diagonal1List[0]
                endIndex = diagonal1List[2]
                break
            }

            //Check upper right to lower left diagonal
            if(i in diagonal2List && ++diagonal2 == 3){
                startIndex = diagonal2List[0]
                endIndex = diagonal2List[2]
                break
            }
        }

        Log.d("winCheck", "start index: $startIndex, end index: $endIndex")

        return Pair(startIndex, endIndex)
    }
}
