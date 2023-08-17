package com.arpak.flagquizapp

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.arpak.flagquizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val trueCounter =intent.getIntExtra("trueAnswer",0)
        binding.textViewSonuc.text = "$trueCounter TRUE ${5-trueCounter} FALSE"

        binding.textViewYuzde.text = " % ${(trueCounter * 100) / 5} SUCCESS"

        binding.buttonTekrarDene.setOnClickListener {

            startActivity(Intent(this@ResultActivity,QuizActivity::class.java))
            finish()
        }

    }


}