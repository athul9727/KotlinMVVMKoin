package com.starapps.kotlinmvvm.view


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.starapps.kotlinmvvm.R
import com.starapps.kotlinmvvm.databinding.FragmentImagedataDetailsBinding
import com.starapps.kotlinmvvm.repository.model.ImageListResponseItem

class ImageDetailsFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(data: ImageListResponseItem) =
            ImageDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable("data_row", data)
            }
        }
    }

    private var imagedata: ImageListResponseItem? = null
    private lateinit var mDataBinding: FragmentImagedataDetailsBinding


    override fun onAttach(context: Context) {
        super.onAttach(context)
        imagedata =  arguments?.getParcelable("data_row")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mDataBinding  = DataBindingUtil.inflate(inflater,
            R.layout.fragment_imagedata_details, container, false)
        val mRootView = mDataBinding.root
        mDataBinding.lifecycleOwner = this
        return mRootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        enableBackButton()
        mDataBinding.imagedata = imagedata
    }

    private fun enableBackButton() {
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as? AppCompatActivity)?.supportActionBar?.setHomeButtonEnabled(true)
    }
    
}