package com.akash.meruva.github

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.ItemTouchHelper


class MainActivity : AppCompatActivity() {

    var requestcode = 123
    lateinit var viewmodel: repoViewmodel
    lateinit var adapter12: recyclerviewadapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.repository_recyclerview)
        val addrepobtn = findViewById<Button>(R.id.addrepo_btn)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter12 = recyclerviewadapter(this)
        recyclerView.adapter = adapter12



        addrepobtn.setOnClickListener {
            val newtaskintent = Intent(this, Addrepo::class.java)
            newtaskintent.putExtra("classifier", "new")
            startActivityForResult(newtaskintent, requestcode)

        }

        viewmodel = ViewModelProvider(this)[repoViewmodel::class.java]
        viewmodel.allrepos.observe(this) {
            adapter12.updatelist(it)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == requestcode) {
            if (resultCode == RESULT_OK) {

                val username = data?.getStringExtra("user_name")
                val description = data?.getStringExtra("description")
                val share_url = data?.getStringExtra("share_url")
                val repo_name = data?.getStringExtra("repo_name")
                val imageurl = data?.getStringExtra("image_url")
                val language = data?.getStringExtra("language")
                val datecreated = data?.getStringExtra("date_created")

                val finaltask = repo(
                    repo_name,
                    username,
                    description,
                    share_url,
                    imageurl,
                    language,
                    datecreated
                )

                viewmodel.Insertrepo(finaltask)
            }
        }
    }

    fun share(url: String) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, "$url")
        startActivity(Intent.createChooser(shareIntent, "Share this meme with"))
    }

    fun delete(repo: repo)
    {
        viewmodel.dleterepo(repo)
    }

    fun onitemclicked(item: repo) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.share_url))
    }

}