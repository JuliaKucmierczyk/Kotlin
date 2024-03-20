package pl.wsei.pam.lab03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.gridlayout.widget.GridLayout
import pl.wsei.pam.lab01.R
import java.util.Timer
import kotlin.concurrent.schedule

class Lab03Activity : AppCompatActivity() {
    private lateinit var mBoard: GridLayout
    private lateinit var mBoardModel: MemoryBoardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab03)

        val columns = intent.getIntExtra("columns", 3)
        val rows = intent.getIntExtra("rows", 3)

        mBoard = findViewById(R.id.grid_layout_id)
        mBoard.columnCount = columns
        mBoard.rowCount = rows

        mBoardModel = MemoryBoardView(mBoard,columns, rows)
        mBoardModel.setOnGameChangeListener { e ->
            run {
                when (e.state) {
                    GameStates.Matching -> {
                        e.tiles.forEach { tile ->
                            tile.revealed = true
                        }
                    }
                    GameStates.Match -> {
                        e.tiles.forEach { tile ->
                            tile.revealed = true
                        }
                    }
                    GameStates.NoMatch -> {
                        e.tiles.forEach { tile ->
                            tile.revealed = true
                        }
                        // Opóźnienie zmiany stanu karty
                        Timer().schedule(2000) {
                            runOnUiThread {
                                e.tiles.forEach { tile ->
                                    tile.revealed = false
                                }
                            }
                        }
                    }
                    GameStates.Finished -> {
                        Toast.makeText(this, "Game finished", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}