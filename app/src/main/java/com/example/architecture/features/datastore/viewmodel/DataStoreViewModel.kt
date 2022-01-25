package com.example.architecture.features.datastore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecture.domain.model.User
import com.example.architecture.features.datastore.usecase.DataStoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val dataStoreUseCase: DataStoreUseCase
) : ViewModel() {
    val firstName = MutableStateFlow("")
    val lastName = MutableStateFlow("")
    private var _mainUser = MutableStateFlow<User?>(null)
    val mainUser: StateFlow<User?> = _mainUser

    fun saveUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val user = User(firstName = firstName.value, lastName = lastName.value)
            dataStoreUseCase.saveUser(user)
            _mainUser.emit(user)
        }
    }

    fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            _mainUser.emit(dataStoreUseCase.getUser())
        }
    }
}