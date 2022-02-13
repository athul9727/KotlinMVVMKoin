package com.starapps.kotlinmvvm.viewmodel

import android.app.Application
import androidx.databinding.Bindable
import com.starapps.kotlinmvvm.util.SingleLiveEvent
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.starapps.kotlinmvvm.repository.Repository
import com.starapps.kotlinmvvm.repository.model.ImageListResponseItem

import com.starapps.kotlinmvvm.util.AppResult
import com.starapps.kotlinmvvm.util.ConnectivityLiveData
import kotlinx.coroutines.launch

class ViewModel(private val repository: Repository,
                private val application: Application) : ViewModel() {

    val showLoading = ObservableBoolean()
    val imageList = MutableLiveData<List<ImageListResponseItem>>()
    val showError = SingleLiveEvent<String>()
    val connectivityLiveData = ConnectivityLiveData(application)

    val bttext = "Search"
    val inputtext = MutableLiveData<String>()
    private val statusmessage = SingleLiveEvent<String>()
    val message: SingleLiveEvent<String>
        get() {  return statusmessage }



    fun getalldata(str:String) {
        showLoading.set(true)
        viewModelScope.launch {

            val result =  repository.getAlldata(str)
            showLoading.set(false)



            when (result) {
                is AppResult.Success -> {
                    imageList.value = result.successData
                    showError.value = null
                }
                is AppResult.Error -> showError.value = result.exception.message
            }
        }
    }


    fun set_edittext() {

        if (inputtext.value == null) {
            statusmessage.value = "Please enter text"
        } else {

            getalldata(inputtext.value!!)

        }

    }

}