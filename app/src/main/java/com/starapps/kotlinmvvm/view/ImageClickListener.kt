package com.starapps.kotlinmvvm.view

import com.starapps.kotlinmvvm.repository.model.ImageListResponseItem


interface ImageClickListener {
    fun onItemClick(imagedata : ImageListResponseItem)
    fun onStartClick()
}