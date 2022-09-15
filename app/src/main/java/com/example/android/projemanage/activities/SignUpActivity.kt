package com.example.android.projemanage.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.android.projemanage.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignUpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setupActionBar()
    }

    private fun setupActionBar() {
        setSupportActionBar(findViewById(R.id.toolbar_sign_up_activity))

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }
       // toolbar_sign_up_activity.setNavigationOnClickListener{onBackPressed()}

        // get reference to button
        val btn_sign_up_register = findViewById(R.id.btn_sign_up) as Button
        btn_sign_up_register.setOnClickListener(
            registerUser()
        )
    }

    private fun registerUser() {
        val name: String = findViewById<EditText>(R.id .et_name).text.toString().trim{it <= ' '}
        val email: String = findViewById<EditText>(R.id.et_email).text.toString().trim{it <= ' '}
        val password: String = findViewById<EditText>(R.id.et_password).text.toString().trim{it <= ' '}

        if(validateForm(name, email, password)){
            showProgressDialog(resources.getString(R.string.please_wait))
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                hideProgressDialog()
                if (task.isSuccessful) {
                    val firebaseUser: FirebaseUser = task.result!!.user!!
                    val registeredEmail = firebaseUser.email!!
                    Toast.makeText(
                        this, "$name you have" +
                                "succesfully registered the email " +
                                " address $registeredEmail", Toast.LENGTH_LONG
                    ).show()
                    FirebaseAuth.getInstance().signOut()
                    finish()
                } else {
                    Toast.makeText(
                        this,
                        task.exception!!.message, Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun validateForm(name:String,email: String, password: String): Boolean{
        return when {
            TextUtils.isEmpty(name)->{
                showErrorSnackBar("Please enter a name")
                false
            }
            TextUtils.isEmpty(email)->{
                showErrorSnackBar("Please enter an email address")
                false
            }
            TextUtils.isEmpty(password)->{
                showErrorSnackBar("Please enter a password")
                false
            }else->{
                true
            }
        }
    }

}

private fun Button.setOnClickListener(registerUser: Unit) {

}



