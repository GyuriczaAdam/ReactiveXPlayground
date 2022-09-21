package hu.gyuriczaadam.reactivexpalygorund.presentation

import androidx.lifecycle.ViewModel
import hu.gyuriczaadam.reactivexpalygorund.di.ViewModelScope

import toothpick.InjectConstructor
import javax.inject.Singleton

@Singleton
@InjectConstructor
@ViewModelScope
class MainViewModel() : ViewModel() {
    fun returnRolledNum():Int{
        return 10
    }
}