package com.example.testapp

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_test_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val MAIN_ENTERY_NAME = "Entry"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_test_main)

        val list = loadTestEntry()

        for (clazz in list) {
            test_list.addView(itemView(clazz, test_list))
        }

    }

    override fun onClick(v: View?) {
        startActivity(Intent(this, v?.tag as Class<*>))
    }

    private fun itemView(clazz: Class<*>, parent: ViewGroup): View {

        val view = layoutInflater.inflate(R.layout.main_entry_item, parent, false)

        val text = view.findViewById<TextView>(R.id.name)
        text.text = clazz.simpleName

        view.tag = clazz
        view.setOnClickListener(this)

        return view
    }


    private fun loadTestEntry(): ArrayList<Class<*>> {

        val entries = ArrayList<Class<*>>()

        val packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)

        for (activity: ActivityInfo in packageInfo.activities) {
            if (activity.name.contains(MAIN_ENTERY_NAME)) {
                entries.add(Class.forName(activity.name))
            }
        }
        return entries
    }

    


}
