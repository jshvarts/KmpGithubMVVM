package com.jshvarts.kmp.android

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jshvarts.kmp.android.R.layout
import com.jshvarts.kmp.android.R.string
import com.jshvarts.kmp.api.DataLoadException
import com.jshvarts.kmp.createPlatformMessage
import com.jshvarts.kmp.model.Member
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  private val repository by lazy {
    (application as KmpGithubMVVMApplication).membersRepository
  }

  private val viewModel by lazy { MembersViewModel(repository) }

  private lateinit var adapter: MemberAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)

    platformMessage.text = createPlatformMessage()

    setupRecyclerView()

    viewModel.members.observe(this, Observer {
      if (it.isEmpty()) {
        Toast.makeText(this, R.string.empty_cache, Toast.LENGTH_LONG).show()
      } else {
        showData(it)
      }
    })

    viewModel.error.observe(this, Observer {
      showError(it)
    })

    viewModel.isRefreshing.observe(this, Observer {
      pullToRefresh.isRefreshing = it
    })

    pullToRefresh.setOnRefreshListener {
      viewModel.loadMembers(force = true)
    }
  }

  private fun showData(members: List<Member>) {
    adapter.members = members

    runOnUiThread {
      adapter.notifyDataSetChanged()
    }
  }

  private fun showError(error: Throwable) {
    val errorMessage = when (error) {
      is DataLoadException -> getString(string.update_problem_message)
      else -> getString(string.unknown_error)
    }
    runOnUiThread {
      Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }
  }

  private fun setupRecyclerView() {
    membersRecyclerView.layoutManager = LinearLayoutManager(this)
    adapter = MemberAdapter(emptyList())
    membersRecyclerView.adapter = adapter
  }
}
