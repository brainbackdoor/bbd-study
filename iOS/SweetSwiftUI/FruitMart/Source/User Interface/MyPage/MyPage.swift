//
//  MyPage.swift
//  FruitMart
//
//  Created by cu on 2020/12/08.
//  Copyright © 2020 Giftbot. All rights reserved.
//

import SwiftUI

struct MyPage: View {
    var body: some View {
        NavigationView {
            Form {
                orderInfoSection
            }
        }
    }
    
    var orderInfoSection: some View {
        Section(header: Text("주문 정보").fontWeight(.medium)) {
            NavigationLink(destination: OrderListView()) {
                Text("주문 목록")
            }
            .frame(height: 44)
        }
    }
}

struct MyPage_Previews: PreviewProvider {
    static var previews: some View {
        MyPage()
    }
}
