package com.example.submissionandroidluthfi

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.bumptech.glide.Glide

class DetailFoodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_food)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val dataFood = if (Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra<Food>("key_food", Food::class.java)
        } else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Food>("key_food")
        }

        val imgHeader = findViewById<ImageView>(R.id.img_header)
        val tvName = findViewById<TextView>(R.id.tv_title)
        val tvIngredient = findViewById<TextView>(R.id.tv_detail_ingredients)
        val tvCity = findViewById<TextView>(R.id.tv_detail_city)
        val tvDescription = findViewById<TextView>(R.id.tv_detail_description)
        val btnShare = findViewById<Button>(R.id.action_share)

        btnShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, dataFood?.name ?: "-")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        Glide.with(applicationContext)
            .load(dataFood?.photo)
            .into(imgHeader)
        tvName.text = dataFood?.name ?: "-"
        tvIngredient.text = dataFood?.ingredients ?: "-"
        tvCity.text = dataFood?.city ?: "-"
        tvDescription.text = dataFood?.description ?: "-"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}