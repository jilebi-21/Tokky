package com.ps.tokky.activities

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.ps.tokky.utils.AppPreferences

open class BaseActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {
    protected val preferences by lazy { AppPreferences.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        preferences.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == AppPreferences.KEY_ALLOW_SCREENSHOTS) {
            if (this is SettingsActivity)
                Handler(Looper.getMainLooper()).postDelayed({
                    recreate()
                }, 1000)
            else recreate()
        }
    }
}