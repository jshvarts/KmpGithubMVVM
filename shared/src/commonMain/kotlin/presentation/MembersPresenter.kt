package com.jshvarts.kmp.shared.presentation

import com.jshvarts.kmp.shared.ApplicationDispatcher
import com.jshvarts.kmp.shared.model.MembersRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MembersPresenter(
  private var view: MembersView?,
  private val repository: MembersRepository
) : BasePresenter, CoroutineScope {

  private val job = Job()

  private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
    onError(throwable)
  }

  override val coroutineContext: CoroutineContext
    get() = ApplicationDispatcher + exceptionHandler

  fun loadMembers() {
    view?.showRefreshing()
    launch {
      val members = repository.getMembers()
      view?.showData(members)
    }.invokeOnCompletion {
      view?.hideRefreshing()
    }
  }

  override fun onError(error: Throwable) {
    view?.showError(error)
  }

  override fun stop() {
    job.cancel()
    view = null
  }
}
