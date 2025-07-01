//package core.ui.designsystem.component
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material3.Card
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.ColorFilter
//import androidx.compose.ui.graphics.DefaultAlpha
//import androidx.compose.ui.graphics.painter.Painter
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.unit.dp
//import core.ui.SingularityScope
//
//@Composable
//fun SSmallIcon(
//    painter: Painter,
//    contentDescription: String?,
//    modifier: Modifier = Modifier,
//    alignment: Alignment = Alignment.Center,
//    contentScale: ContentScale = ContentScale.Fit,
//    alpha: Float = DefaultAlpha,
//    colorFilter: ColorFilter? = null
//) {
//    Image(
//        painter = painter,
//        contentDescription = contentDescription,
//        modifier = Modifier.size(24.dp)
//            .then(modifier),
//        alignment = alignment,
//        contentScale = contentScale,
//        alpha = alpha,
//        colorFilter = colorFilter
//    )
//}
//
//@Composable
//fun SMediumIcon(
//    painter: Painter,
//    contentDescription: String?,
//    modifier: Modifier = Modifier,
//    alignment: Alignment = Alignment.Center,
//    contentScale: ContentScale = ContentScale.Fit,
//    alpha: Float = DefaultAlpha,
//    colorFilter: ColorFilter? = null
//) {
//    Image(
//        painter = painter,
//        contentDescription = contentDescription,
//        modifier = Modifier.size(36.dp)
//            .then(modifier),
//        alignment = alignment,
//        contentScale = contentScale,
//        alpha = alpha,
//        colorFilter = colorFilter
//    )
//}
//
//@Composable
//fun SLargeIcon(
//    painter: Painter,
//    contentDescription: String?,
//    modifier: Modifier = Modifier,
//    alignment: Alignment = Alignment.Center,
//    contentScale: ContentScale = ContentScale.Fit,
//    alpha: Float = DefaultAlpha,
//    colorFilter: ColorFilter? = null
//) {
//    Image(
//        painter = painter,
//        contentDescription = contentDescription,
//        modifier = Modifier.size(48.dp)
//            .then(modifier),
//        alignment = alignment,
//        contentScale = contentScale,
//        alpha = alpha,
//        colorFilter = colorFilter
//    )
//}
//
//@Composable
//fun SExtraLargeIcon(
//    painter: Painter,
//    contentDescription: String?,
//    modifier: Modifier = Modifier,
//    alignment: Alignment = Alignment.Center,
//    contentScale: ContentScale = ContentScale.Fit,
//    alpha: Float = DefaultAlpha,
//    colorFilter: ColorFilter? = null
//) {
//    Image(
//        painter = painter,
//        contentDescription = contentDescription,
//        modifier = Modifier.size(64.dp)
//            .then(modifier),
//        alignment = alignment,
//        contentScale = contentScale,
//        alpha = alpha,
//        colorFilter = colorFilter
//    )
//}
//
//context(SingularityScope)
//@Composable
//fun SSmallLogo(
//    painter: Painter,
//    contentDescription: String?,
//    modifier: Modifier = Modifier,
//    alignment: Alignment = Alignment.Center,
//    /*contentScale: ContentScale = ContentScale.Fit,*/
//    /*alpha: Float = DefaultAlpha,*/
//    /*colorFilter: ColorFilter? = null*/
//    onClick: () -> Unit = {}
//) {
//    Card(
//        onClick = {
//            log("Logo clicked $contentDescription")
//            onClick()
//        },
//        shape = CircleShape
//    ) {
//        Image(
//            painter = painter,
//            contentDescription = contentDescription,
//            modifier = Modifier.size(90.dp)
//                .then(modifier),
//            alignment = alignment,
//            contentScale = ContentScale.Fit,
//            alpha = DefaultAlpha,
//            colorFilter = null
//        )
//    }
//}
//
//context(SingularityScope)
//@Composable
//fun SMediumLogo(
//    painter: Painter,
//    contentDescription: String?,
//    modifier: Modifier = Modifier,
//    alignment: Alignment = Alignment.Center,
//    /*contentScale: ContentScale = ContentScale.Fit,*/
//    /*alpha: Float = DefaultAlpha,*/
//    /*colorFilter: ColorFilter? = null*/
//    onClick: () -> Unit = {}
//) {
//    Card(
//        onClick = {
//            log("Logo clicked $contentDescription")
//            onClick()
//        },
//        shape = CircleShape
//    ) {
//        Image(
//            painter = painter,
//            contentDescription = contentDescription,
//            modifier = Modifier.size(120.dp)
//                .then(modifier),
//            alignment = alignment,
//            contentScale = ContentScale.Fit,
//            alpha = DefaultAlpha,
//            colorFilter = null
//        )
//    }
//}
//
//context(SingularityScope)
//@Composable
//fun SLargeLogo(
//    painter: Painter,
//    contentDescription: String?,
//    modifier: Modifier = Modifier,
//    alignment: Alignment = Alignment.Center,
//    /*contentScale: ContentScale = ContentScale.Fit,*/
//    /*alpha: Float = DefaultAlpha,*/
//    /*colorFilter: ColorFilter? = null*/
//    onClick: () -> Unit = {}
//) {
//    Card(
//        onClick = {
//            log("Logo clicked $contentDescription")
//            onClick()
//        },
//        shape = CircleShape
//    ) {
//        Image(
//            painter = painter,
//            contentDescription = contentDescription,
//            modifier = Modifier.size(150.dp)
//                .then(modifier),
//            alignment = alignment,
//            contentScale = ContentScale.Fit,
//            alpha = DefaultAlpha,
//            colorFilter = null
//        )
//    }
//}
