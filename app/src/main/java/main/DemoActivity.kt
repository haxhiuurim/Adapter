package main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.haxhiu.demo.R
import java.util.*

class DemoActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupAdapter()
    }

    private fun setupAdapter() {
        // We initialize our HAdapter with type of data [String] and layout of [R.layout.adapter_item_numbers]
        val myAdapter = object : HAdapter<String>(R.layout.adapter_item_numbers,
            { old, new -> old.length == new.length },
            { old, new -> old == new },
            { adapter, data, position ->
                // We change item in position 1 on item clicked
                adapter.setItemAt("This data has changed on item click! Previously our data text was: $data", position)
            }
        ) {
            override fun onBind(holder: HViewHolder, data: String) {
                holder.itemView.findViewById<TextView>(R.id.textView).text = data
            }
        }

        // We set our HAdapter to our RecyclerView
        with(findViewById<RecyclerView>(R.id.recyclerView)) {
            adapter = myAdapter
        }

        // We set a list of items to our adapter
        myAdapter.setItems(listOf("Data #1", "Data #2", "Data #3"))

        // With 1 second delay, we change item at position 0 text
        Handler(Looper.getMainLooper()).postDelayed({ myAdapter.setItemAt("This data has been changed!", 0) }, 1000)

        // With 2 seconds delay, we try to remove an item with position that does not exist in our items list
        Handler(Looper.getMainLooper()).postDelayed({
            if(myAdapter.removeItemAt(5)) {
                Toast.makeText(this@DemoActivity, "Item removed on position 5", Toast.LENGTH_SHORT).show()
            }
        }, 2000)

        // With 3 seconds delay, we try to remove an item that exist on our items list which in this case will toast 'Item removed on position 0'
        Handler(Looper.getMainLooper()).postDelayed({
            if(myAdapter.removeItemAt(0)) {
                Toast.makeText(this@DemoActivity, "Item removed on position 0", Toast.LENGTH_SHORT).show()
            }
        }, 3000)
    }
}