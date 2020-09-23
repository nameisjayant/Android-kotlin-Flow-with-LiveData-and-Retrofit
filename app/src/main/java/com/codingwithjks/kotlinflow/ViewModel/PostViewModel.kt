package com.codingwithjks.kotlinflow.ViewModel

import android.util.Log
import androidx.lifecycle.*
import com.codingwithjks.kotlinflow.Model.Post
import com.codingwithjks.kotlinflow.Network.Api
import com.codingwithjks.kotlinflow.Repository.PostRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PostViewModel(private val postRepository: PostRepository) : ViewModel() {

    val postData:MutableLiveData<List<Post>> = MutableLiveData()

    /*  way 1 */
    fun getPost()
    {
        viewModelScope.launch {
            postRepository.getPost()
                .catch { e->
                    Log.d("main", "getPost: ${e.message}")
                }
                .collect {postData1->
                    postData.value=postData1
                }
        }
    }

  /*
     way 2

   val postData1:LiveData<List<Post>> = liveData {
        postRepository.getPost()
            .catch {  }
            .collect {postData->
                emit(postData)
            }
    }

   */

   /*
   way 3
    val postData2:LiveData<List<Post>> = postRepository.getPost()
        .catch {  }
        .asLiveData()

    */
}