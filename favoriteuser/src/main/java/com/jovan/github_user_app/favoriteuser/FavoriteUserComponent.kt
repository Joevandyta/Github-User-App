package com.jovan.github_user_app.favoriteuser

import android.content.Context
import com.jovan.github_user_app.di.FavoriteUserDependencies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteUserDependencies::class])
interface FavoriteUserComponent {

    fun inject(activity: FavoriteUserActivity)

    @Component.Builder
    interface Builder{
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteUserDependencies: FavoriteUserDependencies): Builder
        fun build(): FavoriteUserComponent

    }
}