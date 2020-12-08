//
//  Order.swift
//  FruitMart
//
//  Created by cu on 2020/12/03.
//  Copyright © 2020 Giftbot. All rights reserved.
//

import Foundation

struct Order: Identifiable {
    static var orderSequence = sequence(first: 1) { $0 + 1 }
    
    let id: Int
    let product: Product
    let quantity: Int
    
    var price: Int {
        product.price * quantity
    }
}

extension Order: Codable {}
