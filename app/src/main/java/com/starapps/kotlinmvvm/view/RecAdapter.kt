package com.starapps.kotlinmvvm.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.starapps.kotlinmvvm.R
import com.starapps.kotlinmvvm.databinding.ListItemBinding


import com.starapps.kotlinmvvm.repository.model.ImageListResponseItem

class RecAdapter(
    val context: Context?,
    val clickListener: ImageClickListener
    ) :

    RecyclerView.Adapter<RecAdapter.ImageViewHolder>() {

    var list: List<ImageListResponseItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val mDataBinding: ListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item, parent, false
        )
        return ImageViewHolder(mDataBinding)
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun setlist(imagelist: List<ImageListResponseItem>) {
        this.list = imagelist
        notifyDataSetChanged()
    }

    inner class ImageViewHolder(private val mDataBinding: ListItemBinding) :
        RecyclerView.ViewHolder(mDataBinding.root) {

        fun onBind(position: Int) {
            val row = list[position]
            mDataBinding.imagedata = row
            mDataBinding.imageClickInterface = clickListener
        }
    }

}
