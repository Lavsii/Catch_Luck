package com.lavsii.tyl.utils

import com.lavsii.tyl.data.Countries

sealed class AppState {
    data class Success(val countryData: List<Countries>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
