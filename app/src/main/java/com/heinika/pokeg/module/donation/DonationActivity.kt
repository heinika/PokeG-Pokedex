package com.heinika.pokeg.module.donation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.heinika.pokeg.databinding.ActivityDonationBinding

class DonationActivity : AppCompatActivity() {
  private lateinit var binding: ActivityDonationBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityDonationBinding.inflate(layoutInflater)
    setContentView(binding.root)
    setSupportActionBar(binding.toolbar)
    binding.toolbar.setNavigationOnClickListener { finish() }
  }
}