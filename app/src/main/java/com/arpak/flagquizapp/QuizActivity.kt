package com.arpak.flagquizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.arpak.flagquizapp.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private lateinit var answer: ArrayList<Bayraklar>
    private lateinit var falseAnswer: ArrayList<Bayraklar>
    private lateinit var trueAnswer: Bayraklar
    private lateinit var allOption: HashSet<Bayraklar>
    private lateinit var dataHelper: DataHelper

    private var answerCounter: Int = 0
    private var trueCounter: Int = 0
    private var wrongCounter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataHelper = DataHelper(this@QuizActivity)
        answer = Bayraklardao().bring5RandomFlags(dataHelper)

        answerLoading()

        binding.buttonA.setOnClickListener {
            trueControl(binding.buttonA)
            answerCounterControl()

        }

        binding.buttonB.setOnClickListener {

            trueControl(binding.buttonB)
            answerCounterControl()
        }

        binding.buttonC.setOnClickListener {

            trueControl(binding.buttonC)
            answerCounterControl()
        }

        binding.buttonD.setOnClickListener {

            trueControl(binding.buttonD)
            answerCounterControl()
        }

    }

    fun answerLoading(){
        binding.textViewSoruSayisi.text = "${answerCounter+1}. Answer"

        trueAnswer = answer.get(answerCounter)

        binding.imageViewBayrak.setImageResource(resources.getIdentifier(trueAnswer.bayrak_resim,"drawable",packageName))

        falseAnswer = Bayraklardao().bring3FalseOptionRandom(dataHelper,trueAnswer.bayrak_id)

        allOption = HashSet()
        allOption.add(trueAnswer)
        allOption.add(falseAnswer.get(0))
        allOption.add(falseAnswer.get(1))
        allOption.add(falseAnswer.get(2))

        binding.buttonA.text = allOption.elementAt(0).bayrak_ad
        binding.buttonB.text = allOption.elementAt(1).bayrak_ad
        binding.buttonC.text = allOption.elementAt(2).bayrak_ad
        binding.buttonD.text = allOption.elementAt(3).bayrak_ad
    }


    fun answerCounterControl(){
        answerCounter++
        if (answerCounter != 5){
            answerLoading()

        }else{
            val intent = Intent(this@QuizActivity, ResultActivity::class.java)
            intent.putExtra("trueAnswer",trueCounter)
            startActivity(intent)
            finish()
        }

    }

    fun trueControl(button: Button){

        val buttonOnWriting = button.text.toString()
        val trueAnswerText = trueAnswer.bayrak_ad

        if (buttonOnWriting == trueAnswerText){
            trueCounter++
        }else {
            wrongCounter++
        }

        binding.textViewDogru.text = "Right : $trueCounter"
        binding.textViewYanlS.text = "False : $wrongCounter"
    }

}