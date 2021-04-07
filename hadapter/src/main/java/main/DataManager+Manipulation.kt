package main

import main.HAdapterParent

//TODO: Test all these methods belove if everything works perfectly and publish with samples :)

internal fun <T : Any> HAdapterParent<T>.getFixedPosition(position: Int) : Int {
    return if (hasHeader) {
        position - 1
    } else {
        position
    }
}

internal fun <T : Any> HAdapterParent<T>.addHeader(list: ArrayList<T?>) {
    if(!hasHeader || list.firstOrNull() == null)
        return

    list.add(0, null)
}

internal fun <T : Any> HAdapterParent<T>.addFooter(list: ArrayList<T?>) {
    if(!hasFooter || list.lastOrNull() == null)
        return

    list.add(null)
}

internal fun <T : Any> HAdapterParent<T>.getModifiableList(addExtraItems: Boolean = false): ArrayList<T?> {
    val currentList = ArrayList(differ.currentList)

    if(addExtraItems) {
        addHeader(currentList)
        addFooter(currentList)
    }

    return currentList
}

internal fun <T : Any> HAdapterParent<T>.hasItemAt(position: Int, addExtraItems: Boolean = false): T? {
    with(getModifiableList(addExtraItems)) {
        return if (position < this.size) {
            return this[position]
        } else {
            null
        }
    }
}

fun <T : Any> HAdapterParent<T>.firstItem(): T? {
    with(getModifiableList()) {
        return firstOrNull()
    }
}

fun <T : Any> HAdapterParent<T>.lastItem(): T? {
    with(getModifiableList()) {
        return lastOrNull()
    }
}

fun <T : Any> HAdapterParent<T>.isEmpty(): Boolean {
    return getModifiableList().size <= 0
}

fun <T : Any> HAdapterParent<T>.isNotEmpty(): Boolean {
    return getModifiableList().size > 0
}

fun <T : Any> HAdapterParent<T>.hasItem(data: T): Boolean {
    with(getModifiableList()) {
        return contains(data)
    }
}

fun <T : Any> HAdapterParent<T>.getItems(): ArrayList<T?> = getModifiableList()

fun <T : Any> HAdapterParent<T>.getItemAt(position: Int): T? = getModifiableList()[position]

fun <T : Any> HAdapterParent<T>.getItemPosition(data: T): Int {
    with(getModifiableList()) {
        return if (contains(data)) {
            indexOf(data)
        } else {
            -1
        }
    }
}

fun <T : Any> HAdapterParent<T>.getItemsCount(): Int = getModifiableList().size

fun <T : Any> HAdapterParent<T>.removeItem(data: T): Boolean {
    with(getModifiableList(true)) {
        return if (contains(data)) {
            remove(data)
            differ.submitList(this)
            true
        } else {
            false
        }
    }
}

fun <T : Any> HAdapterParent<T>.removeItemAt(position: Int): Boolean {
    return hasItemAt(position)?.let { data ->
        val list = getModifiableList()
        list.remove(data)
        differ.submitList(list)
        true
    } ?: false
}

fun <T : Any> HAdapterParent<T>.removeItems() {
    differ.submitList(emptyList())
}

fun <T : Any> HAdapterParent<T>.removeItems(from: Int, to: Int) {
    with(getModifiableList(true)) {
        if (from in this.indices && to in this.indices) {
            this.subList(from, to)
            setItems(this)
        }
    }
}

fun <T : Any> HAdapterParent<T>.removeItemsIf(filter: (T?) -> (Boolean)) {
    val updatedList = arrayListOf<T?>()
    getModifiableList().forEach {
        if (filter(it)) {
            updatedList.add(it)
        }
    }

    setItems(updatedList)
}

fun <T : Any> HAdapterParent<T>.setItemAt(data: T, position: Int): Boolean {
    return hasItemAt(position, true)?.let { item ->
        val list = getModifiableList(true)
        list.remove(item)
        list.add(position, data)
        differ.submitList(list)
        true
    } ?: false
}

fun <T : Any> HAdapterParent<T>.setItems(data: ArrayList<T?>): Boolean {
    differ.submitList(data)
    return true
}

fun <T : Any> HAdapterParent<T>.addItem(data: T) {
    with(getModifiableList()) {
        add(data)
        differ.submitList(this)
    }
}

fun <T : Any> HAdapterParent<T>.addItemIf(data: T, filter: (T) -> (Boolean)) {
    if (filter(data)) {
        addItem(data)
    }
}

fun <T : Any> HAdapterParent<T>.addItemAt(data: T, position: Int) {
    with(getModifiableList(false)) {
        add(position, data)
        differ.submitList(this)
    }
}

fun <T : Any> HAdapterParent<T>.addItems(data: List<T>) {
    with(getModifiableList()) {
        addAll(data)
        differ.submitList(this)
    }
}

fun <T : Any> HAdapterParent<T>.addItemsIf(data: List<T>, filter: (T) -> (Boolean)) {
    with(getModifiableList()) {
        data.forEach {
            if (filter(it)) {
                this.add(it)
            }
        }
        differ.submitList(this)
    }
}

fun <T : Any> HAdapterParent<T>.addItemsAt(data: List<T>, position: Int) {
    with(getModifiableList()) {
        addAll(position, data)
        differ.submitList(this)
    }
}

fun <T : Any, R : Comparable<R>> HAdapterParent<T>.sortItems(filter: (T?) -> R?) {
    with(getModifiableList()) {
        sortBy(filter)
        setItems(this)
    }
}