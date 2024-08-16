package com.jovan.github_user_app.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.tabs.TabLayoutMediator
import com.jovan.core.domain.model.User
import com.jovan.github_user_app.R
import com.jovan.github_user_app.databinding.ActivityDetailUserBinding
import com.jovan.github_user_app.utils.SectionsPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailUserActivity : AppCompatActivity() {

    private val viewModel: DetailUserViewModel by viewModels()

    private val tabTitle = intArrayOf(R.string.tab_1, R.string.tab_2)
    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewPager: ViewPager2
    private var user: User? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        supportActionBar!!.title = "Detail User"
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        if (username != null) {
            onProgressBar(true)
            viewModel.setDetailUser(username)
        }
        val id = intent.getIntExtra(EXTRA_ID, 0)

        getDetailUser(id)
        actionSetup(username)
        setViewPager(username)
    }

    private fun getDetailUser(id: Int) {
        viewModel.getDetailUser().observe(this) {
            onProgressBar(false)

            binding.apply {
                tvDetailName.text = it.name
                tvUsernameDetail.text = it.login
                tvFollowingDetail.text = getString(R.string.following, it.following)
                tvFollowerDetail.text = getString(R.string.followers, it.followers)

                Glide.with(this@DetailUserActivity)
                    .load(it.avatarUrl)
                    .circleCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imgProfileDetail)
            }
            user = User(
                id = id,
                login = it.login,
                avatarUrl = it.avatarUrl
            )
            isFavorite(id)
        }

    }

    private fun actionSetup(username: String?) {

        binding.shareBtn.setOnClickListener {
            val shareIntent =
                Intent().apply {
                    this.action = Intent.ACTION_SEND
                    this.putExtra(Intent.EXTRA_TEXT, "https://github.com/$username")
                    this.type = "text/plain"
                }
            startActivity((shareIntent))
        }
    }

    private fun setViewPager(username: String?) {
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, bundle)
        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(tabTitle[position])
        }.attach()

        viewPager = binding.viewPager
        val params = viewPager.layoutParams
        val screenHeight = resources.displayMetrics.heightPixels

        params.height = screenHeight
        viewPager.layoutParams = params
    }

    private fun onProgressBar(state: Boolean) {
        if (state) {
            binding.progressCircularUser.visibility = View.VISIBLE
        } else {
            binding.progressCircularUser.visibility = View.GONE
        }
    }

    private fun isFavorite(id: Int) {
        viewModel.isFavoriteUser(id).observe(this@DetailUserActivity){ favorite ->
            if (favorite != null) {
                binding.toggleFavorite.apply {
                    isChecked = true

                    setOnClickListener {
                        binding.toggleFavorite.isChecked = false
                        viewModel.deleteFavoriteUser(user!!)
                    }
                }
            } else {
                binding.toggleFavorite.apply {

                    isChecked = false
                    setOnClickListener {
                        binding.toggleFavorite.isChecked = true
                        viewModel.addFavoriteUser(user!!)
                    }
                }
            }
        }

    }

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_ID = "extra_id"
    }
}


