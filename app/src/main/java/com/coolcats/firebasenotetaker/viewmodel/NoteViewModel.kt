package com.coolcats.firebasenotetaker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coolcats.firebasenotetaker.model.NotePost
import com.coolcats.firebasenotetaker.model.User
import com.coolcats.firebasenotetaker.util.LoadingStatus

class NoteViewModel : ViewModel(){

    var userData = MutableLiveData<User>()

    var postNoteStatus = MutableLiveData<Boolean>()

    //Progressbar Loading States
    var loadingStatus = MutableLiveData<LoadingStatus>()




}