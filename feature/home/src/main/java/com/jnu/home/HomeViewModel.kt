package com.jnu.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jnu.data.repo.MissingRepository
import com.jnu.domain.FetchMissingDataUseCase
import com.jnu.model.entities.MissingEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val fetchMissingDataUseCase: FetchMissingDataUseCase
, private val missingRepository: MissingRepository) : ViewModel() {
    val missingReports: StateFlow<List<MissingEntity>> = missingRepository.getAllMissingReports()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun fetchMissingReports(latitude: Double, longitude: Double) {
        Log.d("child", "Missing Data Fetch")
        viewModelScope.launch(Dispatchers.IO) {
            fetchMissingDataUseCase(latitude, longitude)
        }
    }

}