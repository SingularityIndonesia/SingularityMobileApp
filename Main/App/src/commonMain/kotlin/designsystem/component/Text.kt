///*
// * Copyright (c) 2024 Singularity Indonesia (stefanus.ayudha@gmail.com)
// * You are not allowed to remove the copyright. Unless you have a "free software" licence.
// */
//package core.ui.designsystem.component
//
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.alpha
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.TextLayoutResult
//import androidx.compose.ui.text.font.FontStyle
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.unit.dp
//
//@Composable
//fun STextTitle(
//    text: String,
//    modifier: Modifier = Modifier,
//    color: Color = Color.Unspecified,
//    textAlign: TextAlign? = null,
//    overflow: TextOverflow = TextOverflow.Ellipsis,
//    softWrap: Boolean = true,
//    maxLines: Int = Int.MAX_VALUE,
//    minLines: Int = 1,
//    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
//    /*fontSize: TextUnit = TextUnit.Unspecified,*/
//    /*fontStyle: FontStyle? = null,*/
//    /*fontWeight: FontWeight? = null,*/
//    /*fontFamily: FontFamily? = null,*/
//    /*letterSpacing: TextUnit = TextUnit.Unspecified,*/
//    /*textDecoration: TextDecoration? = null,*/
//    /*lineHeight: TextUnit = TextUnit.Unspecified,*/
//    /*style: TextStyle = LocalTextStyle.current,*/
//) {
//    Text(
//        text = text,
//        textAlign = textAlign,
//        style = MaterialTheme.typography.titleLarge,
//        modifier = Modifier
//            .padding(
//                vertical = 16.dp
//            )
//            .then(modifier),
//        color = color,
//        overflow = overflow,
//        softWrap = softWrap,
//        maxLines = maxLines,
//        minLines = minLines,
//        onTextLayout = onTextLayout,
//        /*fontSize = fontSize,*/
//        /*fontStyle = fontStyle,*/
//        /*fontWeight = fontWeight,*/
//        /*fontFamily = fontFamily,*/
//        /*letterSpacing = letterSpacing,*/
//        /*textDecoration = textDecoration,*/
//        /*lineHeight = lineHeight,*/
//    )
//}
//
//@Composable
//fun STextSubTitle(
//    text: String,
//    modifier: Modifier = Modifier,
//    color: Color = Color.Unspecified,
//    textAlign: TextAlign? = null,
//    overflow: TextOverflow = TextOverflow.Ellipsis,
//    softWrap: Boolean = true,
//    maxLines: Int = Int.MAX_VALUE,
//    minLines: Int = 1,
//    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
//    /*fontSize: TextUnit = TextUnit.Unspecified,*/
//    /*fontStyle: FontStyle? = null,*/
//    /*fontWeight: FontWeight? = null,*/
//    /*fontFamily: FontFamily? = null,*/
//    /*letterSpacing: TextUnit = TextUnit.Unspecified,*/
//    /*textDecoration: TextDecoration? = null,*/
//    /*lineHeight: TextUnit = TextUnit.Unspecified,*/
//    /*style: TextStyle = LocalTextStyle.current,*/
//) {
//    Text(
//        text = text,
//        textAlign = textAlign,
//        fontStyle = FontStyle.Italic,
//        style = MaterialTheme.typography.bodyLarge,
//        modifier = Modifier
//            .padding(
//                vertical = 4.dp
//            )
//            .alpha(.9f)
//            .then(modifier),
//        color = color,
//        overflow = overflow,
//        softWrap = softWrap,
//        maxLines = maxLines,
//        minLines = minLines,
//        onTextLayout = onTextLayout,
//        /*fontSize = fontSize,*/
//        /*fontStyle = fontStyle,*/
//        /*fontWeight = fontWeight,*/
//        /*fontFamily = fontFamily,*/
//        /*letterSpacing = letterSpacing,*/
//        /*textDecoration = textDecoration,*/
//        /*lineHeight = lineHeight,*/
//    )
//}
//
//@Composable
//fun STextHeadline1(
//    text: String,
//    modifier: Modifier = Modifier,
//    color: Color = Color.Unspecified,
//    textAlign: TextAlign? = null,
//    overflow: TextOverflow = TextOverflow.Ellipsis,
//    softWrap: Boolean = true,
//    maxLines: Int = Int.MAX_VALUE,
//    minLines: Int = 1,
//    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
//    /*fontSize: TextUnit = TextUnit.Unspecified,*/
//    /*fontStyle: FontStyle? = null,*/
//    /*fontWeight: FontWeight? = null,*/
//    /*fontFamily: FontFamily? = null,*/
//    /*letterSpacing: TextUnit = TextUnit.Unspecified,*/
//    /*textDecoration: TextDecoration? = null,*/
//    /*lineHeight: TextUnit = TextUnit.Unspecified,*/
//    /*style: TextStyle = LocalTextStyle.current,*/
//) {
//    Text(
//        text = text,
//        textAlign = textAlign,
//        style = MaterialTheme.typography.headlineLarge,
//        modifier = Modifier
//            .padding(
//                vertical = 20.dp
//            )
//            .then(modifier),
//        color = color,
//        overflow = overflow,
//        softWrap = softWrap,
//        maxLines = maxLines,
//        minLines = minLines,
//        onTextLayout = onTextLayout,
//        /*fontSize = fontSize,*/
//        /*fontStyle = fontStyle,*/
//        /*fontWeight = fontWeight,*/
//        /*fontFamily = fontFamily,*/
//        /*letterSpacing = letterSpacing,*/
//        /*textDecoration = textDecoration,*/
//        /*lineHeight = lineHeight,*/
//    )
//}
//
//@Composable
//fun STextHeadline2(
//    text: String,
//    modifier: Modifier = Modifier,
//    color: Color = Color.Unspecified,
//    textAlign: TextAlign? = null,
//    overflow: TextOverflow = TextOverflow.Ellipsis,
//    softWrap: Boolean = true,
//    maxLines: Int = Int.MAX_VALUE,
//    minLines: Int = 1,
//    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
//    /*fontSize: TextUnit = TextUnit.Unspecified,*/
//    /*fontStyle: FontStyle? = null,*/
//    /*fontWeight: FontWeight? = null,*/
//    /*fontFamily: FontFamily? = null,*/
//    /*letterSpacing: TextUnit = TextUnit.Unspecified,*/
//    /*textDecoration: TextDecoration? = null,*/
//    /*lineHeight: TextUnit = TextUnit.Unspecified,*/
//    /*style: TextStyle = LocalTextStyle.current,*/
//) {
//    Text(
//        text = text,
//        textAlign = textAlign,
//        style = MaterialTheme.typography.headlineMedium,
//        modifier = Modifier
//            .padding(
//                vertical = 14.dp
//            )
//            .then(modifier),
//        color = color,
//        overflow = overflow,
//        softWrap = softWrap,
//        maxLines = maxLines,
//        minLines = minLines,
//        onTextLayout = onTextLayout,
//        /*fontSize = fontSize,*/
//        /*fontStyle = fontStyle,*/
//        /*fontWeight = fontWeight,*/
//        /*fontFamily = fontFamily,*/
//        /*letterSpacing = letterSpacing,*/
//        /*textDecoration = textDecoration,*/
//        /*lineHeight = lineHeight,*/
//    )
//}
//
//@Composable
//fun STextHeadline3(
//    text: String,
//    modifier: Modifier = Modifier,
//    color: Color = Color.Unspecified,
//    textAlign: TextAlign? = null,
//    overflow: TextOverflow = TextOverflow.Ellipsis,
//    softWrap: Boolean = true,
//    maxLines: Int = Int.MAX_VALUE,
//    minLines: Int = 1,
//    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
//    /*fontSize: TextUnit = TextUnit.Unspecified,*/
//    /*fontStyle: FontStyle? = null,*/
//    /*fontWeight: FontWeight? = null,*/
//    /*fontFamily: FontFamily? = null,*/
//    /*letterSpacing: TextUnit = TextUnit.Unspecified,*/
//    /*textDecoration: TextDecoration? = null,*/
//    /*lineHeight: TextUnit = TextUnit.Unspecified,*/
//    /*style: TextStyle = LocalTextStyle.current,*/
//) {
//    Text(
//        text = text,
//        textAlign = textAlign,
//        style = MaterialTheme.typography.headlineSmall,
//        modifier = Modifier
//            .padding(
//                vertical = 12.dp
//            )
//            .then(modifier),
//        color = color,
//        overflow = overflow,
//        softWrap = softWrap,
//        maxLines = maxLines,
//        minLines = minLines,
//        onTextLayout = onTextLayout,
//        /*fontSize = fontSize,*/
//        /*fontStyle = fontStyle,*/
//        /*fontWeight = fontWeight,*/
//        /*fontFamily = fontFamily,*/
//        /*letterSpacing = letterSpacing,*/
//        /*textDecoration = textDecoration,*/
//        /*lineHeight = lineHeight,*/
//    )
//}
//
//@Composable
//fun STextBody(
//    text: String,
//    modifier: Modifier = Modifier,
//    color: Color = Color.Unspecified,
//    textAlign: TextAlign? = null,
//    overflow: TextOverflow = TextOverflow.Ellipsis,
//    softWrap: Boolean = true,
//    maxLines: Int = Int.MAX_VALUE,
//    minLines: Int = 1,
//    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
//    /*fontSize: TextUnit = TextUnit.Unspecified,*/
//    /*fontStyle: FontStyle? = null,*/
//    /*fontWeight: FontWeight? = null,*/
//    /*fontFamily: FontFamily? = null,*/
//    /*letterSpacing: TextUnit = TextUnit.Unspecified,*/
//    /*textDecoration: TextDecoration? = null,*/
//    /*lineHeight: TextUnit = TextUnit.Unspecified,*/
//    /*style: TextStyle = LocalTextStyle.current,*/
//) {
//    Text(
//        text = text,
//        style = MaterialTheme.typography.bodyLarge,
//        textAlign = textAlign,
//        modifier = Modifier
//            .padding(
//                vertical = 2.dp
//            )
//            .then(modifier),
//        color = color,
//        overflow = overflow,
//        softWrap = softWrap,
//        maxLines = maxLines,
//        minLines = minLines,
//        onTextLayout = onTextLayout,
//        /*fontSize = fontSize,*/
//        /*fontStyle = fontStyle,*/
//        /*fontWeight = fontWeight,*/
//        /*fontFamily = fontFamily,*/
//        /*letterSpacing = letterSpacing,*/
//        /*textDecoration = textDecoration,*/
//        /*lineHeight = lineHeight,*/
//    )
//}
//
//@Composable
//fun STextLabel(
//    text: String,
//    modifier: Modifier = Modifier,
//    color: Color = Color.Unspecified,
//    textAlign: TextAlign? = null,
//    overflow: TextOverflow = TextOverflow.Ellipsis,
//    softWrap: Boolean = true,
//    maxLines: Int = Int.MAX_VALUE,
//    minLines: Int = 1,
//    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
//    /*fontSize: TextUnit = TextUnit.Unspecified,*/
//    /*fontStyle: FontStyle? = null,*/
//    /*fontWeight: FontWeight? = null,*/
//    /*fontFamily: FontFamily? = null,*/
//    /*letterSpacing: TextUnit = TextUnit.Unspecified,*/
//    /*textDecoration: TextDecoration? = null,*/
//    /*lineHeight: TextUnit = TextUnit.Unspecified,*/
//    /*style: TextStyle = LocalTextStyle.current,*/
//) {
//    Text(
//        text = text,
//        textAlign = textAlign,
//        style = MaterialTheme.typography.labelLarge,
//        modifier = Modifier
//            .then(modifier),
//        color = color,
//        overflow = overflow,
//        softWrap = softWrap,
//        maxLines = maxLines,
//        minLines = minLines,
//        onTextLayout = onTextLayout,
//        /*fontSize = fontSize,*/
//        /*fontStyle = fontStyle,*/
//        /*fontWeight = fontWeight,*/
//        /*fontFamily = fontFamily,*/
//        /*letterSpacing = letterSpacing,*/
//        /*textDecoration = textDecoration,*/
//        /*lineHeight = lineHeight,*/
//    )
//}