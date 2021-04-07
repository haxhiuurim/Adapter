package main

import android.annotation.SuppressLint
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class HAdapterParent<T: Any>(var compareItems: (old: T, new: T) -> Boolean) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    internal var hasHeader: Boolean = false
    internal var hasFooter: Boolean = false

    @SuppressLint("DiffUtilEquals")
    val differ: AsyncListDiffer<T> = AsyncListDiffer(this, object : DiffUtil.ItemCallback<T>() {

        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = compareItems(oldItem, newItem)

        override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
    })

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}