package com.appadda.launchmodeandstack

import android.app.Activity
import android.app.ActivityManager
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


open class BaseActivity : AppCompatActivity(), View.OnClickListener {

    protected var activityManager: ActivityManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activityManager == null) {
            activityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        }
    }

    protected fun startActivity(activity: Activity?, targetActivityClass: Class<*>?) {
        val intent = Intent(activity, targetActivityClass)
        startActivity(intent)
    }

    protected fun getNumberOfTasks(): Int {
        return activityManager!!.getAppTasks().size
    }

    protected fun getAppTaskState(): String {
        val stringBuilder = StringBuilder()
        val totalNumberOfTasks =
            activityManager!!.getRunningTasks(10).size //Returns total number of tasks - stacks
        stringBuilder.append("\nTotal Number of Tasks: $totalNumberOfTasks\n\n")
        val taskInfo =
            activityManager!!.getRunningTasks(10) //returns List of RunningTaskInfo - corresponding to tasks - stacks
        for (info in taskInfo) {
            stringBuilder.append("Task Id: " + info.id + ", Number of Activities : " + info.numActivities + "\n") //Number of Activities in task - stack

            // Display the root Activity of task-stack
            stringBuilder.append(
                "TopActivity: " + info.topActivity!!.className.toString().replace(
                    "lauchmodesdemo.youtube.codetutor.com.activitylauchmodesdemo.",
                    ""
                ) + "\n"
            )

            // Display the top Activity of task-stack
            stringBuilder.append(
                "BaseActivity:" + info.baseActivity!!.className.toString().replace(
                    "lauchmodesdemo.youtube.codetutor.com.activitylauchmodesdemo.",
                    ""
                ) + "\n"
            )
            stringBuilder.append("\n\n")
        }
        return stringBuilder.toString()
    }

    override fun onClick(view: View?) {

    }
}