package com.jovan.github_user_app.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jovan.core.data.source.remote.network.ApiResponse
import com.jovan.core.domain.model.User
import com.jovan.core.ui.UserAdapter
import com.jovan.github_user_app.R
import com.jovan.github_user_app.databinding.ActivityMainBinding
import com.jovan.github_user_app.detail.DetailUserActivity
import com.jovan.github_user_app.setting.SettingActivity
import com.jovan.github_user_app.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import kotlin.random.Random

@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        menuBar()
        bindAdapter()

        viewModel.searchQuery.observe(this) {
            when (it) {
                is ApiResponse.Success -> {
                    onProgressBar(false)
                    Log.d("MainActivity", "Success: ${it.data.items}")
                    adapter.setList(it.data.items)
                }

                is ApiResponse.Empty -> {
                    onProgressBar(false)
                    Log.d("MainActivity", "Empty: $it")
                    showToast(this, "User Tidak Ditemukan")
                    adapter.emptyList()
                }

                is ApiResponse.Error -> {
                    onProgressBar(false)
                    showToast(this, "Terjadi Kesalahan")
                    Log.d("MainActivity", "Error: ${it.errorMessage}")
                    adapter.emptyList()
                }

                null -> adapter.emptyList()
            }
        }

        if (savedInstanceState == null) {
            onProgressBar(true)
            viewModel.setSearchUser.value = setRandomUsers()
        }
    }

    private fun bindAdapter() {
        adapter = UserAdapter()
        adapter.setOnItemClickCallBack(object : UserAdapter.OnItemClickCallBack {
            override fun onItemClicked(data: User) {
                val intent = Intent(this@MainActivity, DetailUserActivity::class.java)
                intent.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                intent.putExtra(DetailUserActivity.EXTRA_ID, data.id)
                startActivity(intent)
            }
        })

        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = adapter

            searchView.clearFocus()
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean = false

                override fun onQueryTextChange(newText: String?): Boolean {
                    lifecycleScope.launch {
                        viewModel.setSearchUser.value = newText.toString()
                    }
                    return true
                }

            })
        }
    }


    private fun onProgressBar(state: Boolean) {
        if (state) {
            binding.progressCircularUser.visibility = View.VISIBLE
        } else {
            binding.progressCircularUser.visibility = View.GONE
        }
    }

    private fun menuBar() {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.title = "Search Github User"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate menu resource
        menuInflater.inflate(R.menu.menu_bar, menu)
        return true
    }

    private fun setRandomUsers(): String {
        val alphabet = ('a'..'z').toList()
        val randomIndex = Random.nextInt(alphabet.size)
        val randomUsers = alphabet[randomIndex].toString()

        return randomUsers
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.btn_setting -> {
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
                true
            }

            R.id.favorite_btn -> {
                val uri = Uri.parse("github_user_app://favoriteuser")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}