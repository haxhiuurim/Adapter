package adapters

import advanced.HAdapter
import android.widget.TextView
import com.haxhiu.demo.R
import basic.HAdapterBasic

/**
 * We've created a TestAdapterBasic extending HAdapterBasic
 *
 * When extending HAdapterBasic we must set the following:
 *  1) Adapter layout (Required)
 *  2) DiffUtil compare items (Required)
 *  3) Item click (Optional ~ You can extend HAdapterBasic without setting a click listener for items)
 *  4) onBind() (Optional ~ You can extend HAdapterBasic without overriding onBind method)
 */
class TestAdapterBasic : HAdapter<String>(
    layoutId = R.layout.adapter_item_layout,
    compareItems = { old, new -> old.length == new.length },
    onItemClickListener = null,
    onBindViewHolder = { holder: ViewHolder, data: String ->
        holder.itemView.findViewById<TextView>(R.id.textView).text = data
    })
