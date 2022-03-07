package com.example.composeamiibo

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.composeamiibo.model.Amiibo
import com.example.composeamiibo.model.AmiiboDatabase
import com.example.composeamiibo.repository.Repository
import com.example.composeamiibo.ui.theme.ComposeAmiiboTheme

lateinit var viewModel: MainViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var amiibos: ArrayList<Amiibo>
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository, application)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.getAmiibo()
        viewModel.myResponse.observe(this) { response ->
            if (response.isSuccessful) {
                val amiiboDatabase: AmiiboDatabase? = response.body()
                if (amiiboDatabase != null) {
                    Log.d(getString(R.string.log_response_label), amiiboDatabase.toString())
                    amiibos = amiiboDatabase.amiibo
                    setContent {
                        ComposeAmiiboTheme {
                            // A surface container using the 'background' color from the theme
                            Surface(color = MaterialTheme.colors.background) {
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
        backgroundColor = MaterialTheme.colors.background,
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .background(MaterialTheme.colors.background),
                verticalArrangement = Arrangement.spacedBy(16.dp),

                ) {
                itemsIndexed(amiibos) { _, item ->
                    Card(modifier = Modifier
                        .fillMaxSize(),
                        shape = RoundedCornerShape(8.dp),
                        onClick = {
                            viewModel.recyclerViewSelectedCardClick(item)
                        }
                    )
                    {
                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                        ) {
                            Column {
                                BadgedBox(
                                    badge =
                                    {
                                        Box(modifier = Modifier
                                            .background(Color.Red)
                                            .padding(1.dp)
                                        ) {
                                            Text(item.type,
                                                fontSize = 10.sp,
                                                color=Color.White,
                                            )
                                        }
                                    }
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(20.dp))
                                            .background(MaterialTheme.colors.secondary)
                                            .padding(8.dp)
                                    ) {

                                        Image(
                                            painter = rememberImagePainter(item.image),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .size(64.dp)
                                                .padding(8.dp)
                                                .clip(CircleShape)
                                                .background(MaterialTheme.colors.secondary)


                                        )
                                    }
                                }

                            }
                            Spacer(modifier = Modifier.width(32.dp))
                            Column {
                                Header2Text(item.name)
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
        },backgroundColor=MaterialTheme.colors.primary ,
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