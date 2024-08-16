package com.jovan.github_user_app.utils

import android.content.Context
import android.widget.Toast

fun showToast(context: Context, toast: String){
    Toast.makeText(context, toast, Toast.LENGTH_SHORT).show()
}