package main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.haxhiu.demo.R
import data.removeItemAt
import data.setItemAt
import data.setItems
import data.sortItems

class SimpleDataActivity : AppCompatActivity(R.layout.recycler_layout) {

    private val tag: String = "SimpleDataActivity"
    private lateinit var basicAdapter: HAdapterBasic<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupAdapter()
        addItems()
        setItemAt("This data has been changed!", 0)
        removeItemAt(33)
        removeItemAt(2)
        sortItems()
    }

    /* Methods below initialize our adapter with DiffUtil and ItemClick (Which can be set to null) */

    /**
     * Creates instance of [HAdapterBasic] and sets it as adapter of our recyclerView
     */
    private fun setupAdapter() {
        println("$tag: Initializing adapter")
        basicAdapter = object : HAdapterBasic<String>(
            R.layout.adapter_item_layout,
            { old, new -> compareItems(old, new) },
            { adapter, data, position -> onItemClicked(adapter, data, position) }) {
                override fun onBind(holder: HViewHolder, data: String) {
                    holder.itemView.findViewById<TextView>(R.id.textView).text = data
                }
        }

        with(findViewById<RecyclerView>(R.id.recyclerView)) {
            adapter = basicAdapter
        }
    }

    /**
     * This method is used when creating [HAdapterBasic] for DiffUtil comparison
     */
    private fun compareItems(old: String, new: String) : Boolean = old.length == new.length

    /**
     * This method is used when creating [HAdapterBasic] when an item is clicked
     *
     * Changes item's data to 'This data has changed on item click'
     */
    private fun onItemClicked(adapter: HAdapterBasic<String>, data: String, position: Int) {
        println("$tag: Item is clicked on position: $position")
        adapter.setItemAt("This data has changed on item click!", position)
    }

    /* Methods below are used to test adapter on different cases */

    /**
     * Adds dummy data to our adapter
     */
    private fun addItems() {
        println("$tag: Adding dummy data items")
        basicAdapter.setItems(listOf("Data #1", "Data #2", "Data #3"))
    }

    /**
     * After 1 second, Sets item [data] with given [position]
     */
    private fun setItemAt(data: String, position: Int) {
        Handler(Looper.getMainLooper()).postDelayed({
            println("$tag: Replacing data at position: $position")
            basicAdapter.setItemAt(data, position)
        }, 1000)
    }

    /**
     * After 2 seconds, removes item with [position] and in case when item is removed toasts 'Item removed on [position]'
     */
    private fun removeItemAt(position: Int) {
        Handler(Looper.getMainLooper()).postDelayed({
            println("$tag: Removing data at position: $position")
            if(basicAdapter.removeItemAt(position)) {
                println("$tag: Removed item at position: $position")
                Toast.makeText(this@SimpleDataActivity, "Item removed on position $position", Toast.LENGTH_SHORT).show()
            } else {
                println("$tag: Item at $position doesn't exist")
            }
        }, 2000)
    }

    /**
     * After 3 seconds, sorts all items based on length
     */
    private fun sortItems() {
        Handler(Looper.getMainLooper()).postDelayed({
            println("$tag: Sorting items by length")
            basicAdapter.sortItems { it.length }
        }, 3000)
    }
}