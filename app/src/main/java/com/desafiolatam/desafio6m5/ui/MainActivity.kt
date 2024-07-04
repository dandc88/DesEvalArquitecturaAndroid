package com.desafiolatam.desafio6m5.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.desafiolatam.desafio6m5.R
import com.desafiolatam.desafio6m5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerSuperior, SuperiorFragment())
            .replace(R.id.fragmentContainerInferior, InferiorFragment())
            .commit()
    }

}


