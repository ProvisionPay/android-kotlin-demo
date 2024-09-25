package com.provisionpay.kotlindemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.provisionpay.android.deeplinksdk.model.PaymentFailedResult
import com.provisionpay.android.deeplinksdk.model.Transaction
import com.provisionpay.kotlindemo.adapter.ReceiptAdapter
import com.provisionpay.kotlindemo.model.ReceiptRowItem


class ReceiptFragment(var transaction: Transaction?) :DialogFragment() {
    private lateinit var adapter: ReceiptAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL,  android.R.style.Theme_Material_Light_NoActionBar_Fullscreen)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_receipt, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = ReceiptAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.receipt_recycler)
        recyclerView?.layoutManager = LinearLayoutManager(this.requireContext(),)

        val list = createPaymentDoneReceipt(transaction!!)
        adapter.submitList(list)
        recyclerView.adapter = adapter
        super.onViewCreated(view, savedInstanceState)

    }

    private fun createPaymentDoneReceipt(paymentResponse: Transaction): ArrayList<ReceiptRowItem>{
        val list = ArrayList<ReceiptRowItem>()

        paymentResponse.Receipt.Detail?.forEach { item ->
            val isApproved = item.Key == "Status" && paymentResponse.Receipt.Approved
            list.add(
                ReceiptRowItem(
                    caption = item.Key,
                    label = item.Value ?: ""
                )
            )
        }
        return list
    }
}