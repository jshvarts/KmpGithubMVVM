package com.jshvarts.kmp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jshvarts.kmp.shared.model.Member
import com.jshvarts.kmp.shared.model.MembersRepository
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

  fun loadMembers() {
    _isRefreshing.value = true

    viewModelScope.launch {
      _isRefreshing.value = try {
        _members.value = repository.getMembers()
        false
      } catch (error: Throwable) {
        _error.value = error
        false
      }
    }
  }
}
