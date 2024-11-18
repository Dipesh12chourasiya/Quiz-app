package com.example.quizapp

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

//    private val questions = arrayOf("What is the build in database in Android Studio?","What is the full form of APK in Android Development?","In which year, first android was released by Google?")
//
//    private val options = arrayOf(arrayOf("MySQL","SQLite","Firebase"),
//        arrayOf("Android Programming Interface", "Android Package Information", "Application Programming Interface"),
//        arrayOf("2010","2000","2008"))
//
//    private val correctAnswers = arrayOf(1,2,2)


    private val questions = arrayOf(
        "What is the base language used in Android development?",
        "What is the entry point of an Android application?",
        "Which XML attribute is used to set the text in a TextView?",
        "Which component is used for background processing in Android?",
        "What is the default file format of Android resources?",
        "Which version of Android was known as 'Cupcake'?",
        "What is the use of AndroidManifest.xml in Android projects?",
        "Which layout arranges its children in a single row or column?",
        "What does 'dp' stand for in Android development?",
        "Which library is used for dependency injection in Android?",
        "Which Android component is responsible for managing the lifecycle of an app?",
        "Which tool is used to monitor logs in Android development?",
        "What is the main purpose of ViewModel in Android architecture?",
        "Which method is used to inflate a layout in Android?",
        "Which Android Studio feature helps detect and fix coding issues?",
        "What is the primary programming language for Android?",
        "What is the file extension for Android applications?"
    )

    private val options = arrayOf(
        arrayOf("Python", "Java", "C++"),
        arrayOf("Activity", "Service", "Fragment"),
        arrayOf("android:text", "text:android", "set:text"),
        arrayOf("Intent", "Service", "BroadcastReceiver"),
        arrayOf(".json", ".xml", ".txt"),
        arrayOf("Android 2.2", "Android 1.5", "Android 4.0"),
        arrayOf("It defines application permissions", "It stores UI components", "It manages project builds"),
        arrayOf("LinearLayout", "RelativeLayout", "ConstraintLayout"),
        arrayOf("Data Pixels", "Density-independent Pixels", "Device Pixels"),
        arrayOf("ButterKnife", "Dagger", "Retrofit"),
        arrayOf("Activity", "Application", "FragmentManager"),
        arrayOf("Logcat", "ADB", "Gradle"),
        arrayOf("To bind UI components", "To manage UI-related data", "To handle background threads"),
        arrayOf("setLayout()", "inflateLayout()", "LayoutInflater.inflate()"),
        arrayOf("Debugger", "Lint", "Profiler"),
        arrayOf("C++", "Kotlin", "Swift"),
        arrayOf(".apk", ".exe", ".jar")
    )

    private val correctAnswers = arrayOf( 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 2, 1, 1, 0 )


    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuestion()

        binding.option1Button.setOnClickListener{
            checkAnswer(0)
        }
        binding.option2Button.setOnClickListener{
            checkAnswer(1)
        }
        binding.option3Button.setOnClickListener{
            checkAnswer(2)
        }

        binding.restartButton.setOnClickListener{
            restartQuiz()
        }
    }

    private fun correctButtonColors(buttonIndex: Int){
        when(buttonIndex){
            0 -> binding.option1Button.setBackgroundColor(Color.GREEN)
            1 -> binding.option2Button.setBackgroundColor(Color.GREEN)
            2 -> binding.option3Button.setBackgroundColor(Color.GREEN)
        }
    }

    private fun wrongButtonColors(buttonIndex: Int) {
        when(buttonIndex){
            0 -> binding.option1Button.setBackgroundColor(Color.RED)
            1 -> binding.option2Button.setBackgroundColor(Color.RED)
            2 -> binding.option3Button.setBackgroundColor(Color.RED)
        }
    }

    private fun resetButtonColors(){
        binding.option1Button.setBackgroundColor(Color.rgb(50,59,96))
        binding.option2Button.setBackgroundColor(Color.rgb(50,59,96))
        binding.option3Button.setBackgroundColor(Color.rgb(50,59,96))
    }

    private fun showResult(){
        Toast.makeText(this,"Your Score: $score out of ${questions.size}",Toast.LENGTH_LONG)
        binding.restartButton.isEnabled = true
    }

    private fun displayQuestion(){
        binding.questionText.text = questions[currentQuestionIndex]

        binding.option1Button.text = options[currentQuestionIndex][0]
        binding.option2Button.text = options[currentQuestionIndex][1]
        binding.option3Button.text = options[currentQuestionIndex][2]
        resetButtonColors()
    }

    private fun checkAnswer(selectedAnswerIndex: Int){
        val correctAnswerIndex = correctAnswers[currentQuestionIndex];

        if(selectedAnswerIndex == correctAnswerIndex){
            score++;
            correctButtonColors(selectedAnswerIndex)
        } else {
            wrongButtonColors(selectedAnswerIndex)
            correctButtonColors(correctAnswerIndex)
        }
        if(currentQuestionIndex < questions.size - 1){
            currentQuestionIndex++
            binding.questionText.postDelayed({displayQuestion()}, 1000)
        } else {
            showResult()
        }
    }

    private fun restartQuiz(){
        currentQuestionIndex = 0
        score = 0
        displayQuestion()
        binding.restartButton.isEnabled = false
    }
}