//
//  Home.swift
//  FruitMart
//
//  Created by Giftbot on 2020/03/02.
//  Copyright © 2020 Giftbot. All rights reserved.
//

import SwiftUI

struct Home: View {
    @EnvironmentObject private var store: Store
    @State private var quickOrder: Product?
    @State private var showingFavoriteImage: Bool = true
    
  var body: some View {
    NavigationView {
        VStack {
            if showFavorite {
                favoriteProducts
            }
            darkerDivider
            productList
        }

        
    }
  }
    
    var favoriteProducts: some View {
        FavoriteProductScrollView(showingImage: $showingFavoriteImage)
            .padding(.top, 24)
            .padding(.bottom, 8)
    }
    
    var darkerDivider: some View {
        Color.primary
            .opacity(0.3)
            .frame(maxWidth: .infinity, maxHeight: 1)
    }
    
    var productList: some View {
        List {
            ForEach(store.products) { product in
                HStack {
                    NavigationLink(destination: ProductDetailView(product: product)) {
                      ProductRow(product: product, quickOrder: $quickOrder)
                    }
                }
            }
            .listRowBackground(Color.background)
        }
        .background(Color.background)
        .listStyle(PlainListStyle())
        .popupOverContext(item: $quickOrder, style: .blur, content: popupMessage(product:))
        .navigationBarTitle("과일마트")
    }
    
    var showFavorite: Bool {
        !store.products.filter({$0.isFavorite}).isEmpty
    }
}

func popupMessage(product: Product) -> some View {
    let name = product.name.split(separator: " ").last!
    return VStack {
        Text(name)
            .font(.title).bold().kerning(3)
            .foregroundColor(.peach)
            .padding()
        
        OrderCompletedMessage()
    }
}

struct Home_Previews: PreviewProvider {
  static var previews: some View {
    Preview(source: Home())
        .environmentObject(Store())
  }
}
