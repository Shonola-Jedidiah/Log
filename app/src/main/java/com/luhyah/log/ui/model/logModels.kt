package com.luhyah.log.ui.model


data class LogModel(
    val userName: String,
    val diary: Diary,
    val timeCapsule: TimeCapsule
    )

data class Diary(
 var items: List<Item>
)

data class TimeCapsule(
    var items: List<Item>
)

data class Item(
    val title: String,
    val date: String,
    val type: ItemType,
    val url: String
)

enum class ItemType{IMG, TXT, VID}
