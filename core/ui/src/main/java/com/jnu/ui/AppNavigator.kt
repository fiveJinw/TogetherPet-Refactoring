package com.jnu.ui

import android.content.Intent
import androidx.fragment.app.Fragment

interface AppNavigator {
    fun navigateToDashboard(): Intent
    fun navigateToSearchingPage(): Fragment
}