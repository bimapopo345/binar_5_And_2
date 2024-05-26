package bima.app.binar5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Register : AppCompatActivity() {
    private lateinit var preferencesManager: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        preferencesManager = PreferencesManager(this)

        val usernameEditText = findViewById<EditText>(R.id.userdaftar)
        val passwordEditText = findViewById<EditText>(R.id.passdaftar)
        val registerButton = findViewById<Button>(R.id.daftar)

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                preferencesManager.saveUser(username, password)
                // Switch back to the Main (UI) thread
                runOnUiThread {
                    Toast.makeText(this@Register, "User registered successfully", Toast.LENGTH_LONG).show()
                    navigateToLogin()
                }
            }
        }
    }

    private fun navigateToLogin() {
        // Intent to start the Login activity
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish() // Finish the current activity to remove it from the back stack
    }
}