package com.example.myapplicationtlg.ui

import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplicationtlg.databinding.ActivityBaseBinding

open class BaseActivity : AppCompatActivity() {

    private lateinit var baseBinding: ActivityBaseBinding

    override fun setContentView(layoutResID: Int) {
        baseBinding = ActivityBaseBinding.inflate(layoutInflater)
        val contentView = layoutInflater.inflate(layoutResID, baseBinding.contentFrame, false)
        baseBinding.contentFrame.addView(contentView)
        super.setContentView(baseBinding.root)
        setSupportActionBar(baseBinding.toolbar)
    }

    override fun setContentView(view: View?) {
        baseBinding = ActivityBaseBinding.inflate(layoutInflater)
        baseBinding.contentFrame.addView(view)
        super.setContentView(baseBinding.root)
        setSupportActionBar(baseBinding.toolbar)
    }

    fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    fun showShortToast(text:String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    fun showLongToast(text:String){
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
