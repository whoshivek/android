package com.shivek.mvvmgit.data.repo

import com.shivek.mvvmgit.data.api.client

object repo
{
    suspend fun getuser() = client.api.getusers()

}