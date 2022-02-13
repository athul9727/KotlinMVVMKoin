package com.starapps.kotlinmvvm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.starapps.kotlinmvvm.R
import com.starapps.kotlinmvvm.databinding.ActivityStartBinding
import com.starapps.kotlinmvvm.repository.model.ImageListResponseItem
import com.starapps.kotlinmvvm.repository.model.StartModel
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity(), ImageClickListener {

    private lateinit var mDataBinding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     //   setContentView(R.layout.activity_start)

       mDataBinding = DataBindingUtil.setContentView(this,R.layout.activity_start)

        val startmodel = StartModel("Start", 1234)

        mDataBinding.lifecycleOwner = this
        mDataBinding.startmodel = startmodel
        mDataBinding.executePendingBindings()
        mDataBinding.imageClickInterface = this
    }

    override fun onItemClick(imagedata: ImageListResponseItem) {
    }

    override fun onStartClick() {
        //1
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("data1", 1234)
        startActivity(intent)
        //2
        //startActivity(Intent(this, MainActivity::class.java))

    }
}