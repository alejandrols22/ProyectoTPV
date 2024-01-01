package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityRegisterBinding // Asegúrate de que este import corresponda al nombre de tu archivo de layout.

class RegisterActivity : AppCompatActivity() {
    // Declara la variable de binding
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicializa el binding
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener {
            // Aquí iría la lógica para registrar al nuevo usuario
        }
    }
}

