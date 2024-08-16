

package com.jovan.github_user_app.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jovan.github_user_app.detail.follower.FollowerFragment
import com.jovan.github_user_app.detail.following.FollowingFragment

class SectionsPagerAdapter(fragmentActivity: FragmentActivity, data: Bundle) :
    FragmentStateAdapter(fragmentActivity) {

    private val fragmentBundle: Bundle = data

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FollowingFragment().apply { arguments = fragmentBundle }
            1 -> FollowerFragment().apply { arguments = fragmentBundle }
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}