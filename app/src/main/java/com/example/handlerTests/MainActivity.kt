package com.example.handlerTests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.os.Handler
import android.view.View
import com.example.handlerTests.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
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
                binding.infoBox.text = getString(R.string.counterWarning)
            } else {
                binding.infoBox.text = getString(R.string.counterReset)
                counter = 1
            }
        }
    }

    private var counter = 1
    private var timeOrTimes : String? = null
    private fun buttonFunction() {
        var state = false
        binding.infoBox.text = getString(R.string.fetchingText)
        loadingBarOnOffFunction(state)
        Handler(Looper.getMainLooper()).postDelayed({
            state = true
            loadingBarOnOffFunction(state)
            if (counter < 2){
                timeOrTimes = "time"
            }
            timeOrTimes = "times"
            binding.infoBox.text = getString(R.string.infoText, counter.toString(), timeOrTimes)
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