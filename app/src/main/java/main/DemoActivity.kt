package main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.haxhiu.demo.R
import java.util.*

class DemoActivity : AppCompatActivity(R.layout.activity_demo) {

    //TODO: Add builder pattern to our adapter so user can create both as builder and extending adapter
    //TODO: Add header/footer in adapter (with layouts and types) ~ HAdapter<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupClicks()
    }

    private fun setupClicks() {
        findViewById<MaterialButton>(R.id.simpleData).setOnClickListener {
            startActivity(Intent(this@DemoActivity, SimpleDataActivity::class.java))
        }

        findViewById<MaterialButton>(R.id.multipleData).setOnClickListener {
            startActivity(Intent(this@DemoActivity, MultipleDataActivity::class.java))
        }
    }
}