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

###### This library contains two types of adapters: `HAdapter` and `HAdapterBasic`. Check examples below on how we can use them!  
  
  
  
  
  
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
