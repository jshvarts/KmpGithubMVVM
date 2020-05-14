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
  
  @ObservedObject var membersViewModel = MembersViewModel(repository: MembersRepository())
  
  var body: some View {
    NavigationView {
      VStack {
        List(membersViewModel.members, id: \.id) { member in
          MemberView(member: member)
        }
        Text(PlatformMessageKt.createPlatformMessage())
      }
      .navigationBarTitle(Text("Github Members"), displayMode: .large)
      .onAppear(perform: {
        self.membersViewModel.fetch()
      }).alert(isPresented: $membersViewModel.isError, content: {
        Alert(
          title: Text("Error"),
          message: Text("Unable to look up members"),
          dismissButton: .default(Text("OK")) { }
        )
      }
      )
    }
  }
  
  struct MemberView : View {
    @ObservedObject var loader: ImageLoader
    private let member: Member
    
    init(member: Member) {
      self.member = member
      loader = ImageLoader(url: member.avatarUrl)
    }
    
    var body: some View {
      HStack {
        Image(uiImage: uiImageOrPlaceholder(uiImage: loader.image))
          .resizable()
          .frame(width: 100.0, height: 100.0)
          .scaledToFit()
        Text(member.login).font(.headline)
      }
      .onAppear(perform: loader.load )
      .onDisappear(perform: loader.cancel )
    }
    
    private func uiImageOrPlaceholder(uiImage: UIImage?) -> UIImage {
      if let uiImage = uiImage {
        return uiImage
      } else {
        return UIImage(named: "avatar-placeholder")!
      }
    }
  }
  
  struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
      ContentView()
    }
  }
}
