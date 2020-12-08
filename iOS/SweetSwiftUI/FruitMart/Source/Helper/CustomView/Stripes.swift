//
//  Stripes.swift
//  FruitMart
//
//  Created by cu on 2020/12/08.
//  Copyright © 2020 Giftbot. All rights reserved.
//

import SwiftUI

struct Stripes: Shape, Hashable {
    var stripes: Int = 30
    var insertion: Bool = true
    var ratio: CGFloat
    
    var animatableData: CGFloat {
        get { ratio }
        set { ratio = newValue }
    }
    
    func path(in rect: CGRect) -> Path {
      var path = Path()
      let stripeWidth = rect.width / CGFloat(stripes)
      let rects = (0..<stripes).map { (index: Int) -> CGRect in
        let xOffset = CGFloat(index) * stripeWidth
        let adjustment = insertion ? 0 : (stripeWidth * (1 - ratio))
        return CGRect(
          x: xOffset + adjustment, y: 0,
          width: stripeWidth * ratio, height: rect.height
        )
      }
      path.addRects(rects)
      return path
    }
}


