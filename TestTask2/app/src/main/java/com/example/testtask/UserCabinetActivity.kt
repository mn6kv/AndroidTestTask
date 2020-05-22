package com.example.testtask

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class UserCabinetActivity : AppCompatActivity() {

/*    companion object {
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
    }*/

    //lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var args = intent.extras
        var index = args?.get("index")

        if (index == 0) {
            setContentView(R.layout.activity_user_cabinet1)
            saveAuth(0)
        }
        if (index == 1) {
            setContentView(R.layout.activity_user_cabinet2)
            saveAuth(1)
        }
        if (index == 2) {
            setContentView(R.layout.activity_user_cabinet3)
            saveAuth(2)
        }
    }

    fun goToStartScreen(view: View) {
        val ucsIntent = Intent(this, MainActivity::class.java)
        startActivity(ucsIntent)
    }

    fun saveAuth(numUser: Int) {
        var sPref = getPreferences(Context.MODE_PRIVATE)
        var ed = sPref.edit()
        ed.putInt("userIndex", numUser)
        ed.apply()
    }

    fun loadAuth(): Int{
        var sPref = getPreferences(Context.MODE_PRIVATE)
        return sPref.getInt("userIndex", -1)
    }
}
