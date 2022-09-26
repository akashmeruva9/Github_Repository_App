package com.akash.meruva.github

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_addrepo.*
import java.lang.Exception


class Addrepo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addrepo)


        confirm_repobtn.setOnClickListener {

            val url = "https://api.github.com/repos/${owner_edittext.text.toString()}/${reponame_edittext.text.toString()}"

            val jsonObjectRequest1 = JsonObjectRequest(
                Request.Method.GET, url, null,
                { response ->

                      try {
                          val ownerobject = response.getJSONObject("owner")
                          val image_url  = ownerobject.getString("avatar_url")
                          val repo_name = response.getString("name")
                          val username = ownerobject.getString("login")
                          val description = response.getString("description")
                          val shareurl = response.getString("html_url")
                          val language = response.getString("language")
                          val datecreated = response.getString("created_at")
                          val datecreated1 = datecreated.substringBefore('T')

                          val resultIntent = Intent()
                          resultIntent.putExtra("user_name", username)
                          resultIntent.putExtra("share_url", shareurl)
                          resultIntent.putExtra("description", description)
                          resultIntent.putExtra("repo_name", repo_name)
                          resultIntent.putExtra("image_url" , image_url)
                          resultIntent.putExtra("language" , language)
                          resultIntent.putExtra("date_created" , datecreated1)

                          setResult(RESULT_OK, resultIntent)
                          finish()
                      }catch (E : Exception)
                      {
                          Toast.makeText(this, "Wrong Input", Toast.LENGTH_LONG).show()
                      }

                },
                {

                    Toast.makeText(this, "Wrong Input or No Internet", Toast.LENGTH_LONG).show()
                })

            MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest1)

        }

        addepoback.setOnClickListener {
            super.onBackPressed()
        }

    }
}