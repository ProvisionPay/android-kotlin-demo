package com.example.entegrationdeeplink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.provisionpay.android.deeplinksdk.*
import com.provisionpay.android.deeplinksdk.broadcastReceiver.BroadcastReceiverListener
import com.provisionpay.android.deeplinksdk.model.*


class PaymentFragment : Fragment()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val paymentButton = view.findViewById<Button>(R.id.paymentButton)
        val amountText = view.findViewById<EditText>(R.id.amountText)

        SoftposDeeplinkSdk.initialize(InitializeConfig("your_privateKey",this.requireActivity(),"your deeplinkUrl"))
        SoftposDeeplinkSdk.setDebugMode(true)

        paymentButton.setOnClickListener(View.OnClickListener {
         SoftposDeeplinkSdk.startPayment("your_paymentSessionTokenId","your_deeplink_url")
        })

        SoftposDeeplinkSdk.subscribe(object : SoftposDeeplinkSdkListener{
            override fun onCancel() {
                TODO("Not yet implemented")
            }

            override fun onError(e: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onIntentDataNotFound(intentDataError: IntentDataError) {
                TODO("Not yet implemented")
            }

            override fun onOfflineDecline(paymentFailedResult: PaymentFailedResult?) {
                TODO("Not yet implemented")
            }

            override fun onPaymentDone(transaction: Transaction, isApproved: Boolean) {
                TODO("Not yet implemented")
            }

            override fun onSoftposError(errorType: SoftposErrorType, description: String?) {
                TODO("Not yet implemented")
            }

            override fun onTimeOut() {
                TODO("Not yet implemented")
            }

        })

        SoftposDeeplinkSdk.registerBroadcastReceiver("info@payneos.com",
            object : BroadcastReceiverListener {
                override fun onSoftposBroadcastReceived(
                    eventType: Int,
                    eventTypeMessage: String,
                    paymentSessionToken: String
                ) {
                    TODO("Not yet implemented")
                }

            }
        )


    }

    override fun onPause() {
        super.onPause()
        SoftposDeeplinkSdk.unregisterBroadcastReceiver() }
}