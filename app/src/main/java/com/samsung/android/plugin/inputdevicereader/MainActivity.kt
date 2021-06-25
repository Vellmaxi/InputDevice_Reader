package com.samsung.android.plugin.inputdevicereader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.InputDevice
import android.view.KeyEvent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samsung.android.plugin.inputdevicereader.adapter.InputDeviceListAdapter
import com.samsung.android.plugin.inputdevicereader.data.InputDeviceItem

class MainActivity : AppCompatActivity(), InputDeviceListAdapter.OnItemClickListener {

    private var selectedInputDeviceID = 0;
    private var list = ArrayList<InputDeviceItem>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for(id in InputDevice.getDeviceIds()) {
            val inputDevice : InputDevice = InputDevice.getDevice(id);
            val item = InputDeviceItem(id.toString(), inputDevice.name, inputDevice)
            list += item

        }

        val rv = findViewById<View>(R.id.recycler_view) as RecyclerView

        rv.adapter = InputDeviceListAdapter(list, this)
        rv.layoutManager = LinearLayoutManager(this)
        rv.setHasFixedSize(true)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val inputDevice = event?.device
        if(inputDevice?.id == selectedInputDeviceID) {
            Log.d("VELL_EVENT", keyCode.toString() + " from " + inputDevice?.name)

            return true
            //if keyCode is more than a byte then this callback will be called twice
            //return true prevents it
        } else {
            return super.onKeyDown(keyCode, event)
        }
    }

    override fun onItemClicked(position: Int) {

        selectedInputDeviceID = list.get(position).text1.toInt()
        Log.d("VELL_UI", "user click " + position)
    }
}