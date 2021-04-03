package basic

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import helpers.getItemAt
import helpers.hasItemAt

open class HAdapterBasic<T: Any> (var layoutId: Int,
                                  var compareItems: (old: T, new:T) -> Boolean,
                                  var onItemClickListener: ((adapter: HAdapterBasic<T>, data: T, position: Int) -> Unit)? = null,
                                  var onBindViewHolder: ((holder: ViewHolder, data: T) -> Unit)? = null)

    : RecyclerView.Adapter<HAdapterBasic.ViewHolder>() {

    constructor(builder: HAdapterBasicBuilder<T>)
            : this (builder.getLayoutId(), builder.getCompareItems(), builder.getOnItemClickListener(), builder.getOnBindViewHolder())

    override fun getItemViewType(position: Int): Int = layoutId

    override fun getItemCount(): Int = differ.currentList.size

    @SuppressLint("DiffUtilEquals")
    val differ: AsyncListDiffer<T> = AsyncListDiffer(this, object : DiffUtil.ItemCallback<T>() {

        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = compareItems(oldItem, newItem)

        override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
    })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false)) { position ->
            onItemClickListener?.let { listener ->
                hasItemAt(position)?.let { listener.invoke(this, getItemAt(position), position) }
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        onBindViewHolder?.let { binder ->
            hasItemAt(position)?.let { binder(holder, getItemAt(position)) }
        }
    }

    class ViewHolder(itemView: View, itemPosition: (position: Int) -> Unit) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener { itemPosition(adapterPosition) }
        }
    }
}