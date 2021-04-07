package main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import main.hasItemAt

open class HAdapterBasic<T: Any> (var itemLayoutId: Int,
                                  compareItems: (old: T, new:T) -> Boolean,
                                  var onItemClickListener: ((adapter: HAdapterBasic<T>, data: T, position: Int) -> Unit)? = null,
                                  var onBindViewHolder: ((holder: RecyclerView.ViewHolder, data: T) -> Unit)? = null) : HAdapterParent<T>(compareItems) {

    constructor(builder: HAdapterBasicBuilder<T>) : this (builder.getItemLayoutId(), builder.getCompareItems(), builder.getItemOnClickListener(), builder.getItemOnBindViewHolder())

    override fun getItemViewType(position: Int): Int = itemLayoutId

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false)) { position ->
            onItemClickListener?.let { listener ->
                hasItemAt(position)?.let { data ->
                    listener.invoke(this, data, position)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onBindViewHolder?.let { binder ->
            hasItemAt(position)?.let { data ->
                binder(holder, data)
            }
        }
    }

    inner class ViewHolder(itemView: View, itemPosition: (position: Int) -> Unit) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                itemPosition.invoke(adapterPosition)
            }
        }
    }
}