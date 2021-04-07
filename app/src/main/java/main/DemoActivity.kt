package main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.haxhiu.demo.R
import java.util.*

class DemoActivity : AppCompatActivity(R.layout.activity_demo) {

    fun onSimpleDataWithHeaderAndFooterClicked(view: View) {
        startActivity(Intent(this@DemoActivity, HeaderFooterSampleActivity::class.java))
    }

    fun onMultipleDataActivityClicked(view: View) {
        startActivity(Intent(this@DemoActivity, MultipleDataSampleActivity::class.java))
    }
}