# HAdapter
![Version](https://img.shields.io/badge/version-1.0.1-green.svg)

HAdapter is a kotlin library for creating and implementing adapters easier.

## Installation

##### 1. Add jitpack.io on your build.gradle (project)

```
allprojects {
    repositories {
        ...
        ...
        maven { url 'https://jitpack.io/' }
    }
}
```

##### 2. Add HAdapter repository on your build.gradle (app)

```
dependencies {
    ...
    ...
    implementation 'com.github.haxhiuurim:Adapter:1.0.1'
}
```

---


## Creating your adapter

> We support two different adapters `HAdapter` and `HAdapterBasic` where both of these adapters can be used in different ways. Check examples below!    

##### Details of extending HAdapterBasic class below.  

| Parameter | Type  | Description  | Default Value | Our Example |
| ------   | ------ | ------ | ------ | ------ |
| itemLayoutId | Int (Resource layout) | Set to your item layout | -1 | R.layout.adapter_item_layout |
| compareItems | Compareable | Compare your old with new item unique ids or values | Not initialized | Comparing old and new item text lengths |
| onItemClickListener | ((adapter: HAdapterBasic<T>, data: T, position: Int) -> Unit)? | Set to your own delegate | null | null |
| onBindViewHolder | ((holder: RecyclerView.ViewHolder, data: T) -> Unit)? = null | Set to your own delegate for binding items | null | Setting data to our text view |

> Example of extending HAdapterBesic class below.

```kotlin
class TestAdapterBasic : HAdapterBasic<String>(
        itemLayoutId = R.layout.adapter_item_layout,
        compareItems = { old, new -> old.length == new.length },
        onItemClickListener = null,
        onBindViewHolder = { holder: RecyclerView.ViewHolder, data: String ->
            holder.itemView.findViewById<TextView>(R.id.textView).text = data
        })
```
