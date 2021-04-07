package main

import androidx.recyclerview.widget.RecyclerView

class HAdapterBuilder<T : Any> {

    private var itemLayoutId: Int = -1
    private var headerLayoutId: Int = -1
    private var footerLayoutId: Int = -1
    private var headerOnItemClickListener: ((adapter: HAdapterParent<T>) -> Unit)? = null
    private var itemOnClickListener: ((adapter: HAdapterParent<T>, data: T, position: Int) -> Unit)? = null
    private var footerOnItemClickListener: ((adapter: HAdapterParent<T>) -> Unit)? = null
    private var headerOnBindViewHolder: ((holder: RecyclerView.ViewHolder) -> Unit)? = null
    private var itemOnBindViewHolder:((holder: RecyclerView.ViewHolder, data: T) -> Unit)? = null
    private var footerOBindViewHolder: ((holder: RecyclerView.ViewHolder) -> Unit)? = null
    private lateinit var compareItems: (old: T, new:T) -> Boolean

    fun getHeaderLayoutId() = headerLayoutId

    fun setHeaderLayoutId(headerLayoutId: Int) : HAdapterBuilder<T> {
        this.headerLayoutId = headerLayoutId
        return this
    }

    fun getItemLayoutId() = itemLayoutId

    fun setItemLayoutId(itemLayoutId: Int) : HAdapterBuilder<T> {
        this.itemLayoutId = itemLayoutId
        return this
    }

    fun getFooterLayoutId() = footerLayoutId

    fun setFooterLayoutId(footerLayoutId: Int) : HAdapterBuilder<T> {
        this.footerLayoutId = footerLayoutId
        return this
    }

    fun getHeaderOnItemClickListener() = headerOnItemClickListener

    fun setHeaderOnItemClickListener(HeaderOnItemClickListener: ((adapter: HAdapterParent<T>) -> Unit)? = null) : HAdapterBuilder<T> {
        this.headerOnItemClickListener = HeaderOnItemClickListener
        return this
    }

    fun getItemOnClickListener() = itemOnClickListener

    fun setItemOnClickListener(itemOnClickListener: ((adapter: HAdapterParent<T>, data: T, position: Int) -> Unit)? = null) : HAdapterBuilder<T> {
        this.itemOnClickListener = itemOnClickListener
        return this
    }

    fun getFooterOnItemClickListener() = footerOnItemClickListener

    fun setFooterOnItemClickListener(footerOnItemClickListener: ((adapter: HAdapterParent<T>) -> Unit)? = null) : HAdapterBuilder<T> {
        this.footerOnItemClickListener = footerOnItemClickListener
        return this
    }

    fun getHeaderOnBindViewHolder() = headerOnBindViewHolder

    fun setHeaderOnBindViewHolder(headerOnBindViewHolder:((holder: RecyclerView.ViewHolder) -> Unit)? = null) : HAdapterBuilder<T> {
        this.headerOnBindViewHolder = headerOnBindViewHolder
        return this
    }

    fun getItemOnBindViewHolder() = itemOnBindViewHolder

    fun setItemOnBindViewHolder(itemOnBindViewHolder:((holder: RecyclerView.ViewHolder, data: T) -> Unit)? = null) : HAdapterBuilder<T> {
        this.itemOnBindViewHolder = itemOnBindViewHolder
        return this
    }

    fun getFooterOnBindViewHolder() = footerOBindViewHolder

    fun setFooterOnBindViewHolder(footerOBindViewHolder:((holder: RecyclerView.ViewHolder) -> Unit)? = null) : HAdapterBuilder<T> {
        this.footerOBindViewHolder = footerOBindViewHolder
        return this
    }

    fun getCompareItems() = compareItems

    fun setCompareItems(compareItems: (old: T, new:T) -> Boolean) : HAdapterBuilder<T> {
        this.compareItems = compareItems
        return this
    }

    @Throws(HAdapterException::class)
    fun build(): HAdapter<T> {
        if (itemLayoutId == -1)
            throw HAdapterException("setLayoutId() is not added on builder! Please make sure to add it before using build() method")

        if (!(::compareItems.isInitialized))
            throw HAdapterException("setCompareItems() is not added on builder! Please make sure to add it before using build() method")

        return HAdapter(this)
    }
}