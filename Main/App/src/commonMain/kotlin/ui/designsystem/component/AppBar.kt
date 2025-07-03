///*
// * Copyright (c) 2024 Singularity Indonesia (stefanus.ayudha@gmail.com)
// * You are not allowed to remove the copyright. Unless you have a "free software" licence.
// */
//package core.ui.designsystem.component
//
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.ArrowBack
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import core.ui.SingularityScope
//
//context(SingularityScope)
//@Composable
//fun STopAppBar(
//    title: String,
//    onBack: () -> Unit
//) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth(),
//        verticalAlignment = Alignment.CenterVertically,
//    ) {
//        IconButton(
//            onClick = {
//                log("Navigate back from $title")
//                onBack()
//            }
//        ) {
//            Icon(
//                Icons.AutoMirrored.Filled.ArrowBack,
//                contentDescription = null
//            )
//        }
//
//        STextTitle(
//            text = title
//        )
//    }
//}