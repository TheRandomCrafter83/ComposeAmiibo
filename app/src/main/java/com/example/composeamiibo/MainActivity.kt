package com.example.composeamiibo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import coil.compose.rememberImagePainter
import com.example.composeamiibo.model.Amiibo
import com.example.composeamiibo.model.Root
import com.example.composeamiibo.repository.Repository
import com.example.composeamiibo.ui.theme.ComposeAmiiboTheme
import com.example.myapplication.MainViewModel
import com.example.myapplication.MainViewModelFactory

class MainActivity : ComponentActivity() {

    private lateinit var viewModel:MainViewModel

    //CommitTest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var amiibos: ArrayList<Amiibo> = ArrayList<Amiibo>()
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getAmiibo()
        viewModel.myResponse.observe(this, { response->
            if(response.isSuccessful){
                val root: Root? = response.body()
                if (root != null) {
                    Log.d("Response",root.toString())
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
        })

    }
}

@Composable
fun ActivityContent(amiibos:ArrayList<Amiibo>) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),

    ){
        itemsIndexed(amiibos){index, item ->  
            Card(modifier = Modifier
                .fillMaxSize(),
                shape = RoundedCornerShape(8.dp),

                )
            {
                Row(){
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeAmiiboTheme {

    }
}