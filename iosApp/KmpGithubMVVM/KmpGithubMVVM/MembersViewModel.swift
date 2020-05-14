//
//  MembersViewModel.swift
//  KmpGithubMVVM
//
//  Created by James Shvarts on 5/10/20.
//  Copyright © 2020 James Shvarts. All rights reserved.
//

import Foundation
import shared

class MembersViewModel: ObservableObject {
  @Published var members = [Member]()
  @Published var isError = false
  
  private let repository: MembersRepository
  
  init(repository: MembersRepository) {
    self.repository = repository
  }
  
  func fetch() {
    repository.fetchMembers(onSuccess: { [weak self] data in
      self?.members = data
      }, onError: { [weak self] throwable in
        self?.isError = true
      }
    )
  }
}
