package com.jovan.github_user_app.favoriteuser

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jovan.core.domain.model.User
import com.jovan.core.ui.UserAdapter
import com.jovan.github_user_app.databinding.ActivityFavoriteUserBinding
import com.jovan.github_user_app.detail.DetailUserActivity
import com.jovan.github_user_app.di.FavoriteUserDependencies
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteUserActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel : FavoriteUserViewModel by viewModels {
        factory
    }

    private lateinit var binding: ActivityFavoriteUserBinding
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteUserComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteUserDependencies::class.java
                )
            )
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        supportActionBar!!.title = "Favorite Users"
        setContentView(binding.root)


        onProgressBar(true)

        bindAdapter()

        viewModel.getFavoriteUser().observe(this){it ->
            if (it!=null){
                val list = arrayListOf<User>()
                it.map {
                    val user = User(id = it.id, login = it.login, avatarUrl = it.avatarUrl)
                    list.add(user)
                }
                onProgressBar(false)
                adapter.setList(list)
            }
        }
    }

    private fun bindAdapter(){
        adapter = UserAdapter()
        adapter.setOnItemClickCallBack(object : UserAdapter.OnItemClickCallBack {
            override fun onItemClicked(data: User) {
                Intent(this@FavoriteUserActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                    it.putExtra(DetailUserActivity.EXTRA_ID, data.id)
                    startActivity(it)
                }
            }
        })

        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@FavoriteUserActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = adapter
        }
    }

    private fun onProgressBar(state: Boolean) {
        if (state) {
            binding.progressCircularUser.visibility = View.VISIBLE
        } else {
            binding.progressCircularUser.visibility = View.GONE
        }
    }
}