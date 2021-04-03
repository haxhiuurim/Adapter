package basic

import helpers.HAdapterException

class HAdapterBasicBuilder<T: Any> {

    private var layoutId: Int = -1
    private var onItemClickListener: ((adapter: HAdapterBasic<T>, data: T, position: Int) -> Unit)? = null
    private var onBindViewHolder:((holder: HAdapterBasic.ViewHolder, data: T) -> Unit)? = null
    private lateinit var compareItems: (old: T, new:T) -> Boolean

    fun getLayoutId() = layoutId

    fun setLayoutId(layoutId: Int) : HAdapterBasicBuilder<T> {
        this.layoutId = layoutId
        return this
    }

    fun getOnItemClickListener() = onItemClickListener

    fun setOnItemClickListener(onItemClickListener: ((adapter: HAdapterBasic<T>, data: T, position: Int) -> Unit)? = null) : HAdapterBasicBuilder<T> {
        this.onItemClickListener = onItemClickListener
        return this
    }

    fun getOnBindViewHolder() = onBindViewHolder

    fun setOnBindViewHolder(onBind:((holder: HAdapterBasic.ViewHolder, data: T) -> Unit)? = null) : HAdapterBasicBuilder<T> {
        this.onBindViewHolder = onBind
        return this
    }

    fun getCompareItems() = compareItems

    fun setCompareItems(compareItems: (old: T, new:T) -> Boolean) : HAdapterBasicBuilder<T> {
        this.compareItems = compareItems
        return this
    }

    @Throws(HAdapterException::class)
    fun build() : HAdapterBasic<T> {
        if(layoutId == -1)
            throw HAdapterException("setLayoutId() is not added on builder! Please make sure to add it before using build() method")

        if(!(::compareItems.isInitialized))
            throw HAdapterException("setCompareItems() is not added on builder! Please make sure to add it before using build() method")

        return HAdapterBasic(this)
    }
}