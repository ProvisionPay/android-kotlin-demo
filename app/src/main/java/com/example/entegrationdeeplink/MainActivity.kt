package com.example.entegrationdeeplink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.provisionpay.android.deeplinksdk.SoftposDeeplinkSdk

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.main_activity,PaymentFragment())
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        SoftposDeeplinkSdk.handleDeeplinkTransaction()
    }
}