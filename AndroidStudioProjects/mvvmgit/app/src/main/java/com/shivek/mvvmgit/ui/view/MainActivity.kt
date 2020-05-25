package com.shivek.mvvmgit.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shivek.mvvmgit.R
import com.shivek.mvvmgit.data.models.Allgituser
import com.shivek.mvvmgit.ui.adapter.githubadapter
import com.shivek.mvvmgit.ui.viewmodel.githubviewmodel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val vm by lazy {
        ViewModelProvider(this).get(githubviewmodel::class.java)
    }

    val list = arrayListOf<Allgituser>()
    val adapter = githubadapter(list)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
vm.fetchdata()
        vm.users.observe(this ,  Observer {
            if (!it.isNullOrEmpty())
            {
                list.addAll(it)
                adapter.notifyDataSetChanged()

            }
        })

    }
}
