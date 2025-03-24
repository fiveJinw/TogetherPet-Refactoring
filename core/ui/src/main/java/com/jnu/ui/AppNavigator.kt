package com.jnu.ui

import android.content.Intent

interface AppNavigator {
    fun navigateToDashboard(): Intent
    fun navigateToSearchingPage(): Intent
}