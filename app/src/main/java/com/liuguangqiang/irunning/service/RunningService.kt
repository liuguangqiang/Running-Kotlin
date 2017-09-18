package com.liuguangqiang.irunning.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
import com.liuguangqiang.irunning.data.entity.Step
import com.liuguangqiang.irunning.utils.event.StepEvent
import com.liuguangqiang.support.utils.Logger
import com.liuguangqiang.support.utils.TimeUtils
import io.realm.Realm
import org.greenrobot.eventbus.EventBus

/**
 * Created by Eric on 2017/5/19.
 */
class RunningService : Service(), SensorEventListener {

    private var today: String? = null

    private var lastDate: String? = null

    private var lastCounterValue = 0

    private var currentStep: Step? = null

    private var currentStepCount = 0

    private var realm: Realm? = null

    override fun onBind(intent: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate() {
        super.onCreate()
        Logger.d("RunningService onCreate")
        realm = Realm.getDefaultInstance()
        today = getDateTime()
        startSensor()
    }

    /**
     * Improve the priority of Service.
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    private fun getDateTime(): String {
        return TimeUtils.getDateTimeByFormat("yyyy-MM-dd")
    }

    /**
     * save steps into Realm.
     */
    fun saveSteps() {
        Logger.d("saveSteps:" + currentStepCount)
        realm?.beginTransaction()
        currentStep?.count = currentStepCount
        realm?.commitTransaction()
    }

    private fun resetSteps(date: String) {
        lastCounterValue = 0
        currentStepCount = 0

        today = date
        lastDate = today
        currentStep = realm?.where(Step::class.java)?.equalTo("date", date)?.findFirst()
        if (currentStep == null) {
            realm?.beginTransaction()
            currentStep = realm?.createObject(Step::class.java)
            currentStep?.date = date
            realm?.commitTransaction()
        } else {
            currentStepCount = currentStep!!.count!!
        }
        EventBus.getDefault().post(StepEvent(currentStepCount, date))
    }

    fun startSensor() {
        var obj = getSystemService(SENSOR_SERVICE)

        var sensorManager: SensorManager = obj as SensorManager
        if (sensorManager != null) {
            var sensor: Sensor? = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
            if (sensor != null)
                sensorManager?.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL, 0)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val currentDate = getDateTime()
        if (currentDate != lastDate) {
            //如果已经进入第二天
            resetSteps(currentDate)
        }

        val counterValue = event!!.values[0].toInt()
        if (lastCounterValue == 0) {
            lastCounterValue = counterValue
        }

        currentStepCount += counterValue - lastCounterValue
        lastCounterValue = counterValue
        saveSteps()
        EventBus.getDefault().post(StepEvent(currentStepCount, today!!))

        //同步
//        changeTimes--
//        if (changeTimes == 0) {
//            changeTimes = 10
//            runningPresenter.autoSyncSteps(today, currentStepCount)
//        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

}
