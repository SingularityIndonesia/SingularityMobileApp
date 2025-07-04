///*
// * Copyright (c) 2024 Singularity Indonesia (stefanus.ayudha@gmail.com)
// * You are not allowed to remove the copyright. Unless you have a "free software" licence.
// */
//package core.ui.designsystem.component
//
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.text.KeyboardActions
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material3.LocalTextStyle
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldColors
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Shape
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.input.VisualTransformation
//import core.ui.SingularityScope
//import core.ui.designsystem.SingularityTheme
//import core.ui.designsystem.`large-padding`
//
//context(SingularityScope)
//@Composable
//fun STextField(
//    value: String,
//    onValueChange: (String) -> Unit,
//    modifier: Modifier = Modifier,
//    enabled: Boolean = true,
//    readOnly: Boolean = false,
//    textStyle: TextStyle = LocalTextStyle.current,
//    label: @Composable (() -> Unit)? = null,
//    placeholder: @Composable (() -> Unit)? = null,
//    leadingIcon: @Composable (() -> Unit)? = null,
//    trailingIcon: @Composable (() -> Unit)? = null,
//    prefix: @Composable (() -> Unit)? = null,
//    suffix: @Composable (() -> Unit)? = null,
//    supportingText: @Composable (() -> Unit)? = null,
//    isError: Boolean = false,
//    visualTransformation: VisualTransformation = VisualTransformation.None,
//    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
//    keyboardActions: KeyboardActions = KeyboardActions.Default,
//    singleLine: Boolean = false,
//    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
//    minLines: Int = 1,
//    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
//    shape: Shape = TextFieldDefaults.shape,
//    colors: TextFieldColors = TextFieldDefaults.colors(),
//) {
//    TextField(
//        value = value,
//        onValueChange = {
//            log("Typing $it")
//            onValueChange(it)
//        },
//        modifier = modifier,
//        enabled = enabled,
//        readOnly = readOnly,
//        textStyle = textStyle,
//        label = label,
//        placeholder = placeholder,
//        leadingIcon = leadingIcon,
//        trailingIcon = trailingIcon,
//        prefix = prefix,
//        suffix = suffix,
//        supportingText = supportingText,
//        isError = isError,
//        visualTransformation = visualTransformation,
//        keyboardOptions = keyboardOptions,
//        keyboardActions = keyboardActions,
//        singleLine = singleLine,
//        maxLines = maxLines,
//        minLines = minLines,
//        interactionSource = interactionSource,
//        shape = shape,
//        colors = colors,
//    )
//}
//
//context(SingularityScope)
//@Composable
//fun SSearchComponent(
//    clue: String,
//    onSearch: (String) -> Unit
//) {
//    val attr = SingularityTheme.attr
//    STextField(
//        modifier = Modifier
//            .padding(horizontal = attr.`large-padding`)
//            .fillMaxWidth(),
//        value = clue,
//        onValueChange = onSearch,
//    )
//}
