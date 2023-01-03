package com.example.kotlinapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.nio.charset.Charset


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var loginButton : Button = findViewById(R.id.loginButton)
        var registerButton = findViewById<Button>(R.id.registerButton)

        registerButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        })

        loginButton.setOnClickListener(View.OnClickListener {
            postVolley()
        })

    }

//    fun getVolley(){
//        // Instantiate the RequestQueue.
//        val queue = Volley.newRequestQueue(this)
//        val url: String = "https://private-4c0e8-simplestapi3.apiary-mock.com/message"
////        val url: String = "https://jsonplaceholder.typicode.com/posts"
//
//        // Request a string response from the provided URL.
//        val stringReq = StringRequest(
//            Request.Method.GET, url,
//            Response.Listener<String> { response ->
//                var strResp = response.toString()
//                Log.d("API", strResp)
//            },
//            Response.ErrorListener {Log.d("API", "that didn't work") })
//        queue.add(stringReq)
//    }

    fun postVolley() {
        val queue = Volley.newRequestQueue(this)
        val url = "http://103.165.127.197:44280/auth/login"
//        val url = "https://jsonplaceholder.typicode.com/posts"

        var emailText: EditText = findViewById(R.id.editTextUserEmail)
        var passwordText: EditText = findViewById(R.id.editTextUserPassword)

        val email = emailText.text.toString()
        val password  = passwordText.text.toString()

        val requestBody = "username=${email}" + "&password=${password}"
//        val requestBody = "title=foo" + "&body=bar" + "&userID=${userId}"
        val stringReq : StringRequest =
            object : StringRequest(Method.POST, url,
                Response.Listener { response ->
                    // response
                    var strResp = response.toString()
                    Log.d("API TESTING", strResp)
                    startActivity(Intent(this, DashboardActivity::class.java))
                }
                ,
                Response.ErrorListener { error ->
                    Log.d("API", "error => $error")
                }
            )

            {
                override fun getBody(): ByteArray {
                    return requestBody.toByteArray(Charset.defaultCharset())
                }
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers = HashMap<String, String>()
                    headers["Content-Type"] = "application/json"
                    return headers
                }
            }
        queue.add(stringReq)
    }

//    private fun postData() {
//        val queue = Volley.newRequestQueue(this)
//        val getSharePrefs = getSharedPreferences(CHECK_BOX, MODE_PRIVATE)
//        val url = "http://103.90.25.146:44280/auth/login"
//
//        var emailText = findViewById<EditText>(R.id.editTextUserEmail)
//        var passwordText = findViewById<EditText>(R.id.editTextUserPassword)
//
//        val postData = JSONObject()
//        try {
//            postData.put("email", emailText.text.toString())
//            postData.put("password", passwordText.text.toString())
//            Log.d("POST_DATA", "postData: $postData")
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }
//        val jsonObjectRequest = JsonObjectRequest(
//            Request.Method.POST,
//            url, postData, { response ->
//                sharedPreferences.edit().putBoolean("logged", true).apply()
//                var token = ""
//                try {
//                    token = response.getString("Authorization")
//                    Log.i("TOKEN", "onResponse: $token")
//                } catch (e: JSONException) {
//                    e.printStackTrace()
//                }
//                sharedPreferences?.edit()?.putString("Token", token).apply()
//                val intent = Intent(this, DashboardActivity::class.java)
//                startActivity(intent)
//                finish()
//                Log.i("POST", "onResponse: $response")
//            }) { error ->
//            Log.d("Error", "onErrorResponse: " + error.message)
//        }
//        queue.add(jsonObjectRequest)
//    }

}
