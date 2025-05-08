package com.luhyah.log.ui.composables
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CameraAlt
import androidx.compose.material.icons.rounded.CloudUpload
import androidx.compose.material.icons.rounded.EditNote
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.luhyah.log.R
import com.luhyah.log.ui.LogViewModel
import com.luhyah.log.ui.model.Item
import com.luhyah.log.ui.model.ItemType
import com.luhyah.log.ui.theme.LogTheme


@Composable
fun HomePage(
    modifier: Modifier = Modifier,  logViewModel: LogViewModel = viewModel()
){
    Box {
        IconButton(onClick = {}, modifier = modifier.align(Alignment.TopEnd)) {
            Icon(Icons.Rounded.MoreVert, contentDescription = "Menu")
        }
        Column(modifier = modifier
            .align(Alignment.TopStart)
            .fillMaxSize()
            .padding(5.dp)
        ) {
            Text(text = "Welcome, " + logViewModel.logModel.userName,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(start = 5.dp, top= 4.dp, bottom = 23.dp, end = 5.dp))

//        HorizontalDivider(
//            thickness = 2.dp,
//            color = Color(red =0f, green = 0f, blue = 0f),
//            modifier = Modifier.padding(bottom = 15.dp)
//        )

            var currentTab by remember { mutableIntStateOf(0) }
            var tabItems = listOf<String>(stringResource(id = R.string.time_capsule), stringResource(id = R.string.diary))
            var pagerState = rememberPagerState { tabItems.size }

            LaunchedEffect(currentTab) {
                pagerState.animateScrollToPage(currentTab)
            }
            LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
                if(!pagerState.isScrollInProgress){
                    currentTab = pagerState.currentPage
                }
            }

            TabRow(selectedTabIndex = currentTab,
                containerColor = Color(red = 0, green = 0, blue = 0, alpha = 0)) {
                tabItems.forEachIndexed { pos, title ->
                    Tab(
                        selected = pos == currentTab,
                        onClick = {
                            currentTab = pos
                        },
                        text = { Text(text = title,
                            style = MaterialTheme.typography.headlineSmall)},
                        selectedContentColor = MaterialTheme.colorScheme.tertiary,
                        unselectedContentColor =MaterialTheme.colorScheme.secondary
                    )
                }
            }
            var size by remember { mutableStateOf(IntSize.Zero) }
            HorizontalPager(state = pagerState, modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .onSizeChanged {
                    size = it
                },
                verticalAlignment = Alignment.Top) {
//
                    //Lazy Grid containing logViewModel.logModel.diary
                    LazyVerticalGrid(columns = GridCells.Adaptive(200.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.padding(5.dp)) {
                        if(it == 0){
                            items(logViewModel.logModel.timeCapsule.items.size){
                                    it -> ItemComposable(logViewModel.logModel.timeCapsule.items[it])
                            }
                        }else{
                            items(logViewModel.logModel.diary.items.size){
                                    it -> ItemComposable(logViewModel.logModel.diary.items[it])
                            }
                        }
                    }
            }

        }

        FloatingButtons({},{}, modifier = Modifier
            .align(Alignment.BottomEnd)
            .navigationBarsPadding())
    }
}
@Composable
fun FloatingButtons(toCam: ()-> Unit, toNote: ()-> Unit, modifier: Modifier= Modifier){
    Column (modifier = modifier.padding(25.dp)){
        FloatingActionButton(onClick = toNote,
            shape = RoundedCornerShape(100),
            modifier = Modifier.padding(bottom = 25.dp)) {
            Icon(Icons.Rounded.EditNote, contentDescription = "Write")
        }
        FloatingActionButton(onClick = toCam,
            shape = RoundedCornerShape(100),
            modifier = Modifier.padding(bottom = 0.dp)) {
            Icon(Icons.Rounded.CameraAlt, contentDescription = "Open Camera")
        }

    }
}


@Composable
fun ItemComposable(item: Item){

        Column (verticalArrangement = Arrangement.Center, modifier = Modifier.height(250.dp)){
            Card(shape = RoundedCornerShape(
                topEnd = 10.dp, bottomEnd = 0.dp,
                topStart = 10.dp, bottomStart = 0.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically,modifier = Modifier.padding(10.dp)){
                    Text(text = item.title, maxLines = 1, overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(0.7f),
                        fontWeight = FontWeight.Bold)

                    Text(text = item.date, fontWeight = FontWeight.Bold, modifier = Modifier)

                }
            }
            Card(shape = RoundedCornerShape(
                topEnd = 0.dp, bottomEnd = 10.dp,
                topStart = 0.dp, bottomStart = 10.dp),
                modifier = Modifier
                .padding(top = 1.dp)
                .fillMaxHeight()){
                Box(modifier = Modifier.fillMaxSize()) {
                    when(item.type){
                        ItemType.IMG -> {}
                        ItemType.TXT -> {}
                        ItemType.VID -> {}
                    }
                    //This should switch between Icons.Rounded.CloudDone, Icons.Rounded.CloudOff and Icons.Rounded.CloudUpload based on Internet availability and whether the file is uploaded or not
                    Icon(Icons.Rounded.CloudUpload, contentDescription = "", modifier = Modifier.align(Alignment.BottomEnd).padding(12.dp))
                }


            }
        }
}


@Composable
fun WYSIWYG(){
    
}

@Composable
fun Drafts(items:List<Item>){
    LazyVerticalGrid(columns = GridCells.Adaptive(200.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier.padding(5.dp)) {
            items (items.size){
                    it -> ItemComposable(items[it])
            }
    }
}

@Composable
fun CamX(){

}

@Composable
fun Auth(){

}

@Composable
fun Settings(){

}


@Preview(fontScale = 0.8f, showSystemUi = true)
@Composable
fun Preview(){
    LogTheme {
        HomePage(Modifier.systemBarsPadding())
    }

}


//@Preview
@Composable
fun ItemComposablePreview(){
    ItemComposable(Item(title = "To my future self", date = "07/23", url = "", type = ItemType.IMG))
}

//@Preview
@Composable
fun WYSIWYGPreview(){
    WYSIWYG()
}

//@Preview
@Composable
fun DraftsPreview(){
    Drafts(listOf())
}

//@Preview
@Composable
fun CamXPreview(){
    CamX()
}

//@Preview
@Composable
fun AuthPreview(){
    Auth()
}

//@Preview
@Composable
fun SettingsPreview(){
    Settings()
}