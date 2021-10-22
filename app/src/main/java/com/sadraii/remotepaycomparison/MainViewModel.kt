package com.sadraii.remotepaycomparison

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sadraii.remotepaycomparison.data.CalculatedTax
import com.sadraii.remotepaycomparison.data.DataUtils.calculateTax
import com.sadraii.remotepaycomparison.data.StateData
import com.sadraii.remotepaycomparison.data.StateData.getState

class MainViewModel() : ViewModel() {

    var originalState by mutableStateOf("")
        private set
    var originalSalary by mutableStateOf("")
        private set
    var remoteState by mutableStateOf("")
        private set
    var remoteSalary by mutableStateOf("")
        private set

    val states by mutableStateOf(StateData.states)

    var originalTax by mutableStateOf<CalculatedTax>(CalculatedTax())
        private set
    var remoteTax by mutableStateOf<CalculatedTax>(CalculatedTax())
        private set

    var taxCalculated by mutableStateOf(false)
        private set
    var taxDifference by mutableStateOf<Double>(0.0)
        private set

    fun onSelectOriginalState(state: String) {
        originalState = state
    }

    fun onEditOriginalSalary(salary: String) {
        originalSalary = salary
    }

    fun onSelectRemoteState(state: String) {
        remoteState = state
    }

    fun onEditRemoteSalary(salary: String) {
        remoteSalary = salary
    }

    fun onClickCalculate() {
        originalTax = calculateTax(originalSalary.toInt(), getState(originalState))
        remoteTax = calculateTax(remoteSalary.toInt(), getState(remoteState))
        taxDifference = originalTax.total - remoteTax.total
        taxCalculated = true
    }
}