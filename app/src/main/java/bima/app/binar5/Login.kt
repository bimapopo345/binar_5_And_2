package bima.app.binar5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class Login : AppCompatActivity() {
    private lateinit var preferencesManager: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        preferencesManager = PreferencesManager(this)

        val usernameEditText = findViewById<EditText>(R.id.userlogin)
        val passwordEditText = findViewById<EditText>(R.id.userpass)
        val loginButton = findViewById<Button>(R.id.login)
        val registerButton = findViewById<Button>(R.id.register)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            preferencesManager.usernameLiveData.observe(this, { storedUsername ->
                preferencesManager.passwordLiveData.observe(this, { storedPassword ->
                    if (username == storedUsername && password == storedPassword) {
                        Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))  // Navigate to isi activity
                    } else {
                        Toast.makeText(this, "Invalid Login username or password", Toast.LENGTH_SHORT).show()
                    }
                })
            })
        }

        registerButton.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }
    }
}