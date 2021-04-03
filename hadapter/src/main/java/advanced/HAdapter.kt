package advanced

import basic.HAdapterBasic

open class HAdapter<T : Any>(layoutId: Int,
                             compareItems: (old: T, new: T) -> Boolean,
                             onItemClickListener: ((adapter: HAdapterBasic<T>, data: T, position: Int) -> Unit)? = null,
                             onBindViewHolder: ((holder: ViewHolder, data: T) -> Unit)? = null)

    : HAdapterBasic<T>(layoutId, compareItems, onItemClickListener, onBindViewHolder) {

    constructor(builder: HAdapterBuilder<T>) : this(builder.getLayoutId(), builder.getCompareItems(), builder.getOnItemClickListener(), builder.getOnBindViewHolder())

    //TODO: Add header/footer in adapter (with layouts and types)
}