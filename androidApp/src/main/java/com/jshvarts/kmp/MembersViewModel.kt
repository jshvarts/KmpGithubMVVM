package com.jshvarts.kmp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jshvarts.kmp.shared.model.Member
import com.jshvarts.kmp.shared.repository.MembersRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MembersViewModel(
  private val repository: MembersRepository
) : ViewModel() {

  private val _members = MutableLiveData<List<Member>>()
  val members: LiveData<List<Member>> = _members

  private val _error = MutableLiveData<Throwable>()
  val error: LiveData<Throwable> = _error

  private val _isRefreshing = MutableLiveData<Boolean>()
  val isRefreshing: LiveData<Boolean> = _isRefreshing

  init {
    loadMembers()
  }

  @ExperimentalCoroutinesApi
  fun loadMembers(force: Boolean = false) {

    viewModelScope.launch {
      repository.fetchMembersAsFlow(force)
        .onStart {
          _isRefreshing.value = true
        }.onCompletion {
          _isRefreshing.value = false
        }.catch {
          _error.value = it
        }.collect {
          _members.value = it
        }
    }
  }
}
