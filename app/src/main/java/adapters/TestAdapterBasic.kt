package adapters

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.haxhiu.demo.R
import main.HAdapterBasic

/**
 * We've created a TestAdapterBasic extending HAdapterBasic
 *
 * When extending HAdapterBasic we must set the following:
 *  1) Adapter item layout (Required)
 *  2) DiffUtil compare items (Required)
 *  3) Item click listener (Optional ~ You can extend HAdapterBasic without setting a click listener for items or just passing null value)
 *  4) onBindViewHolder() (Optional ~ You can extend HAdapterBasic without overriding onBind method or just passing null value)
 */
class TestAdapterBasic : HAdapterBasic<String>(
        itemLayoutId = R.layout.adapter_item_layout,
        compareItems = { old, new -> old.length == new.length },
        onItemClickListener = null,
        onBindViewHolder = { holder: RecyclerView.ViewHolder, data: String ->
            holder.itemView.findViewById<TextView>(R.id.textView).text = data
        })
