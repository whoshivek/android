package com.shivek.mvvmgit.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shivek.mvvmgit.data.models.Allgituser
import com.shivek.mvvmgit.data.repo.repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class githubviewmodel : ViewModel()
{
    val users = MutableLiveData<List<Allgituser>>()

    fun fetchdata()
    {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO){repo.getuser()}
            if (response.isSuccessful)
            {
                response.body().let {
                    users.postValue(it)
                }
            }
        }

    }
}