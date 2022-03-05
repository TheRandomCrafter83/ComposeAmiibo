package com.example.composeamiibo

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.composeamiibo.model.Amiibo
import com.example.composeamiibo.model.Root
import com.example.composeamiibo.repository.Repository
import com.example.composeamiibo.ui.theme.ComposeAmiiboTheme
import com.example.composeamiibo.ui.theme.ListBackground
import kotlinx.serialization.json.JsonNull.content

lateinit var viewModel: MainViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var amiibos: ArrayList<Amiibo>
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getAmiibo()
        viewModel.myResponse.observe(this) { response ->
            if (response.isSuccessful) {
                val root: Root? = response.body()
                if (root != null) {
                    Log.d(getString(R.string.log_response_label), root.toString())
                    amiibos = root.amiibo
                    setContent {
                        ComposeAmiiboTheme {
                            // A surface container using the 'background' color from the theme
                            Surface(color = ListBackground) {
                                ActivityContent(amiibos)
                            }
                        }
                    }
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalCoilApi::class)
@Composable
fun ActivityContent(amiibos: ArrayList<Amiibo>) {
    val context: Context = LocalContext.current
    Scaffold(
        modifier= Modifier
            .background(Color.Transparent)
            .fillMaxSize(),
        backgroundColor = ListBackground,
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .background(ListBackground),
                verticalArrangement = Arrangement.spacedBy(16.dp),

                ) {
                itemsIndexed(amiibos) { _, item ->
                    Card(modifier = Modifier
                        .fillMaxSize(),
                        shape = RoundedCornerShape(8.dp),
                        onClick = {
                            viewModel.showViewAmiibo(context, item)
                        }
                    )
                    {
                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                        ) {
                            Column {
                                Image(
                                    painter = rememberImagePainter(item.image),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(64.dp)
                                        .padding(8.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Header2Text(item.character)
                                Text(item.amiiboSeries)
                                Text(item.gameSeries)
                            }
                        }

                    }
                }
            }
        },
        topBar = {
        TopAppBar(title = {
            Spacer(modifier = Modifier.width(16.dp))
            Text("Amiibo Rest Api Example")
        },
            navigationIcon = {
                Spacer(modifier = Modifier.width(16.dp))
                Image(
                    painterResource(R.drawable.amiibo_logo),
                    contentScale = ContentScale.Inside,
                    contentDescription = "Amiibo Rest Api Example",
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
        )
    })
}


@OptIn(ExperimentalCoilApi::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeAmiiboTheme {
        Surface(color = MaterialTheme.colors.background) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(count = 10) {
                    Card(
                        modifier = Modifier
                            .fillMaxSize(),
                        shape = RoundedCornerShape(8.dp)

                    )
                    {
                        Row {
                            Column {
                                Image(
                                    painter = rememberImagePainter(it.toString()),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(64.dp)
                                        .padding(8.dp)
                                )
                            }
                            Column {
                                Text("Hello")
                                Text("world")
                                Text("how are you")
                            }
                        }

                    }

                }
            }
        }
    }
}