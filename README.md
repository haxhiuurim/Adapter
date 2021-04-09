# HAdapter (AsyncListDiffer & DiffUtils)
![Version](https://img.shields.io/badge/version-1.0.1-green.svg)

HAdapter is a kotlin library for creating and implementing asynchronous adapters easier. These adapters work on background thread and use diff util to compare items and update only necessary ones.

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

###### This SDK offers two types of adapters: `HAdapter` and `HAdapterBasic`. You can use them by creating an instance of adapter, extending adapter or using their builders (HAdapterBuilder & HAdapterBasicBuilder). Check examples below on how we can use them!  
  
  
  
  
  
##### Example of creating HAdapterBasic using HAdapterBasicBuilder
  
  
    
```kotlin
mySampleAdapter = HAdapterBuilder<String>()
        .setCompareItems{ old, new -> compareItems(old, new)}
        .setItemLayoutId(R.layout.adapter_item_layout)
        .setItemOnBindViewHolder { holder, data -> itemOnBindViewHolder(holder, data) }
        .setItemOnClickListener { adapter, data, position -> onItemClicked(adapter, data, position) }
        .build()
                
with(recyclerView) {
    layoutManager = LinearLayoutManager(this@HeaderFooterSampleActivity)
    adapter = mySampleAdapter
}
```
  
---

##### Example of creating HAdapter using HAdapterBuilder
  
`Important: Set itemAnimator = null on your recyclerView on which you're using HAdapter in order to avoid crashes!`  
    
```kotlin
mySampleAdapter = HAdapterBuilder<String>()
        .setCompareItems{ old, new -> compareItems(old, new)}
        .setItemLayoutId(R.layout.adapter_item_layout)
        .setItemOnBindViewHolder { holder, data -> itemOnBindViewHolder(holder, data) }
        .setItemOnClickListener { adapter, data, position -> onItemClicked(adapter, data, position) }
        .setHeaderLayoutId(R.layout.adapter_header_layout)
        .setHeaderOnBindViewHolder { headerOnBindViewHolder(it) }
        .setHeaderOnItemClickListener { headerOnItemClicked(it) }
        .setFooterLayoutId(R.layout.adapter_header_layout)
        .setFooterOnBindViewHolder { footerOnBindViewHolder(it) }
        .setFooterOnItemClickListener { footerOnItemClicked(it) }
        .build()
                
with(recyclerView) {
    layoutManager = LinearLayoutManager(this@HeaderFooterSampleActivity)
    adapter = mySampleAdapter
    itemAnimator = null
}
```

---

##### List of methods this SDK supports to edit/change data on adapters. T represents your data type!
 * firstItem() : T?
 * lastItem() : T?
 * isEmpty() : Boolean
 * isNotEmpty() : Boolean
 * hasItem(data: T) : Boolean
 * getItems() : ArrayList<T?>
 * getItemAt(position: Int) : T?
 * getItemPosition(data: T) : Int
 * removeItem(data: T) : Boolean
 * removeItemAt(position: Int) : Boolean
 * removeItems()
 * removeItemsIf(filter: (T?) -> (Boolean?))
 * setItemAt(position: Int, data: T) : Boolean
 * setItems(data: ArrayList<T?>) : Boolean
 * addItem(data: T)
 * addItemAt(position: Int, data : T)
 * addItems(data: ArrayList<T>)
 * addItemsAt(position: Int, ArrayList<T>)
 * sortItems(filter: (T?) -> (R : Comperable?))
