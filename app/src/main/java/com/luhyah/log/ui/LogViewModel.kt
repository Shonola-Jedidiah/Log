package com.luhyah.log.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.luhyah.log.ui.model.Diary
import com.luhyah.log.ui.model.Item
import com.luhyah.log.ui.model.ItemType
import com.luhyah.log.ui.model.LogModel
import com.luhyah.log.ui.model.TimeCapsule

class LogViewModel: ViewModel() {
    var logModel by mutableStateOf<LogModel>(tempLogModel)
        private set

}



val timeCapsule = listOf<Item>(
    Item(title = "To my future self", date = "07/23", url = "", type = ItemType.IMG),
    Item(title = "To my past self", date = "07/23", url = "", type = ItemType.IMG),
    Item(title = "To my present self", date = "07/23", url = "", type = ItemType.IMG),
    Item(title = "To the ones I love", date = "07/23", url = "", type = ItemType.IMG),
    Item(title = "To the ones I loved", date = "07/23", url = "", type = ItemType.IMG),
    Item(title = "To the crazy ones and daredevils", date = "07/23", url = "", type = ItemType.IMG),
    Item(title = "To the diehards", date = "07/23", url = "", type = ItemType.IMG),
)
val diary = listOf<Item>(
    Item(title = "Boring Monday", date = "07/23", url = "", type = ItemType.IMG),
    Item(title = "Craziest Sexual Encounter", date = "07/23", url = "", type = ItemType.IMG),
    Item(title = "Saddest Day Of My Life", date = "07/23", url = "", type = ItemType.IMG),
    Item(title = "A Near Death Experience", date = "07/23", url = "", type = ItemType.IMG),
    Item(title = "Another Near Death Experience", date = "07/23", url = "", type = ItemType.IMG),
    Item(title = "Homosexuality: A rant", date = "07/23", url = "", type = ItemType.IMG),
    Item(title = "Zionism: A condemnating rant", date = "07/23", url = "", type = ItemType.IMG),
)

val tempLogModel = LogModel(userName = "Luhyah",diary= Diary(diary), timeCapsule = TimeCapsule(timeCapsule)
)