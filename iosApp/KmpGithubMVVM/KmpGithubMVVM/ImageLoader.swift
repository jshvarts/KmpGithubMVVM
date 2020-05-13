//
//  ImageLoader.swift
//  KmpGithubMVVM
//
//  Created by James Shvarts on 5/12/20.
//  Copyright © 2020 James Shvarts. All rights reserved.
//

import UIKit
import Combine

class ImageLoader: ObservableObject {
  @Published var image: UIImage?
  private let url: String
  private var cancellable: AnyCancellable?
  
  init(url: String) {
      self.url = url
  }

  deinit {
      cancellable?.cancel()
  }

  func load() {
    cancellable = URLSession.shared.dataTaskPublisher(for: URL(string: url)!)
      .map { UIImage(data: $0.data) }
      .replaceError(with: nil)
      .receive(on: DispatchQueue.main)
      .assign(to: \.image, on: self)
  }
  
  func cancel() {
      cancellable?.cancel()
  }
}
