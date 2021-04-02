package data

import main.HAdapterBasic

internal fun <T : Any> HAdapterBasic<T>.getModifiableList(): ArrayList<T> =
    ArrayList(differ.currentList)

internal fun <T : Any> HAdapterBasic<T>.hasItemAt(position: Int): ArrayList<T>? {
    with(getModifiableList()) {
        return if (position in this.indices) {
            this
        } else {
            null
        }
    }
}

fun <T : Any> HAdapterBasic<T>.firstItem(): T? {
    with(getModifiableList()) {
        return first().takeIf { isNotEmpty() }
    }
}

fun <T : Any> HAdapterBasic<T>.lastItem(): T? {
    with(getModifiableList()) {
        return last().takeIf { isNotEmpty() }
    }
}

fun <T : Any> HAdapterBasic<T>.isEmpty(): Boolean {
    return getModifiableList().isEmpty()
}

fun <T : Any> HAdapterBasic<T>.isNotEmpty(): Boolean {
    return getModifiableList().isNotEmpty()
}

fun <T : Any> HAdapterBasic<T>.hasItem(data: T): Boolean {
    with(getModifiableList()) {
        return contains(data)
    }
}

fun <T : Any> HAdapterBasic<T>.getItems(): ArrayList<T> = getModifiableList()

fun <T : Any> HAdapterBasic<T>.getItemAt(position: Int): T = differ.currentList[position]

fun <T : Any> HAdapterBasic<T>.getItemPosition(data: T): Int {
    with(getModifiableList()) {
        return if (contains(data)) {
            indexOf(data)
        } else {
            -1
        }
    }
}

fun <T : Any> HAdapterBasic<T>.getItemsCount(): Int = getModifiableList().size

fun <T : Any> HAdapterBasic<T>.removeItem(data: T): Boolean {
    with(getModifiableList()) {
        return if (contains(data)) {
            remove(data)
            true
        } else {
            false
        }
    }
}

fun <T : Any> HAdapterBasic<T>.removeItemAt(position: Int): Boolean {
    return hasItemAt(position)?.let { list ->
        with(list) {
            removeAt(position)
            differ.submitList(this)
            true
        }
    } ?: false
}

fun <T : Any> HAdapterBasic<T>.removeItems() {
    differ.submitList(emptyList())
}

fun <T : Any> HAdapterBasic<T>.removeItems(from: Int, to: Int) {
    with(getModifiableList()) {
        if (from in this.indices && to in this.indices) {
            this.subList(from, to)
            setItems(this)
        }
    }
}

fun <T : Any> HAdapterBasic<T>.removeItemsIf(filter: (T) -> (Boolean)) {
    val updatedList = arrayListOf<T>()
    getModifiableList().forEach {
        if (filter(it)) {
            updatedList.add(it)
        }
    }

    setItems(updatedList)
}

fun <T : Any> HAdapterBasic<T>.setItemAt(data: T, position: Int): Boolean {
    return hasItemAt(position)?.let { list ->
        with(list) {
            removeAt(position)
            add(position, data)
            differ.submitList(this)
            true
        }
    } ?: false
}

fun <T : Any> HAdapterBasic<T>.setItems(data: List<T>): Boolean {
    differ.submitList(data)
    return true
}

fun <T : Any> HAdapterBasic<T>.addItem(data: T) {
    with(getModifiableList()) {
        add(data)
        differ.submitList(this)
    }
}

fun <T : Any> HAdapterBasic<T>.addItemIf(data: T, filter: (T) -> (Boolean)) {
    if (filter(data)) {
        addItem(data)
    }
}

fun <T : Any> HAdapterBasic<T>.addItemAt(data: T, position: Int) {
    with(getModifiableList()) {
        add(position, data)
        differ.submitList(this)
    }
}

fun <T : Any> HAdapterBasic<T>.addItems(data: List<T>) {
    with(getModifiableList()) {
        addAll(data)
        differ.submitList(this)
    }
}

fun <T : Any> HAdapterBasic<T>.addItemsIf(data: List<T>, filter: (T) -> (Boolean)) {
    with(getModifiableList()) {
        data.forEach {
            if (filter(it)) {
                this.add(it)
            }
        }
        differ.submitList(this)
    }
}

fun <T : Any> HAdapterBasic<T>.addItemsAt(data: List<T>, position: Int) {
    with(getModifiableList()) {
        addAll(position, data)
        differ.submitList(this)
    }
}

fun <T : Any, R : Comparable<R>> HAdapterBasic<T>.sortItems(filter: (T) -> R?) {
    with(getModifiableList()) {
        sortBy(filter)
        setItems(this)
    }
}