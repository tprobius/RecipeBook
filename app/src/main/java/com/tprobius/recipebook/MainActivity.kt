package com.tprobius.recipebook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tprobius.recipebook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        private var newSession = true

        fun getSessionState(): Boolean = newSession

        fun setSessionState() {
            newSession = !newSession
        }
    }
}