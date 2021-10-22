package com.sadraii.remotepaycomparison

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sadraii.remotepaycomparison.data.State
import com.sadraii.remotepaycomparison.ui.theme.RemotePayComparisonTheme

class MainActivity : ComponentActivity() {

    val mainViewModel by viewModels<MainViewModel>()

    @ExperimentalComposeUiApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RemotePayComparisonTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen(viewModel = mainViewModel)
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun MainScreen(viewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        StateIncomeUserInput(
            modifier = Modifier.fillMaxWidth(),
            state = viewModel.originalState,
            onStateChange = viewModel::onSelectOriginalState,
            salary = viewModel.originalSalary,
            onSalaryChange = viewModel::onEditOriginalSalary,
            stateList = viewModel.states,
            dropdownLabel = stringResource(R.string.original_state_input_label),
            textFieldLabel = stringResource(R.string.original_salary_input_label),
            textFieldPlaceholder = stringResource(R.string.salary_input_placeholder)

        )
        Spacer(modifier = Modifier.height(16.dp))
        StateIncomeUserInput(
            modifier = Modifier.fillMaxWidth(),
            state = viewModel.remoteState,
            onStateChange = viewModel::onSelectRemoteState,
            salary = viewModel.remoteSalary,
            onSalaryChange = viewModel::onEditRemoteSalary,
            stateList = viewModel.states,
            dropdownLabel = stringResource(R.string.remote_state_input_label),
            textFieldLabel = stringResource(R.string.remote_salary_input_label),
            textFieldPlaceholder = stringResource(R.string.salary_input_placeholder)
        )
        Spacer(modifier = Modifier.height(16.dp))
        val enabled = viewModel.originalState.isNotBlank() &&
                viewModel.originalSalary.isNotBlank() &&
                viewModel.remoteState.isNotBlank() &&
                viewModel.remoteSalary.isNotBlank()
        OutlinedButton(
            onClick = viewModel::onClickCalculate,
            enabled = enabled
        ) {
            Text("Calculate")
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (viewModel.taxCalculated) {
            val salaryDiff = viewModel.originalSalary.toDouble() - viewModel.remoteSalary.toDouble()
            val salaryLessMore = when {
                salaryDiff > 0 -> "$salaryDiff less"
                salaryDiff < 0 -> "${-salaryDiff} more"
                else -> "the same"
            }
            val taxLessMore = when {
                viewModel.taxDifference > 0 -> "${viewModel.taxDifference} less"
                viewModel.taxDifference < 0 -> "${-viewModel.taxDifference} more"
                else -> "the same"
            }
            val taxDifferenceSummary = stringResource(
                R.string.tax_difference_summary,
                viewModel.originalState,
                viewModel.remoteState,
                salaryLessMore,
                taxLessMore
            )
            Text(taxDifferenceSummary)
            // 50k less and 10k less
            // This leaves you with %1$s in your pocket.
            val totalDiff = salaryDiff - viewModel.taxDifference
            val totalLessMore = when {
                totalDiff > 0 -> "$totalDiff less"
                totalDiff < 0 -> "${-totalDiff} more"
                else -> "the same"
            }
            val taxDiffTotalSummary = stringResource(
                R.string.tax_difference_total_summary,
                totalLessMore
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(taxDiffTotalSummary)
        }
    }
}

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
            onValueChange = { newValue ->
                onSalaryChange(newValue.trimStart('0'))
            },
            label = { Text(textFieldLabel) },
            placeholder = { Text(textFieldPlaceholder) },
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
@Preview
@Composable
fun PreviewStateIncomeUserInput() {
    RemotePayComparisonTheme {
        // A surface container using the 'background' color from the theme
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