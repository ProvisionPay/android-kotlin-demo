package com.provisionpay.kotlindemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.entegrationdeeplink.R
import com.provisionpay.android.deeplinksdk.SoftposDeeplinkSdk

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.main_activity,PaymentFragment()).commit()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        SoftposDeeplinkSdk.handleDeeplinkTransaction()
    }
}