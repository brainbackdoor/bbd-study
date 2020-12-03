//
//  Popup.swift
//  FruitMart
//
//  Created by cu on 2020/12/03.
//  Copyright © 2020 Giftbot. All rights reserved.
//

import SwiftUI

enum PopupStyle {
    case none
    case blur
    case dimmed
}

struct Popup<Message: View>: ViewModifier {
  let size: CGSize?
  let style: PopupStyle
  let message: Message
  
  init(
    size: CGSize? = nil,
    style: PopupStyle = .none,
    message: Message
  ) {
    self.size = size
    self.style = style
    self.message = message
  }

  func body(content: Content) -> some View {
    content
      .blur(radius: style == .blur ? 2 : 0)
      .overlay(Rectangle()
        .fill(Color.black.opacity(style == .dimmed ? 0.4 : 0)))
      .overlay(popupContent)
  }

  private var popupContent: some View {
    GeometryReader { g in
      VStack { self.message }
        .frame(width: self.size?.width ?? g.size.width * 0.6,
               height: self.size?.height ?? g.size.height * 0.25)
        .background(Color.primary.colorInvert())
        .cornerRadius(12)
        .shadow(color: .primaryShadow, radius: 15, x: 5, y: 5)
        .overlay(self.checkCircleMark, alignment: .top)
        // iOS 13과 iOS 14의 지오메트리 리더 뷰 정렬 위치가 달라졌으므로 조정
        .position(x: g.size.width / 2, y: g.size.height / 2)
    }
  }

  private var checkCircleMark: some View {
    Symbol("checkmark.circle.fill", color: .peach)
      .font(Font.system(size: 60).weight(.semibold))
      // iOS 13과 14에서 크기 차이가 있어 조정
      .background(Color.white.scaleEffect(0.2))
      .offset(x: 0, y: -20)
  }
}
