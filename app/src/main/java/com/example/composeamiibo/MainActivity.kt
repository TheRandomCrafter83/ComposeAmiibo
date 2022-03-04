package com.example.composeamiibo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import coil.compose.rememberImagePainter
import com.example.composeamiibo.model.Amiibo
import com.example.composeamiibo.model.Root
import com.example.composeamiibo.repository.Repository
import com.example.composeamiibo.ui.theme.ComposeAmiiboTheme
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel

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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ActivityContent(amiibos: ArrayList<Amiibo>) {
    val context: Context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),

        ) {
        itemsIndexed(amiibos) { _, item ->
            Card(modifier = Modifier
                .fillMaxSize(),
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    showViewAmiibo(context, item)

                }

            )
            {
                Row() {
                    Column() {
                        Image(
                            painter = rememberImagePainter(item.image),
                            contentDescription = null,
                            modifier = Modifier
                                .size(64.dp)
                                .padding(8.dp)
                        )
                    }
                    Column() {
                        Text(item.character)
                        Text(item.amiiboSeries)
                        Text(item.gameSeries)
                    }
                }

            }
        }
    }
}

fun showViewAmiibo(context: Context, amiibo: Amiibo) {
    var bundle: Bundle = Bundle()
    var encAmiibo: String = Json.encodeToString(amiibo)
    bundle.putString(SEL_ITEM_KEY, encAmiibo)
    var intent: Intent = Intent(context, AmiiboViewer::class.java)
    intent.action = Intent.ACTION_VIEW

    intent.putExtra(SEL_ITEM_KEY, bundle)
    context.startActivity(intent)
}

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
                        Row() {
                            Column() {
                                Image(
                                    painter = rememberImagePainter(it.toString()),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(64.dp)
                                        .padding(8.dp)
                                )
                            }
                            Column() {
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