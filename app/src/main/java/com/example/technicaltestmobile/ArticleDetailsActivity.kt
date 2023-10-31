package com.example.technicaltestmobile

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class ArticleDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)
        title = "Detail Article"

        onInit()
    }

    private fun onInit(){
        val actionBar = supportActionBar
        val article : ArticlesItem? = intent.getParcelableExtra("dataArticle")

        val titleArticle: TextView = findViewById(R.id.titleArticle)
        val dateArticle: TextView = findViewById(R.id.dateArticle)
        val authorArticle: TextView = findViewById(R.id.authorArticle)
        val contentArticle: TextView = findViewById(R.id.contentArticle)
        val imageArticle: ImageView = findViewById(R.id.imageArticle)

        actionBar?.setDisplayHomeAsUpEnabled(true)

        titleArticle.text = article?.title
        dateArticle.text = article?.publishedAt
        authorArticle.text = article?.author
        contentArticle.text = article?.content
        Glide.with(this).load(article?.urlToImage)
            .fallback(com.google.android.material.R.drawable.mtrl_ic_error)
            .error(com.google.android.material.R.drawable.mtrl_ic_error)
            .into(imageArticle)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}