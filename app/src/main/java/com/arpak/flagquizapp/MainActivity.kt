package com.arpak.flagquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arpak.flagquizapp.databinding.ActivityMainBinding
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        databaseToCopy()

        binding.buttonBasla.setOnClickListener{

            startActivity(Intent(this@MainActivity,QuizActivity::class.java))
        }



    }


    fun databaseToCopy(){
        val copyHelper = DatabaseCopyHelper(this@MainActivity)
        try {

            copyHelper.createDataBase()
            copyHelper.openDataBase()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}