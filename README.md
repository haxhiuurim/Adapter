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

> Example of extending HAdapterBasic class below. 
> NOTE: We can create an instance of HAdapterBasic with same parameters similar as we're extending HAdapterBasic class.

```kotlin
class TestAdapterBasic : HAdapterBasic<String>(
        itemLayoutId = R.layout.adapter_item_layout,
        compareItems = { old, new -> old.length == new.length },
        onItemClickListener = null,
        onBindViewHolder = { holder: RecyclerView.ViewHolder, data: String ->
            holder.itemView.findViewById<TextView>(R.id.textView).text = data
        })
```
  
  
  
##### Details of extending HAdapter class below.  

| Parameter | Type  | Description  | Default Value | Our Example |
| ------   | ------ | ------ | ------ | ------ |
| itemLayoutId | Int (Resource layout) | Set to your item layout | -1 | R.layout.adapter_item_layout |
| compareItems | Compareable | Compare your old with new item unique ids or values | Not initialized | Comparing old and new item text lengths |
| itemOnClickListener | ((adapter: HAdapter<T>, data: T, position: Int) -> Unit)? | Set to your own delegate | null | null |
| itemOnBindViewHolder | ((holder: RecyclerView.ViewHolder, data: T) -> Unit)? = null | Set to your own delegate for binding items | null | Setting data to our text view |
| headerLayoutId | Int (Resource layout) | Set to your header layout | -1 | R.layout.header_item_layout |
| headerOnItemClickListener | ((adapter: HAdapter<T>) -> Unit)? | Set to your own delegate | null | null |
| headerOnBindViewHolder | ((holder: RecyclerView.ViewHolder) -> Unit)? = null | Set to your own delegate for binding header | null | Setting data to our header view |
| footerLayoutId | Int (Resource layout) | Set to your header layout | -1 | R.layout.footer_item_layout |
| footerOnItemClickListener | ((adapter: HAdapter<T>) -> Unit)? | Set to your own delegate | null | null |
| footerOnBindViewHolder | ((holder: RecyclerView.ViewHolder) -> Unit)? = null | Set to your own delegate for binding footer | null | Setting data to our footer view |

> Example of extending HAdapter class below.
> NOTE: We can create an instance of HAdapter with same parameters similar as we're extending HAdapterBasic class.

```kotlin
class TestAdapter : HAdapter<String>(
    itemLayoutId = R.layout.adapter_item_layout,
    compareItems = { old, new -> old.length == new.length },
    itemOnClickListener = null,
    itemOnBindViewHolder = { holder: RecyclerView.ViewHolder, data: String ->
        holder.itemView.findViewById<TextView>(R.id.textView).text = data
    },
    headerLayoutId = R.layout.adapter_header_layout,
    headerOnItemClickListener = null,
    headerOnBindViewHolder = { holder: RecyclerView.ViewHolder ->
        holder.itemView.findViewById<TextView>(R.id.textView).text = "This is my header view"
    },
    footerLayoutId = R.layout.adapter_footer_layout,
    footerOnItemClickListener = null,
    footerOnBindViewHolder = { holder: RecyclerView.ViewHolder ->
        holder.itemView.findViewById<TextView>(R.id.textView).text = "This is my footer view"
    }
)
```

##### Details of creating HAdapterBasic instance using HAdapterBasicBuilder.

> Example of creating instance of HAdapterBasic class using HAdapterBasicBuilder.
> NOTE: In order to create HAdapterBasic using HAdapterBasicBuilder we use the same parameters as when we extend HAdapterBasic.

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

##### Details of creating HAdapter instance using HAdapterBuilder.

> Example of creating instance of HAdapter class using HAdapterBuilder.
> NOTE: In order to create HAdapter using HAdapterBuilder we use the same parameters as when we extend HAdapter.

`IMPORTANT: Make sure you set itemAnimator to null when set our adapter to our recycler otherwise the application will crash when using HAdapter!`

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
