package adapters

import android.content.Context
import android.widget.TextView
import android.widget.Toast
import com.haxhiu.demo.R
import main.HAdapterBasic
import data.setItemAt

class TestAdapterBasic : HAdapterBasic<String>(R.layout.adapter_item_layout, { old, new -> old.length == new.length },
    { adapter, data, position ->
        // We set item click listener here. We can also provide `null` so it doesn't set a click listener
        adapter.setItemAt("This item has been clicked!", position)
    }){

    override fun onBind(holder: HViewHolder, data: String) {
        holder.itemView.findViewById<TextView>(R.id.textView).text = data
    }
}