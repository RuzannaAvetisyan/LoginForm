package ruzanna.game.loginform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val login = findViewById<Button>(R.id.login)
        val eMail = findViewById<EditText>(R.id.e_mail)
        val password = findViewById<EditText>(R.id.password)

        login.setOnClickListener{
            val eMailText = eMail.text.toString()
            val passwordText = password.text.toString()
            if(isValidEmail(eMailText) && isValidPassword(passwordText)){
                Toast.makeText(applicationContext, "E-mail: $eMailText and Password are valid.",
                    Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(applicationContext, "E-mail: $eMailText or Password is invalid.",
                    Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val valid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        return valid && (email.endsWith("gmail.com") || email.endsWith("mail.ru"))
    }

    private fun isValidPassword(password:String): Boolean {

        if (password.length < 8) {
            return false
        }

        var exp = ".*[0-9].*"
        var pattern = Pattern.compile(exp, Pattern.CASE_INSENSITIVE)
        var matcher = pattern.matcher(password)
        if (!matcher.matches()) {
            return false
        }

        exp = ".*[A-Z].*"
        pattern = Pattern.compile(exp)
        matcher = pattern.matcher(password)
        if (!matcher.matches()) {
            return false
        }

        exp = ".*[a-z].*"
        pattern = Pattern.compile(exp)
        matcher = pattern.matcher(password)
        if (!matcher.matches()) {
            return false
        }

        exp = ".*[~!@#\$%\\^&*()\\-_=+\\|\\[{\\]};:'\",<.>/?].*"
        pattern = Pattern.compile(exp)
        matcher = pattern.matcher(password)
        if (!matcher.matches()) {
            return false
        }

        return true
    }
}
