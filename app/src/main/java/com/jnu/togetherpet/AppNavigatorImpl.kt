package com.jnu.togetherpet

import android.content.Context
import android.content.Intent
import com.jnu.togetherpet.ui.activity.dashboard.DashboardActivity
import com.jnu.ui.AppNavigator
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppNavigatorImpl @Inject constructor(
    @ApplicationContext private val context: Context
): AppNavigator {
    override fun navigateToDashboard(): Intent = Intent(context, DashboardActivity::class.java)
}