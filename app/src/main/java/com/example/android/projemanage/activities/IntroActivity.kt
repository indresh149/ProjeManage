package com.example.android.projemanage.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import com.example.android.projemanage.R

class IntroActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val btn_click_me_signIn = findViewById(R.id.btn_sign_in_intro) as Button
        btn_click_me_signIn.setOnClickListener{
            startActivity(Intent(this, SignInActivity::class.java))
        }

        val btn_click_me = findViewById(R.id.btn_sign_up_intro) as Button
        btn_click_me.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
        }

    }
}