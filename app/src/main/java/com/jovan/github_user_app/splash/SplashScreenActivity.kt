package com.jovan.github_user_app.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.jovan.github_user_app.R
import com.jovan.github_user_app.databinding.ActivitySplashScreenBinding
import com.jovan.github_user_app.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class, kotlinx.coroutines.FlowPreview::class)
@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    private val viewModel : SplashViewModel by viewModels()
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

            binding = ActivitySplashScreenBinding.inflate(layoutInflater)
            setContentView(binding.root)
            supportActionBar?.hide()

            Glide.with(this)
                .load(R.mipmap.ic_launcher_round)
                .circleCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imgSplash)

            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed({
                val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000)

        }
    }
}
