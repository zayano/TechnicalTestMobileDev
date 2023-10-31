package com.example.technicaltestmobile

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Top Headlines"

        val rvHeadlines: RecyclerView = findViewById(R.id.rvTopHeadlines)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)

        progressBar.visibility = View.VISIBLE


        NetworkConfig().getService()
            .getTopHeadlines()
            .enqueue(object : Callback<TopHeadlinesResponse>, RecyclerViewClickListener {
                override fun onFailure(call: Call<TopHeadlinesResponse>, t: Throwable) {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<TopHeadlinesResponse>,
                    response: Response<TopHeadlinesResponse>
                ) {
                    progressBar.visibility = View.GONE
                    val articleAdapter = TopHeadlinesAdapter(response.body()?.articles as List<ArticlesItem>?)
                    rvHeadlines.adapter = articleAdapter
                    articleAdapter.listener = this
                }

                override fun onItemClicked(view: View, article: ArticlesItem?) {
                    val intent = Intent(this@MainActivity, ArticleDetailsActivity::class.java)
                    intent.putExtra("dataArticle",article)
                    startActivity(intent)
                }

            })
    }
}