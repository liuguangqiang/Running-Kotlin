package com.liuguangqiang.irunning.service

import android.app.Service
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
import android.util.Log
import com.liuguangqiang.irunning.data.entity.Step

/**
 * Created by Eric on 2017/5/19.
 */
class RunningService : Service(), SensorEventListener {

    private var today: String? = null

    private var lastDate: String? = null

    private var lastCounterValue = 0

    private var currentStep: Step? = null

    private var currentStepCount = 0

    override fun onBind(intent: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("running", "RunningService onCreate")
        startSensor()
    }

    /**
     * Improve the priority of Service.
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    /**
     * save steps into Realm.
     */
    fun saveSteps() {

    }

    fun startSensor() {
        var sensorManager: SensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        var sensor: Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL, 0)
    }

    override fun onSensorChanged(event: SensorEvent?) {

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

}
