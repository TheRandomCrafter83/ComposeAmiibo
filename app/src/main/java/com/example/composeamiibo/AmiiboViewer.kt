package com.example.composeamiibo

import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.composeamiibo.model.Amiibo
import com.example.composeamiibo.ui.theme.ComposeAmiiboTheme
import com.example.composeamiibo.util.Util

class AmiiboViewer : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAmiiboTheme {
                val amiibo: Amiibo = viewModel.selectedAmiibo
                val amiiboName: String = "" + amiibo.name
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {Text(amiiboName)},
                            navigationIcon = {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = getString(R.string.back),
                                    modifier = Modifier
                                        .clickable { this.finish() }
                                )
                            },
                            actions = {
                                MenuAction(R.drawable.ic_share,
                                    R.string.share,
                                    modifier=Modifier
                                        .clickable {
                                            Toast.makeText(applicationContext,"Hello", Toast.LENGTH_SHORT).show()
                                            Util.shareAmiibo(viewModel.selectedAmiibo)
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
                        ViewAmiibo(amiibo = amiibo)
                    }
                }

            }
        }
    }
}

@Composable
fun ViewAmiibo(amiibo: Amiibo) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            //.verticalScroll(rememberScrollState())
            .background(MaterialTheme.colors.background)
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(amiibo.image).apply(block = fun ImageRequest.Builder.() {
                    size(Size.ORIGINAL)
                }).build()
            ),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            HeaderText(stringResource(R.string.amiibo_view_header))
            RowData(stringResource(R.string.amiibo_series_label), amiibo.amiiboSeries)
            RowData(stringResource(R.string.amiibo_game_series_label), amiibo.gameSeries)
            Header2Text(stringResource(R.string.amiibo_release_dates_header))
            RowData(
                stringResource(R.string.amiibo_release_australia_label),
                amiibo.release.au
            )
            RowData(
                stringResource(R.string.amiibo_release_europe_label),
                amiibo.release.eu
            )
            RowData(
                stringResource(R.string.amiibo_release_japan_label),
                amiibo.release.jp
            )
            RowData(
                stringResource(R.string.amiibo_release_northamerica_label),
                amiibo.release.na
            )
        }

    }
}

@Composable
fun RowData(label: String, value: String) {
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