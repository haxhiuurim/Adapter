package main

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haxhiu.demo.R
import main.setItemAt
import main.setItems
import kotlinx.android.synthetic.main.adapter_item_layout.view.*
import kotlinx.android.synthetic.main.recycler_layout.*
import java.util.*

class HeaderFooterSampleActivity: AppCompatActivity(R.layout.recycler_layout) {

    private val tag: String = "SimpleDataWithHeaderActivity"
    private lateinit var mySampleAdapter: HAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupAdapter()
        addItems()
    }

    /* Methods below initialize our adapter with DiffUtil using HAdapterBuilder  */

    /**
     * Creates instance of [HAdapter] using [HAdapterBuilder] and we set it as adapter to our [recyclerView]
     */
    private fun setupAdapter() {
        println("$tag: Initializing adapter")

        mySampleAdapter = HAdapterBuilder<String>()
                .setCompareItems{ old, new -> compareItems(old, new)}
                .setItemLayoutId(R.layout.adapter_item_layout)
                .setItemOnBindViewHolder { holder, data -> itemOnBindViewHolder(holder, data) }
                .setItemOnClickListener { adapter, data, position -> onItemClicked(adapter, data, position) }
                .setHeaderLayoutId(R.layout.adapter_header_layout)
                .setHeaderOnBindViewHolder { headerOnBindViewHolder(it) }
                .setHeaderOnItemClickListener { headerOnItemClicked(it) }
                .setFooterLayoutId(R.layout.adapter_header_layout)
                .setFooterOnBindViewHolder { footerOnBindViewHolder(it) }
                .setFooterOnItemClickListener { footerOnItemClicked(it) }
                .build()

        with(recyclerView) {
            layoutManager = LinearLayoutManager(this@HeaderFooterSampleActivity)
            adapter = mySampleAdapter
            itemAnimator = null
        }
    }

    /**
     * This method is used when creating [HAdapter] for DiffUtil comparison
     */
    private fun compareItems(old: String, new: String) : Boolean = old.toCharArray().last() == new.toCharArray().last()

    /**
     * This method is used to bind header view when creating [HAdapter]
     */
    private fun headerOnBindViewHolder(holder: RecyclerView.ViewHolder) {
        holder.itemView.findViewById<TextView>(R.id.textView).text = "Header View"
    }

    /**
     * This method is used to bind item views when creating [HAdapter]
     */
    private fun itemOnBindViewHolder(holder: RecyclerView.ViewHolder, data: String) {
        holder.itemView.findViewById<TextView>(R.id.textView).text = data
    }

    /**
     * This method is used to bind footer view when creating [HAdapter]
     */
    private fun footerOnBindViewHolder(holder: RecyclerView.ViewHolder) {
        holder.itemView.findViewById<TextView>(R.id.textView).text = "Footer View"
    }

    /**
     * This method is used when creating [HAdapter] for when header is clicked
     */
    private fun headerOnItemClicked(adapter: HAdapterParent<String>) {
        Toast.makeText(this, "Header is clicked", Toast.LENGTH_SHORT).show()
    }

    /**
     * This method is used when creating [HAdapter] for when an item is clicked
     *
     * Changes item's data to random UUID text.
     */
    private fun onItemClicked(adapter: HAdapterParent<String>, data: String, position: Int) {
        println("$tag: Item is clicked on position: $position")
        adapter.setItemAt(position, UUID.randomUUID().toString())
    }

    /**
     * This method is used when creating [HAdapter] for when footer is clicked
     */
    private fun footerOnItemClicked(adapter: HAdapterParent<String>) {
        Toast.makeText(this, "Footer is clicked", Toast.LENGTH_SHORT).show()
    }

    /* Methods below are used to test adapter on different cases */

    /**
     * Adds dummy data to our adapter
     */
    private fun addItems() {
        println("$tag: Adding dummy data items")
        mySampleAdapter.setItems(arrayListOf("Data #1", "Data #2", "Data #3"))
    }
}