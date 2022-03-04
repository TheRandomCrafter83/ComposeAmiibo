package com.example.composeamiibo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.composeamiibo.api.RetrofitInstance
import com.example.composeamiibo.model.Amiibo
import com.example.composeamiibo.ui.theme.ComposeAmiiboTheme
import com.example.myapplication.MainViewModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class AmiiboViewer : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAmiiboTheme {
                // A surface container using the 'background' color from the theme
                var bundle:Bundle? = intent.extras?.getBundle("sel_item")
                var encAmiibo: String? = bundle?.getString("sel_item")
                var amiibo: Amiibo? = encAmiibo?.let { Json.decodeFromString(it) }

                Scaffold(
                    topBar = {
                        TopAppBar(title ={
                            if (amiibo != null) {
                                Text( amiibo.name)
                            }
                        },
                        navigationIcon = {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back",
                                modifier = Modifier
                                    .clickable{
                                        this.finish()
                                    }

                            )
                        }
                            )
                    }

                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        if (amiibo != null) {
                            ViewAmiibo(amiibo = amiibo)
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun ViewAmiibo(amiibo: Amiibo){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)
    ) {
        Image(painter= rememberImagePainter(amiibo.image),
        contentDescription = "",
            modifier= Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = .5.toFloat())
                .padding(8.dp)
        )
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            HeaderText("Ammibo Information")
            RowData("Amiibo Series", amiibo.amiiboSeries)
            RowData("Game Series", amiibo.gameSeries)
            Header2Text("Release Dates:")
            RowData("Austrailia",
            if(amiibo.release.au != null){
                amiibo.release.au.toString()
            }else{
                "Unavailable"
            }
                )
            RowData("Europe",
                if(amiibo.release.eu != null){
                    amiibo.release.eu.toString()
                }else{
                    "Unavailable"
                }
            )
            RowData("Japan",
                if(amiibo.release.jp != null){
                    amiibo.release.jp.toString()
                }else{
                    "Unavailable"
                }
            )
            RowData("North America",
                if(amiibo.release.na != null){
                    amiibo.release.na.toString()
                }else{
                    "Unavailable"
                }
            )
        }

    }
}

@Composable
fun RowData(label:String, value:String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(label)
        Text(
            text = value, modifier = Modifier
                .weight(1f), textAlign = TextAlign.End
        )
    }
}

@Composable
fun HeaderText(text:String){
    Text(text,fontWeight=FontWeight.Bold, fontSize=24.sp)
}

@Composable
fun Header2Text(text:String){
    Text(text,fontWeight=FontWeight.Bold, fontSize=18.sp)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ComposeAmiiboTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background)
            ) {

            }
        }
    }
}