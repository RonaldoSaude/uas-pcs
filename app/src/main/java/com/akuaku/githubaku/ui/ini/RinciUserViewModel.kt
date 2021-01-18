package com.akuaku.githubaku.ui.ini

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akuaku.githubaku.api.RetrofitUser
import com.akuaku.githubaku.data.InuUserRespponeses
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RinciUserViewModel : ViewModel(){
    val user = MutableLiveData<InuUserRespponeses>()

    fun setUserDetail(username: String){
        RetrofitUser.apiInstance
            .getUserDetail(username)
            .enqueue(object : Callback<InuUserRespponeses> {
                override fun onFailure(call: Call<InuUserRespponeses>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

                override fun onResponse(
                    call: Call<InuUserRespponeses>,
                    response: Response<InuUserRespponeses>
                ) {
                    if(response.isSuccessful){
                        user.postValue(response.body())
                    }
                }

            })
    }

    fun getUserDetail() : LiveData<InuUserRespponeses> {
        return user
    }
}
