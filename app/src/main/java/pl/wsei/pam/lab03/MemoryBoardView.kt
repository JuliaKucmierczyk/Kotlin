package pl.wsei.pam.lab03

import android.view.Gravity
import android.view.View
import android.widget.ImageButton
import androidx.gridlayout.widget.GridLayout
import pl.wsei.pam.lab01.R
import java.util.Stack


class MemoryBoardView(
    val gridLayout: GridLayout,
    private val cols: Int,
    private val rows: Int
) {
    private val tiles: MutableMap<String, Tile> = mutableMapOf()
    private val icons: List<Int> = listOf(
        R.drawable.baseline_rocket_launch_24,
        R.drawable.baseline_rowing_24,
        R.drawable.baseline_sailing_24,
        R.drawable.baseline_theater_comedy_24,
        R.drawable.baseline_sports_esports_24,
        R.drawable.baseline_stroller_24,
        R.drawable.baseline_sports_tennis_24,
        R.drawable.baseline_time_to_leave_24,
        R.drawable.baseline_pest_control_rodent_24,
        R.drawable.baseline_tag_faces_24,
        R.drawable.baseline_two_wheeler_24,
        R.drawable.baseline_skateboarding_24,
        R.drawable.baseline_sick_24,
        R.drawable.baseline_public_24,
        R.drawable.baseline_sports_bar_24,
        R.drawable.baseline_whatshot_24,
        R.drawable.baseline_wb_cloudy_24,
        R.drawable.baseline_surfing_24,
        R.drawable.baseline_sports_mma_24
    )
    init {
        val shuffledIcons: MutableList<Int> = mutableListOf<Int>().also {
            it.addAll(icons.subList(0, cols * rows / 2))
            it.addAll(icons.subList(0, cols * rows / 2))
            it.shuffle()
        }


        // tu umieść kod pętli tworzący wszystkie karty, który jest obecnie
        // w aktywności Lab03Activity
        for (row in 0 until rows) {
            for (col in 0 until cols) {

                val btn = ImageButton(gridLayout.context).also {
                    it.tag = "${row}x${col}"
                    val layoutParams = GridLayout.LayoutParams()
                    it.setImageResource(R.drawable.baseline_texture_24)
                    layoutParams.width = 0
                    layoutParams.height = 0
                    layoutParams.setGravity(Gravity.CENTER)
                    layoutParams.columnSpec = GridLayout.spec(col, 1, 1f)
                    layoutParams.rowSpec = GridLayout.spec(row, 1, 1f)
                    it.layoutParams = layoutParams}
                gridLayout.addView(btn)
                addTile(btn, shuffledIcons.removeAt(0))
            }
        }
    }
    private val deckResource: Int = R.drawable.baseline_texture_24
    private var onGameChangeStateListener: (MemoryGameEvent) -> Unit = { (e) -> }
    private val matchedPair: Stack<Tile> = Stack()
    private val logic: MemoryGameLogic = MemoryGameLogic(cols * rows / 2)

    private fun onClickTile(v: View) {
        val tile = tiles[v.tag]
        matchedPair.push(tile)
        val matchResult = logic.process {
            tile?.tileResource?:-1
        }
        onGameChangeStateListener(MemoryGameEvent(matchedPair.toList(), matchResult))
        if (matchResult != GameStates.Matching) {
            matchedPair.clear()
        }
    }

    fun setOnGameChangeListener(listener: (event: MemoryGameEvent) -> Unit) {
        onGameChangeStateListener = listener
    }

    private fun addTile(button: ImageButton, resourceImage: Int) {
        button.setOnClickListener(::onClickTile)
        val tile = Tile(button, resourceImage, deckResource)
        tiles[button.tag.toString()] = tile
    }


}