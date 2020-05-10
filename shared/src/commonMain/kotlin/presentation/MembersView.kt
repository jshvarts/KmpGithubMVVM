package com.jshvarts.kmp.shared.presentation

import com.jshvarts.kmp.shared.model.Member

interface MembersView : BaseView {
  fun showRefreshing()
  fun hideRefreshing()
  fun showData(members: List<Member>)
}
