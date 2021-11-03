package com.sadraii.remotepaycomparison

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import java.math.BigDecimal
import java.math.RoundingMode

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun MainScreen(viewModel: MainViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        MainAppBar()
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

        if (viewModel.isTaxCalculated) {
            val salaryDiff =
                BigDecimal(viewModel.originalSalary) - BigDecimal(viewModel.remoteSalary)
            val salaryLessMore = when {
                salaryDiff > BigDecimal.ZERO -> "${salaryDiff.trimZero()} less"
                salaryDiff < BigDecimal.ZERO -> "${-salaryDiff.trimZero()} more"
                else -> "the same"
            }
            val taxLessMore = when {
                viewModel.taxDifference > BigDecimal.ZERO -> "${viewModel.taxDifference.trimZero()} less"
                viewModel.taxDifference < BigDecimal.ZERO -> "${-viewModel.taxDifference.trimZero()} more"
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
            val totalDiff = salaryDiff - viewModel.taxDifference
            val totalLessMore = when {
                totalDiff > BigDecimal.ZERO -> "${totalDiff.trimZero()} less"
                totalDiff < BigDecimal.ZERO -> "${-totalDiff.trimZero()} more"
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

@Composable
private fun MainAppBar() {
    // TODO check Material Theme colors matches my theme
    Surface(
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface
    ) {
        TopAppBar(
            title = { Text(text = stringResource(R.string.app_name)) },
            elevation = 0.dp,
            backgroundColor = Color.Transparent,
            modifier = Modifier.statusBarsPadding()
        )
    }
}

/**
 * If it's an integer, remove trailing zeros. Otherwise, round to 2 decimals.
 */
fun BigDecimal.trimZero(): BigDecimal {
    return if (isInt()) setScale(0) else setScale(2, RoundingMode.HALF_UP)
}

fun BigDecimal.isInt(): Boolean {
    return signum() == 0 || scale() <= 0 || stripTrailingZeros().scale() <= 0
}