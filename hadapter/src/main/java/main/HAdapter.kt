package main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import main.*
import main.hasItemAt

open class HAdapter<T : Any>(private var headerLayoutId: Int = -1,
                             private var itemLayoutId: Int,
                             private var footerLayoutId: Int = -1,
                             compareItems: (old: T, new: T) -> Boolean,
                             private var headerOnItemClickListener: ((adapter: HAdapterParent<T>) -> Unit)? = null,
                             private var itemOnClickListener: ((adapter: HAdapterParent<T>, data: T, position: Int) -> Unit)? = null,
                             private var footerOnItemClickListener: ((adapter: HAdapterParent<T>) -> Unit)? = null,
                             private var headerOnBindViewHolder: ((holder: RecyclerView.ViewHolder) -> Unit)? = null,
                             private var itemOnBindViewHolder: ((holder: RecyclerView.ViewHolder, data: T) -> Unit)? = null,
                             private var footerOnBindViewHolder: ((holder: RecyclerView.ViewHolder) -> Unit)? = null) : HAdapterParent<T>(compareItems) {

    init {
        if(headerLayoutId != -1) {
            super.hasHeader = true
        }

        if(footerLayoutId != -1) {
            super.hasFooter = true
        }
    }

    companion object {

        const val TYPE_HEADER = 6684
        const val TYPE_FOOTER = 1145
        const val TYPE_ITEM = 3333
    }

    constructor(builder: HAdapterBuilder<T>) :
            this(builder.getHeaderLayoutId(), builder.getItemLayoutId(), builder.getFooterLayoutId(),
                    builder.getCompareItems(), builder.getHeaderOnItemClickListener(), builder.getItemOnClickListener(),
                    builder.getFooterOnItemClickListener(), builder.getHeaderOnBindViewHolder(), builder.getItemOnBindViewHolder(),
                    builder.getFooterOnBindViewHolder())

    override fun getItemCount(): Int {
        return getModifiableList(true).size
    }

    override fun getItemViewType(position: Int): Int {
        return if (headerLayoutId != -1 && position == 0 && getModifiableList(true)[position] == null) {
            TYPE_HEADER
        } else if (footerLayoutId != -1 && position == itemCount - 1 && getModifiableList(true)[position] == null) {
            TYPE_FOOTER
        } else {
            TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                HeaderViewHolder(LayoutInflater.from(parent.context).inflate(headerLayoutId, parent, false)) {
                    headerOnItemClickListener?.invoke(this)
                }
            }
            TYPE_FOOTER -> {
                FooterViewHolder(LayoutInflater.from(parent.context).inflate(footerLayoutId, parent, false)) {
                    footerOnItemClickListener?.invoke(this)
                }
            }
            else ->  {
                ViewHolder(LayoutInflater.from(parent.context).inflate(itemLayoutId, parent, false)) { position ->
                    itemOnClickListener?.let { listener ->
                        hasItemAt(position, true)?.let { data ->
                            listener.invoke(this, data, position)
                        }
                    }
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_HEADER -> headerOnBindViewHolder?.invoke(holder as HeaderViewHolder)
            TYPE_FOOTER -> footerOnBindViewHolder?.invoke(holder as FooterViewHolder)
            else -> itemOnBindViewHolder?.let { delegate -> hasItemAt(position, true)?.let { data -> delegate.invoke(holder as HAdapter<*>.ViewHolder, data) } }
        }
    }

    class HeaderViewHolder(itemView: View, clickDelegate: () -> Unit) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                clickDelegate.invoke()
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

    class FooterViewHolder(itemView: View, clickDelegate: () -> Unit) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                clickDelegate.invoke()
            }
        }
    }
}