package com.starapps.kotlinmvvm.repository.model


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
 class ImageListResponse (val list : ArrayList<ImageListResponseItem>?) : Parcelable