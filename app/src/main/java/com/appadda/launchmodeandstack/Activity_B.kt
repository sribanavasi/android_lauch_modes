package com.appadda.launchmodeandstack

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class Activity_B : BaseActivity() {
    companion object {
        private val TAG = Activity_B::class.java.getSimpleName()
        private var instanceCounter = 0
    }

    private var currentInstanceValue = 0

    private var buttonStartActivityA: Button? = null
    private var buttonStartActivityB: Button? = null
    private var buttonStartActivityC: Button? = null
    private var buttonStartActivityD: Button? = null
    private var textViewTaskInfo: TextView? = null
    private var textViewInstanceValue: TextView? = null

    init {
        instanceCounter++
        currentInstanceValue = instanceCounter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)
        buttonStartActivityA = findViewById<View>(R.id.buttonStartActivityA) as Button
        buttonStartActivityB = findViewById<View>(R.id.buttonStartActivityB) as Button
        buttonStartActivityC = findViewById<View>(R.id.buttonStartActivityC) as Button
        buttonStartActivityD = findViewById<View>(R.id.buttonStartActivityD) as Button
        textViewTaskInfo = findViewById<View>(R.id.textViewTaskInfo) as TextView
        textViewInstanceValue = findViewById<View>(R.id.textViewInstanceValue) as TextView
        textViewInstanceValue!!.append(",Current instance: $currentInstanceValue")
        buttonStartActivityA!!.setOnClickListener(this)
        buttonStartActivityB!!.setOnClickListener(this)
        buttonStartActivityC!!.setOnClickListener(this)
        buttonStartActivityD!!.setOnClickListener(this)
    }


    override fun onResume() {
        super.onResume()
        Log.i(TAG, "Instances: $currentInstanceValue")
        textViewTaskInfo?.text = getAppTaskState()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.buttonStartActivityA -> startActivity(this, Activity_A::class.java)
            R.id.buttonStartActivityB -> startActivity(this, Activity_B::class.java)
            R.id.buttonStartActivityC -> startActivity(this, Activity_C::class.java)
            R.id.buttonStartActivityD -> startActivity(this, Activity_D::class.java)
            else -> {}
        }
    }
}