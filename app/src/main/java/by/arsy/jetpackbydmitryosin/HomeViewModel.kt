package by.arsy.jetpackbydmitryosin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class HomeViewModel : ViewModel() {

    private val _uiLoading = MutableStateFlow(value = false)

    val uiState = _uiLoading.asStateFlow()

    fun startLoading() {
        _uiLoading.value = true
        viewModelScope.launch {
            delay(duration = 1800.milliseconds)
            _uiLoading.value = false
        }
    }
}