package main

import adapters.TestAdapterBasic
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.haxhiu.demo.R
import data.addItemAt
import java.util.*

class MultipleDataActivity : AppCompatActivity(R.layout.recycler_layout) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupAdapter()
    }

    private fun setupAdapter() {
        // We initialize our TestAdapterBasic we created that extends HAdapterBasic
        val myAdapter = TestAdapterBasic()

        // We set our HAdapter to our RecyclerView
        with(findViewById<RecyclerView>(R.id.recyclerView)) {
            adapter = myAdapter
        }

        // For every 200ms we add a new random UUID to our adapter with position 0, this is so we can test it's performance
        Timer().schedule(object : TimerTask() {
            override fun run() {
                Handler(Looper.getMainLooper()).post {
                    myAdapter.addItemAt(UUID.randomUUID().toString(), 0)
                    // We print item count of our adapter
                    println("++Count: ${myAdapter.itemCount}")
                }
            }
        }, 500, 200)
    }
}