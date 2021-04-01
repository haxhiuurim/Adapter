package main

internal fun <T: Any> HAdapter<T>.getModifiableList() : ArrayList<T> = ArrayList(differ.currentList)

internal fun <T: Any> HAdapter<T>.hasItemAt(position: Int) : ArrayList<T>?{
    with(getModifiableList()) {
        return if(position in this.indices) {
            this
        } else {
            null
        }
    }
}

fun <T:Any> HAdapter<T>.getItemAt(position: Int) : T = differ.currentList[position]

fun <T:Any> HAdapter<T>.removeItemAt(position: Int) : Boolean {
    return hasItemAt(position)?.let { list ->
        list.removeAt(position)
        differ.submitList(list)
        true
    } ?: false
}

fun <T:Any> HAdapter<T>.setItemAt(data: T, position: Int) : Boolean {
    return hasItemAt(position)?.let { list ->
        list.removeAt(position)
        list.add(position, data)
        differ.submitList(list)
        true
    } ?: false
}

fun <T:Any> HAdapter<T>.setItems(data: List<T>) : Boolean{
    differ.submitList(data)
    return true
}


//TODO: Add more manipulation methods here :)