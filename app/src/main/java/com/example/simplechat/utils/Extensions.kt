package com.example.simplechat.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Context.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

fun AppCompatActivity.openFragment(
    @IdRes containerViewId: Int,
    fragment: Fragment,
    addToBackStack: Boolean = false,
    tag: String? = null
): Fragment {
    return fragment.also {
        supportFragmentManager.beginTransaction().apply {
            replace(containerViewId, it, tag)
            if (addToBackStack) {
                addToBackStack(null)
            }
            commit()
        }
    }
}

fun Context.openActivity(className: Class<*>, bundleKey: String = "", bundle: Bundle? = null) {

    this.startActivity(
        Intent(this, className)
            .apply {
                putExtra(bundleKey, bundle)
            }
    )
}
