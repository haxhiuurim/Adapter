package main

import androidx.recyclerview.widget.RecyclerView

class HAdapterBasicBuilder<T : Any> {

    private var itemLayoutId: Int = -1
    private var itemOnClickListener: ((adapter: HAdapterParent<T>, data: T, position: Int) -> Unit)? = null
    private var itemOnBindViewHolder:((holder: RecyclerView.ViewHolder, data: T) -> Unit)? = null
    private lateinit var compareItems: (old: T, new:T) -> Boolean

    fun getItemLayoutId() = itemLayoutId

    fun setItemLayoutId(itemLayoutId: Int) : HAdapterBasicBuilder<T> {
        this.itemLayoutId = itemLayoutId
        return this
    }

    fun getItemOnClickListener() = itemOnClickListener

    fun setItemOnClickListener(itemOnClickListener: ((adapter: HAdapterParent<T>, data: T, position: Int) -> Unit)? = null) : HAdapterBasicBuilder<T> {
        this.itemOnClickListener = itemOnClickListener
        return this
    }

    fun getItemOnBindViewHolder() = itemOnBindViewHolder

    fun setItemOnBindViewHolder(itemOnBindViewHolder:((holder: RecyclerView.ViewHolder, data: T) -> Unit)? = null) : HAdapterBasicBuilder<T> {
        this.itemOnBindViewHolder = itemOnBindViewHolder
        return this
    }

    fun getCompareItems() = compareItems

    fun setCompareItems(compareItems: (old: T, new:T) -> Boolean) : HAdapterBasicBuilder<T> {
        this.compareItems = compareItems
        return this
    }

    @Throws(HAdapterException::class)
    fun build(): HAdapterBasic<T> {
        if (itemLayoutId == -1)
            throw HAdapterException("setLayoutId() is not added on builder! Please make sure to add it before using build() method")

        if (!(::compareItems.isInitialized))
            throw HAdapterException("setCompareItems() is not added on builder! Please make sure to add it before using build() method")

        return HAdapterBasic(this)
    }
}