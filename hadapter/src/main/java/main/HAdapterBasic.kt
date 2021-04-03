package main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import data.getItemAt
import data.hasItemAt

abstract class HAdapterBasic<T: Any> (private val layoutId: Int,
                                      private val compareItems: (old: T, new:T) -> Boolean,
                                      private val onItemClicked: ((adapter: HAdapterBasic<T>, data: T, position: Int) -> Unit)? = null) : RecyclerView.Adapter<HAdapterBasic.HViewHolder>() {

    open fun onBind(holder: HViewHolder, data: T) {}

    override fun getItemViewType(position: Int): Int = layoutId

    override fun getItemCount(): Int = differ.currentList.size

    @SuppressLint("DiffUtilEquals")
    val differ: AsyncListDiffer<T> = AsyncListDiffer(this@HAdapterBasic, object : DiffUtil.ItemCallback<T>() {

        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = compareItems(oldItem, newItem)

        override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
    })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HViewHolder {
        return HViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false)) { position ->
            onItemClicked?.let { listener ->
                hasItemAt(position)?.let { listener.invoke(this, getItemAt(position), position) }
            }
        }
    }

    override fun onBindViewHolder(holder: HViewHolder, position: Int) {
        hasItemAt(position)?.let { onBind(holder, getItemAt(position)) }
    }

    class HViewHolder(itemView: View, itemPosition: (position: Int) -> Unit) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener { itemPosition(adapterPosition) }
        }
    }
}