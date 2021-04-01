package main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class HAdapter<T: Any> (private val layoutId: Int,
                                 private val compareItems: (old: T, new:T) -> Boolean,
                                 private val compareContents: (old:T, new:T) -> Boolean,
                                 private val onItemClicked: ((adapter: HAdapter<T>, data: T, position: Int) -> Unit)? = null) : RecyclerView.Adapter<HAdapter.HViewHolder>() {

    abstract fun onBind(holder: HViewHolder, data: T)

    override fun getItemViewType(position: Int): Int = layoutId

    override fun getItemCount(): Int = differ.currentList.size

    val differ: AsyncListDiffer<T> = AsyncListDiffer(this@HAdapter, object : DiffUtil.ItemCallback<T>() {

        override fun areItemsTheSame(oldItem: T, newItem: T) = compareItems(oldItem, newItem)

        override fun areContentsTheSame(oldItem: T, newItem: T) = compareContents(oldItem, newItem)
    })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HViewHolder {
        return HViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false)) {
            onItemClicked?.invoke(this, getItemAt(it), it)
        }
    }

    override fun onBindViewHolder(holder: HViewHolder, position: Int) {
        hasItemAt(position)?.let {
            onBind(holder, getItemAt(position))
        }
    }

    class HViewHolder(itemView: View, itemPosition: (position: Int) -> Unit) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener { itemPosition(adapterPosition) }
        }
    }
}