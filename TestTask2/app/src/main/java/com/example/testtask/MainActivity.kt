package com.example.testtask

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    companion object {
        val FIRST_USER = User(
            "leha228@mail.ru", "Leha", "Pupkin",
            R.drawable.man1, "228Leha"
        )
        val SECOND_USER = User(
            "dedAfanasiy@rambler.com", "Afanasiy",
            "Lavrentev", R.drawable.man2, "Soyka1934"
        )
        val THIRD_USER = User(
            "chelovek@gmail.com", "Chelovek", "Obichniy",
            R.drawable.man3, "Imn0tAlien"
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val index = loadAuth()
        if (index == 0)
            setContentView(R.layout.activity_user_cabinet1)
        if (index == 1)
            setContentView(R.layout.activity_user_cabinet2)
        if (index == 2)
            setContentView(R.layout.activity_user_cabinet3)
        setContentView(R.layout.activity_main)
    }

    fun pushSignInButton(view: View) {
        var email = editText.text
        var password = editText2.text

        if (checkData(email, password)) {
            val signingUser = chekExistingUser(email)
            if (signingUser != null)
                goToUserCabinetScreen(signingUser)
        }
    }

    fun goToUserCabinetScreen(userIndex: Int) {
        val ucsIntent = Intent(this, UserCabinetActivity::class.java)
        ucsIntent.putExtra("index", userIndex)
        startActivity(ucsIntent)
    }

    fun chekExistingUser(email: Editable): Int {
        val emailString = email.toString()
        val usersArray = arrayOf<User>(FIRST_USER, SECOND_USER, THIRD_USER)
        for (i in usersArray.indices)
            if (emailString.equals(usersArray.get(i).getEmail()))
                return i
        /*if (emailString.equals(FIRST_USER.getEmail()) || emailString.equals(SECOND_USER.getEmail())
         || emailString.equals(THIRD_USER.getEmail()))
            return true*/
        toastNoUser()
        return -1
    }

    fun checkData(email: Editable, password: Editable): Boolean {

        if (!regEmail(email)) {
            toastNotCorrectEmail()
            return false
        }
        if (!regPassword(password)) {
            toastNotCorrectPassword()
            return false
        }
        return true
    }

    fun toastNoUser() {
        val toast = Toast.makeText(this, "User is not exist", Toast.LENGTH_LONG)
        toast.show()
    }

    fun toastNotCorrectEmail() {
        val toast = Toast.makeText(this, "Not correct email", Toast.LENGTH_LONG)
        toast.show()
    }

    fun toastNotCorrectPassword() {
        val toast = Toast.makeText(this, "Not correct password", Toast.LENGTH_LONG)
    }

    fun regEmail(string: Editable): Boolean {
        var p: Pattern = Pattern.compile("\\A[^@]+@([^@\\.]+\\.)+[^@\\.]+\\z")
        var m: Matcher = p.matcher(string)
        return m.matches()
    }

    fun regPassword(string: Editable): Boolean {
        var p: Pattern =
            Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#\$%^&*]{6,}")
        var m: Matcher = p.matcher(string)
        return m.matches()
    }

    fun goToStartScreen(view: View) {
        val ucsIntent = Intent(this, MainActivity::class.java)
        startActivity(ucsIntent)
    }

    fun loadAuth(): Int{
        var sPref = getPreferences(Context.MODE_PRIVATE)
        return sPref.getInt("userIndex", -1)
    }
}
