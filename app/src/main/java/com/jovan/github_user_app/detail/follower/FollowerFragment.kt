package com.jovan.github_user_app.detail.follower

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jovan.github_user_app.R
import com.jovan.core.domain.model.User
import com.jovan.core.ui.UserAdapter
import com.jovan.github_user_app.databinding.FragmentFollowerBinding
import com.jovan.github_user_app.detail.DetailUserActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowerFragment : Fragment(R.layout.fragment_follower) {
    private val viewModel: FollowersViewModel  by viewModels()

    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: UserAdapter
    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString(DetailUserActivity.EXTRA_USERNAME).toString()
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFollowerBinding.bind(view)

        adapter = UserAdapter()
        adapter.setOnItemClickCallBack(object : UserAdapter.OnItemClickCallBack {
            override fun onItemClicked(data: User) {
                Toast.makeText(requireContext(), "${data.login} Clicked", Toast.LENGTH_SHORT).show()
            }
        })

        binding.apply {
            rvUser.setHasFixedSize(true)
            rvUser.layoutManager = LinearLayoutManager(activity)
            rvUser.adapter = adapter
        }

        onProgressBar(true)
        viewModel.setListFollowers(username)
        viewModel.getListFollowers().observe(viewLifecycleOwner) {
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