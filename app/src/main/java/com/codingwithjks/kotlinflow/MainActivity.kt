package com.codingwithjks.kotlinflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codingwithjks.kotlinflow.Adapter.PostAdapter
import com.codingwithjks.kotlinflow.Model.Post
import com.codingwithjks.kotlinflow.Network.Api
import com.codingwithjks.kotlinflow.Repository.PostRepository
import com.codingwithjks.kotlinflow.ViewModel.PostViewModel
import com.codingwithjks.kotlinflow.ViewModel.PostViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class MainActivity : AppCompatActivity() {
    private val TAG="main"
    private lateinit var recyclerView:RecyclerView
    private lateinit var postAdapter: PostAdapter
    private lateinit var postViewModel:PostViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       initUi()
        val postViewModelFactory=PostViewModelFactory(PostRepository())
        postViewModel=ViewModelProvider(this,postViewModelFactory)[PostViewModel::class.java]
        postViewModel.getPost()
        postViewModel.postData.observe(this, Observer {
            Log.d(TAG, "onCreate: ${it[0].body}")
            postAdapter.setPostData(it as ArrayList<Post>)
            progressBar.visibility=View.GONE
            recyclerView.visibility=View.VISIBLE
        })
    }

   private fun initUi() {
        recyclerView=findViewById(R.id.recyclerView)
        postAdapter= PostAdapter(this, ArrayList())
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter=postAdapter
        }
    }

}