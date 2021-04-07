package main

import adapters.TestAdapterBasic
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.haxhiu.demo.R
import main.addItemAt
import java.util.*

class MultipleDataSampleActivity : AppCompatActivity(R.layout.recycler_layout) {

    private val tag: String = "MultipleDataActivity"
    private lateinit var testAdapterBasic: TestAdapterBasic
    private lateinit var timer: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupAdapter()
        generateDummyData(200)
    }

    /**
     * Initializes TestAdapterBasic and sets it to our recyclerView
     */
    private fun setupAdapter() {
        println("$tag: Initializing TestAdapterBasic")
        testAdapterBasic = TestAdapterBasic()

        // We set our HAdapter to our RecyclerView
        with(findViewById<RecyclerView>(R.id.recyclerView)) {
            adapter = testAdapterBasic
        }
    }

    /**
     * Adds random UUID string on position 0 for every [period] given in params and prints items count
     */
    private fun generateDummyData(period: Long) {
        println("$tag: Generating dummy UUID texts every $period ms")
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                Handler(Looper.getMainLooper()).post {
                    testAdapterBasic.addItemAt(0, UUID.randomUUID().toString())
                    println("$tag: Added new item. Items count: ${testAdapterBasic.itemCount}")
                }
            }
        }, 500, period)
    }

    override fun onDestroy() {
        println("$tag: Destroyed dummy UUID texts generator")
        timer.cancel()
        super.onDestroy()
    }
}