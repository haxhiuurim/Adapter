# HAdapter (AsyncListDiffer & DiffUtils)
![Version](https://img.shields.io/badge/version-1.0.2-green.svg)

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
    implementation 'com.github.haxhiuurim:h-adapter:1.0.2'
}
```

---


## Creating your adapter

###### This SDK offers two types of adapters: `HAdapter` and `HAdapterBasic`. You can use them by creating an instance of adapters, extending adapters or using their builders (HAdapterBuilder & HAdapterBasicBuilder) as in our examples below!
  
  
  
  
  
##### Example of creating HAdapterBasic using HAdapterBasicBuilder
  
  
    
```kotlin
var mySampleAdapter = HAdapterBuilder<String>()
        .setCompareItems{ old, new -> old.length == new.length }
        .setItemLayoutId(R.layout.adapter_item_layout)
        .setItemOnBindViewHolder { holder, data -> /* Bind your items here */ }
        .setItemOnClickListener { adapter, data, position -> /* Set your item click listener here */ }
        .build()

// Applying our adapter and LinearLayoutManager to our recycler
with(recyclerView) {
    layoutManager = LinearLayoutManager(this@HeaderFooterSampleActivity)
    adapter = mySampleAdapter
}
```
  
---

##### Example of creating HAdapter using HAdapterBuilder
  
`Important: Set itemAnimator = null on your recyclerView on which you're using HAdapter in order to avoid crashes!`  
    
```kotlin
var mySampleAdapter = HAdapterBuilder<String>()
        .setCompareItems{ old, new -> old.length == new.length }
        .setItemLayoutId(R.layout.adapter_item_layout)
        .setItemOnBindViewHolder { holder, data -> /* Bind your items here */ }
        .setItemOnClickListener { adapter, data, position -> /* Set your item click listener here */ }
        .setHeaderLayoutId(R.layout.adapter_header_layout)
        .setHeaderOnBindViewHolder { holder -> /* Bind your header here */ }
        .setHeaderOnItemClickListener { adapter -> /* Set your header click listener here */ }
        .setFooterLayoutId(R.layout.adapter_footer_layout)
        .setFooterOnBindViewHolder { holder -> /* Bind your footer here */ }
        .setFooterOnItemClickListener { adapter -> /* Set your footer click listener here */ }
        .build()
           
// Applying our adapter, LinearLayoutManager and itemAnimator as null to our recycler           
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
