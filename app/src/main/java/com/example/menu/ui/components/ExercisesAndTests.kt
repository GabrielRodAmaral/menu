package com.example.menu.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.menu.R

@Preview(showBackground = true)
@Composable
fun Desafio() {
    Row (Modifier.fillMaxWidth()) {
        Box(modifier = Modifier
            .height(150.dp)
            .width(126.dp)
            .background(Color.Blue))
        Column {
            Text(
                "Test 1",
                modifier = Modifier
                    .background(Color.Gray)
                    .padding(16.dp)
                    .fillMaxWidth()
            )
            Text(
                "Test 2",
                modifier = Modifier
                    .padding(16.dp))
        }
    }
}


@Preview(name = "Desafio 2 layout", showBackground = true)
@Composable
fun DesafioDois() {
    Surface (
        Modifier
            .clip(shape = RoundedCornerShape(15.dp))
    ) {
        Row (Modifier.fillMaxWidth()) {
            Box(
                Modifier
                    .height(200.dp)
                    .width(100.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color(0xFF7B1FA2),
                                Color(0xFFCE93D8)
                            )
                        )
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "imagem exemplo",
                    modifier = Modifier
                        .offset(x = 50.dp)
                        .clip(shape = CircleShape)
                        .border(
                            BorderStroke(
                                width = 2.dp, brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0xFFCE93D8),
                                        Color(0xFF7B1FA2)
                                    )
                                )
                            ), shape = CircleShape
                        )
                        .size(100.dp)
                        .align(Alignment.CenterEnd)
                )
            }
            Spacer(Modifier.width(50.dp))
            Text(
                LoremIpsum(50).values.first(),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = 32.dp),
                maxLines = 6,
                overflow = TextOverflow.Ellipsis,
                fontSize = 16.sp
            )
        }
    }
}

@Preview
@Composable
fun DesafioDescricaoPreview() {
    DesafioDescricao(LoremIpsum(30).values.first())
}


@Composable
fun DesafioDescricao(description: String = "") {
    Surface(
        shape = RoundedCornerShape(15.dp),
        shadowElevation = 4.dp,
    ) {
        Column(
            Modifier
                .heightIn(250.dp, 260.dp)
                .width(200.dp)
                .verticalScroll(ScrollState(0))
        ) {
            val imageSize = 100.dp
            Box(
                modifier = Modifier
                    .height(imageSize)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFF6200EE),
                                Color(0xFF03DAC5)
                            )
                        )
                    )
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "Imagem do produto",
                    Modifier
                        .offset(y = (imageSize / 2))
                        .size(imageSize)
                        .align(Alignment.BottomCenter)
                        .clip(shape = CircleShape)
                )
            }
            Spacer(
                modifier = Modifier
                    .height(imageSize / 2)
            )
            Column(Modifier.padding(16.dp)) {
                Text(
                    text = LoremIpsum(50).values.first(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight(700),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "R$ 35,90",
                    Modifier.padding(top = 8.dp),
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400)
                )
            }
            if (description.isNotBlank()) {
                Text(
                    text = description,
                    Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 16.dp)
                )
            }
        }
    }
}