package com.digimaster.kotlin2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.digimaster.kotlin2.model.LoginResponseModel
import com.digimaster.kotlin2.api.RetrofitInstance
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class LoginViewModel: ViewModel(){
    // MutableLiveData itu jalur data
    private val loginResponseModel = MutableLiveData<LoginResponseModel>()
    // error handling
    private val errorListener = MutableLiveData<Boolean>()
    // composDisposable buat naro api
    private val composDisposable = CompositeDisposable()

    fun login(email: String, password: String){
        composDisposable.add(
            RetrofitInstance.apiInterface.login(email, password)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<LoginResponseModel>(){ // Subscribewith itu isinya observer
                override fun onSuccess(t: LoginResponseModel) {
                    if("success" == t.status){
                        loginResponseModel.value = t
                    } else {
                        errorListener.value = true
                    }
                }
                override fun onError(e: Throwable) {
                    errorListener.value = true
                }
            }))
    }

    fun register(email: String, name: String, address: String, dob: String, password: String){
        composDisposable.add(
            RetrofitInstance.apiInterface.register(email, name, address, dob, password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<LoginResponseModel>(){
                    override fun onSuccess(t: LoginResponseModel) {
                        if("success" == t.status){
                            loginResponseModel.value = t
                        } else {
                            errorListener.value = true
                        }
                    }
                    override fun onError(e: Throwable) {
                        errorListener.value = true
                    }
                }))
    }

    fun getLoginResponseModel(): MutableLiveData<LoginResponseModel>{
        return loginResponseModel
    }

    fun getRegisterResponseModel(): MutableLiveData<LoginResponseModel>{
        return loginResponseModel
    }

    fun getErrorListener(): MutableLiveData<Boolean>{
        return errorListener
    }

    override fun onCleared() {
        super.onCleared()
        composDisposable.clear()
    }
}