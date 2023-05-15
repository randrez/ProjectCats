package com.example.projectcats.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectcats.R
import com.example.projectcats.domain.models.Cat
import com.example.projectcats.viewModels.states.CatState
import com.google.accompanist.picasso.PicassoImage
import com.squareup.picasso.Picasso

@Composable
fun CatsScreen(
    state: CatState,
    cats: List<Cat>
) {

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.cats),
                    color = MaterialTheme.colors.onPrimary
                )
            },
            backgroundColor = MaterialTheme.colors.primary
        )
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .background(MaterialTheme.colors.background)
                .fillMaxSize()
        ) {

            if (state.loading) {
                CircularProgressIndicator(
                    color = Color.Blue,
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                )
            } else {
                LazyColumn {
                    items(cats) { cat ->
                        Row{
                            CardCat(cat)
                            Spacer(modifier = Modifier.size(5.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CardCat(cat: Cat) {
    Card(modifier = Modifier.fillMaxWidth(), elevation = 2.dp, shape = RoundedCornerShape(10.dp)) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = cat.breedName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            ImageCat(imageUrl = "https://ejemplo.com/mi_imagen.jpg")

            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = cat.origin, color = Color.Gray)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = cat.intelligence.toString(), color = Color.DarkGray)
            }
        }
    }
}

@Composable
fun ImageCat(imageUrl: String) {

    val picasso = Picasso.get()
    val requestCreator = picasso.load(imageUrl)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        PicassoImage(data = requestCreator) {}
    }
}