package com.jnu.togetherpet.ui.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jnu.togetherpet.ui.fragment.community.CommunityFragment
import com.jnu.togetherpet.ui.fragment.diary.DiaryFragment
import com.jnu.home.HomeFragment

class DashboardViewModel : ViewModel() {
    private val _selectedFragment = MutableLiveData<Fragment>()
    val selectedFragment: LiveData<Fragment> get() = _selectedFragment

    fun selectFragmentToShow(itemId: Int) {
        val fragment = when (itemId) {
            com.jnu.ui.R.id.bottom_home -> com.jnu.home.HomeFragment()
            com.jnu.ui.R.id.bottom_community -> CommunityFragment()
            com.jnu.ui.R.id.bottom_searching -> com.jnu.searching.SearchingPetFragment()
            com.jnu.ui.R.id.bottom_diary -> DiaryFragment()
            com.jnu.ui.R.id.bottom_walking -> com.jnu.walking.WalkingPetFragment()
            else -> com.jnu.home.HomeFragment()
        }
        _selectedFragment.value = fragment
    }
}