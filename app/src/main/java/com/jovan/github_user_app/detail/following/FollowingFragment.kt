package com.jovan.github_user_app.detail.following

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jovan.core.domain.model.User
import com.jovan.core.ui.UserAdapter
import com.jovan.github_user_app.R
import com.jovan.github_user_app.databinding.FragmentFollowingBinding
import com.jovan.github_user_app.detail.DetailUserActivity
import com.jovan.github_user_app.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowingFragment : Fragment(R.layout.fragment_following) {
    private val viewModel: FollowingViewModel by viewModels()
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: UserAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFollowingBinding.bind(view)

        val args = arguments
        username = args?.getString(DetailUserActivity.EXTRA_USERNAME).toString()

        adapter = UserAdapter()
        binding.apply {
            rvUser.setHasFixedSize(true)
            rvUser.layoutManager = LinearLayoutManager(activity)
            rvUser.adapter = adapter
        }
        adapter.setOnItemClickCallBack(object : UserAdapter.OnItemClickCallBack {
            override fun onItemClicked(data: User) {
                showToast(requireContext(), "${data.login} Clicked")
            }
        })
        
        onProgressBar(true)
        viewModel.setListFollowing(username)
        viewModel.getListFollowing().observe(viewLifecycleOwner) {
            if (it != null){
                adapter.setList(ArrayList(it))
                onProgressBar(false)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onProgressBar(state: Boolean) {
        if (state) {
            binding.progressCircularUser.visibility = View.VISIBLE
        } else {
            binding.progressCircularUser.visibility = View.GONE
        }
    }
}