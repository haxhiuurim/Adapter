package advanced

import basic.HAdapterBasic
import helpers.HAdapterException

class HAdapterBuilder<T: Any> {

    private var layoutId: Int = -1
    private var onItemClicked: ((adapter: HAdapterBasic<T>, data: T, position: Int) -> Unit)? = null
    private var onBindViewHolder: ((holder: HAdapterBasic.ViewHolder, data: T) -> Unit)? = null
    private lateinit var compareItems: (old: T, new:T) -> Boolean

    fun getLayoutId() = layoutId

    fun setLayoutId(layoutId: Int) : HAdapterBuilder<T> {
        this.layoutId = layoutId
        return this
    }

    fun getOnItemClickListener() = onItemClicked

    fun setOnItemClickListener(onItemClicked: ((adapter: HAdapterBasic<T>, data: T, position: Int) -> Unit)? = null) : HAdapterBuilder<T> {
        this.onItemClicked = onItemClicked
        return this
    }

    fun getOnBindViewHolder() = onBindViewHolder

    fun setOnBindViewHolder(onBind:((holder: HAdapterBasic.ViewHolder, data: T) -> Unit)? = null) : HAdapterBuilder<T> {
        this.onBindViewHolder = onBind
        return this
    }

    fun getCompareItems() = compareItems

    fun setCompareItems(compareItems: (old: T, new:T) -> Boolean) : HAdapterBuilder<T> {
        this.compareItems = compareItems
        return this
    }

    @Throws(HAdapterException::class)
    fun build() : HAdapter<T> {
        if(layoutId == -1)
            throw HAdapterException("setLayoutId() is not added on builder! Please make sure to add it before using build() method")

        if(!(::compareItems.isInitialized))
            throw HAdapterException("setCompareItems() is not added on builder! Please make sure to add it before using build() method")

        return HAdapter(this)
    }
}