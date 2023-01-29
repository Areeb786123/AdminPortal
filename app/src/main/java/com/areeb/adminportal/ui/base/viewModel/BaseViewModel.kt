package com.areeb.adminportal.ui.base.viewModel

import androidx.lifecycle.ViewModel
import com.areeb.adminportal.data.preferences.UserSharedPreferences
import javax.inject.Inject

open class BaseViewModel : ViewModel() {
    @Inject
    lateinit var sharedPreferences: UserSharedPreferences
}
