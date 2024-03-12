package pl.wsei.pam.lab02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import pl.wsei.pam.lab01.R

class Lab02Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab02)

        val buttons = listOf<Button>(
            findViewById(R.id.main_6_6_board),
            findViewById(R.id.main_4_4_board),
            findViewById(R.id.main_4_3_board),
            findViewById(R.id.main_3_2_board)
        )

        buttons.forEach { button ->
            button.setOnClickListener { onButtonClick(button) }
        }
    }

    private fun onButtonClick(button: Button) {
        val boardSizeText = button.text.toString()
        val tag: String? = button.tag as? String
        val tokens: List<String>? = tag?.split(" ")
        val rows = tokens?.get(0)?.toInt()
        val columns = tokens?.get(1)?.toInt()

        if (rows != null && columns != null) {
            Toast.makeText(
                this,
                "Wybrano planszę o rozmiarze: $boardSizeText ($rows x $columns)",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}