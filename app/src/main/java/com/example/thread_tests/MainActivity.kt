package com.example.handlerTests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.os.Handler
import android.view.View
import com.example.handlerTests.databinding.ActivityMainBinding

private lateinit var binding : ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.mainButton.text = getString(R.string.buttonText)
        binding.resetButton.text = getString(R.string.resetText)
        binding.mainButton.setOnClickListener {
            buttonFunction()
        }
        binding.resetButton.setOnClickListener {
            if(counter == 1){
                binding.Text.text = getString(R.string.counterWarning)
            } else {
                binding.Text.text = getString(R.string.resetText)
                counter = 1
            }
        }
    }

    private var counter = 1
    private var timeOrTimes : String? = null

    private fun buttonFunction() {
        var state = false

        binding.Text.text = getString(R.string.fetchingText)
        loadingBarOnOffFunction(state)
        Handler(Looper.getMainLooper()).postDelayed({
            state = true
            loadingBarOnOffFunction(state)
            if (counter < 2){
                timeOrTimes = "time"
            } else{
                timeOrTimes = "times"
            }
            binding.Text.text = getString(R.string.Text, counter.toString(), timeOrTimes)
            binding.mainButton.text = getString(R.string.buttonTextAfter)
            counter += 1
        },3000)
    }

    private fun loadingBarOnOffFunction(state: Boolean){
        if (!state){
            binding.loading.visibility = View.VISIBLE
            binding.mainButton.visibility = View.INVISIBLE
        }
        else{
            binding.loading.visibility = View.INVISIBLE
            binding.mainButton.visibility = View.VISIBLE
        }
    }
}