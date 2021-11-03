package com.sadraii.remotepaycomparison

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sadraii.remotepaycomparison.data.State
import com.sadraii.remotepaycomparison.ui.theme.RemotePayComparisonTheme

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun StateIncomeUserInput(
    modifier: Modifier = Modifier,
    state: String,
    onStateChange: (String) -> Unit,
    salary: String,
    onSalaryChange: (String) -> Unit,
    stateList: List<State>,
    dropdownLabel: String,
    textFieldLabel: String,
    textFieldPlaceholder: String
) {
    var expanded by remember { mutableStateOf(false) }
    Column(modifier = modifier) {
        ExposedDropdownMenuBox(
            modifier = modifier,
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            OutlinedTextField(
                modifier = modifier,
                readOnly = true,
                value = state,
                onValueChange = onStateChange,
                label = { Text(dropdownLabel) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                }
//                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                stateList.forEach { selectedOption ->
                    DropdownMenuItem(
                        onClick = {
                            onStateChange(selectedOption.name)
                            expanded = false
                        }
                    ) {
                        Text(text = selectedOption.name)
                    }
                }
            }
        }
        val controller = LocalSoftwareKeyboardController.current
        OutlinedTextField(
            modifier = modifier,
            value = salary,
            onValueChange = { newText ->
                val newValue = newText.replace(Regex("[ ,-]"), "")
                onSalaryChange(
                    when {
                        newValue.isBlank() || newValue == "0" -> "0"
                        else -> newValue.trimStart('0')
                    }
                )
            },
            label = { Text(textFieldLabel) },
            placeholder = { Text(textFieldPlaceholder) },
//            visualTransformation = {
//                TransformedText(
//                    AnnotatedString("$${it.text}"),
//                    OffsetMapping.Identity
//                )
//            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { controller?.hide() }
            )
        )
    }
}

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Preview(name = "Light theme", widthDp = 360, heightDp = 640)
@Preview(name = "Dark theme", widthDp = 360, heightDp = 640, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewStateIncomeUserInput() {
    RemotePayComparisonTheme {
        Surface(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            color = MaterialTheme.colors.background
        ) {
            StateIncomeUserInput(
                modifier = Modifier.fillMaxWidth(),
                state = "",
                onStateChange = {},
                salary = "",
                onSalaryChange = {},
                stateList = listOf<State>(),
                dropdownLabel = "State",
                textFieldLabel = "Salary",
                textFieldPlaceholder = "Enter"
            )
        }
    }
}