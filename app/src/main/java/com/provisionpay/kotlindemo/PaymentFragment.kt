package com.provisionpay.kotlindemo

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.provisionpay.android.deeplinksdk.*
import com.provisionpay.android.deeplinksdk.broadcastReceiver.BroadcastReceiverListener
import com.provisionpay.android.deeplinksdk.model.*


class PaymentFragment : Fragment()  {
    private  lateinit var list : ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_payment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val paymentButton = view.findViewById<Button>(R.id.paymentButton)

        SoftposDeeplinkSdk.initialize(InitializeConfig("your_private_key",this.requireActivity(),"your_softpos_url"))
        SoftposDeeplinkSdk.setDebugMode(true)

        paymentButton.setOnClickListener(View.OnClickListener {
         SoftposDeeplinkSdk.startPayment("your_paymentSessionTokenId","your_softpos_url")
        })

        SoftposDeeplinkSdk.subscribe(object : SoftposDeeplinkSdkListener{
            override fun onCancel() {
                Log.d("SOFTPOS","onCancel)")
            }

            override fun onError(e: Throwable) {
                Log.d("SOFTPOS","onError")
            }

            override fun onIntentData(dataFlow: IntentDataFlow, data: String?) {
                Log.d("SOFTPOS","onIntentData")

            }

            override fun onOfflineDecline(paymentFailedResult: PaymentFailedResult?) {
                Log.d("SOFTPOS","onOfflineDecline")
            }

            override fun onPaymentDone(transaction: Transaction, isApproved: Boolean) {
                Log.d("SOFTPOS","onPaymentDone")
                val receiptFragment = ReceiptFragment(transaction)
                receiptFragment.show(fragmentManager!!,"Receipt")
            }

            override fun onSoftposError(errorType: SoftposErrorType, description: String?) {
                Log.d("SOFTPOS","onSoftposError")}

            override fun onTimeOut() {
                Log.d("SOFTPOS","onTimeOut") }

        })

        SoftposDeeplinkSdk.registerBroadcastReceiver("your_packageID",
            object : BroadcastReceiverListener {
                override fun onSoftposBroadcastReceived(
                    eventType: Int,
                    eventTypeMessage: String,
                    paymentSessionToken: String
                ) {
                   Log.d("SOFTPOS","onSoftposBroadcastReceived")
                }
            }
        )
    }

    override fun onPause() {
        super.onPause()
        SoftposDeeplinkSdk.unregisterBroadcastReceiver()
    }


}
