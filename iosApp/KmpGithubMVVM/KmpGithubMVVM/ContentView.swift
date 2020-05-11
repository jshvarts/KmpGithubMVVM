//
//  ContentView.swift
//  KmpGithubMVVM
//
//  Created by James Shvarts on 5/10/20.
//  Copyright © 2020 James Shvarts. All rights reserved.
//

import SwiftUI
import shared

struct ContentView: View {
  
  @ObservedObject var membersViewModel = MembersViewModel(repository: MembersRepository(api: GithubApi()))
  
  var body: some View {
      NavigationView {
          List(membersViewModel.members, id: \.id) { member in
            MemberView(member: member)
          }
          .navigationBarTitle(Text("Github Members"), displayMode: .large)
          .onAppear(perform: {
              self.membersViewModel.fetch()
          })
      }
  }
}

struct MemberView : View {
  var member: Member
  
  var body: some View {
      Text(member.login).font(.headline)
  }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
