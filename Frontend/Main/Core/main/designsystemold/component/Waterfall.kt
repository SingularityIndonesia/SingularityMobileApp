package designsystemold.component

import Waterfall
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import utils.px

@Preview
@Composable
fun WaterfallPreview() {
    val density = LocalDensity.current
    val images = remember {
        listOf(
            "https://media.istockphoto.com/id/814423752/photo/eye-of-model-with-colorful-art-make-up-close-up.jpg?s=612x612&w=0&k=20&c=l15OdMWjgCKycMMShP8UK94ELVlEGvt7GmB_esHWPYE=",
            "https://cnc-magazine.oramiland.com/parenting/images/kim-da-hyun.width-800.format-webp.webp",
            "https://burst.shopifycdn.com/photos/fashion-model-in-fur.jpg?width=1000&format=pjpg&exif=0&iptc=0",
            "https://cdn.shopify.com/s/files/1/0469/3927/5428/files/TWICE-Dahyun-for-A-pieu-2023-Juicy-Pang-Tint-documents-2.jpg?v=1738754206",
            "https://i.pinimg.com/736x/3f/10/a6/3f10a655fbe33402d858bffa7193df16.jpg",
            "https://images.saatchiart.com/saatchi/2199861/art/10341725/9404341-JUYLYEKQ-32.jpg",
            "https://pbs.twimg.com/media/GllvhtPW4AAUnRR?format=jpg&name=large",
            "https://img.freepik.com/free-photo/closeup-scarlet-macaw-from-side-view-scarlet-macaw-closeup-head_488145-3540.jpg?semt=ais_hybrid&w=740",
            "https://images.panda.org/assets/images/pages/welcome/orangutan_1600x1000_279157.jpg",
            "https://media.istockphoto.com/id/2148595463/photo/african-elephant-in-wilderness-at-sunset.jpg?s=612x612&w=0&k=20&c=WmZfQYFpQOKTIyB95U6NQ6h8WQM-Pjp9TCMvF63-Xf4=",
            "https://thumbs.dreamstime.com/b/i-m-hungry-ma-12739742.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQmfuHcvQcxVfU6YzM_ty5dfnSmojk8h2NSoi58XxFOzbh1ceRx9En9nnZUiVNqhH6IX-M&usqp=CAU"
        )
    }

    val items = remember { (0..30).toList() }
    val width = remember { mutableStateOf(0.dp) }

    Waterfall(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()
            .onSizeChanged {
                width.value = (it.width / density.density).dp
            },
        items = items,
        verticalGap = { panelWidth, rowCount -> if (rowCount > 2) 16.dp else 4.dp },
        horizontalGap = { panelWidth, rowCount -> if (rowCount > 2) 16.dp else 4.dp },
        contentPadding = { panelWidth, rowCount -> PaddingValues(if (rowCount > 2) 24.dp else 8.dp) },
        minWidth = { if (it > 800.dp) 300.dp else 200.dp }
    ) { item ->
        RatioImage(
            modifier = Modifier
                .background(Color.White)
                .border(BorderStroke(1.px, Color.Black.copy(alpha = .5f)))
                .padding(16.dp)
                .fillMaxWidth(),
            model = images[item % images.size]
        )
    }
}
