package com.samsung.android.plugin.inputdevicereader.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.samsung.android.plugin.inputdevicereader.R
import com.samsung.android.plugin.inputdevicereader.data.InputDeviceItem

class InputDeviceListAdapter(
    private val list: List<InputDeviceItem>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<InputDeviceListAdapter.InputDeviceListVH>() {
    inner class InputDeviceListVH(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val textView1: TextView = itemView.findViewById(R.id.text_view_1);
        val textView2: TextView = itemView.findViewById(R.id.text_view_2);

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position: Int = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                listener.onItemClicked(position);
            }
        }

    }

    interface OnItemClickListener{
        fun onItemClicked(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InputDeviceListVH {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.input_device_item,
            parent, false);

        return InputDeviceListVH(itemView);
    }

    override fun onBindViewHolder(holder: InputDeviceListVH, position: Int) {
        val currentItem = list[position];

        holder.textView1.text = currentItem.text1;
        holder.textView2.text = currentItem.text2;
    }

    override fun getItemCount() = list.size
}