package adapters

import android.widget.TextView
import com.haxhiu.demo.R
import main.HAdapterBasic

/**
 * We've created a TestAdapterBasic extending HAdapterBasic
 *
 * When extending HAdapterBasic we must use the following:
 *  1) Adapter layout
 *  2) DiffUtil compare items
 *  3) Item click (Optional)
 *  4) onBind()
 */
class TestAdapterBasic : HAdapterBasic<String>(R.layout.adapter_item_layout, { old, new -> old.length == new.length }, null) {

    override fun onBind(holder: HViewHolder, data: String) {
        holder.itemView.findViewById<TextView>(R.id.textView).text = data
    }
}