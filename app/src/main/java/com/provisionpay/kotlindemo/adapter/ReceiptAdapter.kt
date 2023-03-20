package com.provisionpay.kotlindemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.provisionpay.kotlindemo.R
import com.provisionpay.kotlindemo.model.ReceiptRowItem

class ReceiptAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items : List<ReceiptRowItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ReceiptViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.receipt_list_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ReceiptViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    fun submitList(receiptList: List<ReceiptRowItem>){
        items = receiptList
    }

    class ReceiptViewHolder constructor(
        itemView : View
    ): RecyclerView.ViewHolder(itemView){

        val caption: TextView = itemView.findViewById<TextView>(R.id.caption)
        val label: TextView = itemView.findViewById<TextView>(R.id.label)

        fun bind(receiptBind: ReceiptRowItem){
            caption.text = receiptBind.caption
            label.text = receiptBind.label

        }
    }
}