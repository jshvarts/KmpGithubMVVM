package com.jshvarts.kmp.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jshvarts.kmp.model.Member
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_member.view.memberAvatar
import kotlinx.android.synthetic.main.list_item_member.view.memberLogin

class MemberAdapter(var members: List<Member>) : RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
    val itemView =
      LayoutInflater.from(parent.context).inflate(R.layout.list_item_member, parent, false)
    return MemberViewHolder(itemView)
  }

  override fun getItemCount() = members.size

  override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
    holder.bind(members[position])
  }

  class MemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(member: Member) {
      Picasso.get().load(member.avatarUrl).into(itemView.memberAvatar)
      itemView.memberLogin.text = member.login
    }
  }
}
