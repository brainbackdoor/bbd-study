//
//  ProductRow.swift
//  FruitMart
//
//  Created by cu on 2020/11/25.
//  Copyright © 2020 Giftbot. All rights reserved.
//

import SwiftUI

struct ProductRow: View {
    let product: Product
    
    var body: some View {
        HStack {
            productImage
            productionDescription
        }
        .frame(height: 150)
        .background(Color.primary.colorInvert())
        .cornerRadius(6)
        .shadow(color: Color.primaryShadow, radius: 1, x: 2, y: 2)
        .padding(.vertical, 8)
    }
    
    var productImage: some View {
        Image(product.imageName)
            .resizable()
            .scaledToFit()
            .frame(width: 140)
            .clipped()
    }

    var productionDescription: some View {
        VStack(alignment: .leading) {
            Spacer()
            Text(product.name)
                .font(.headline)
                .fontWeight(.medium)
                .padding(.bottom, 6)
            Text(product.description)
                .font(.footnote)
                .foregroundColor(.secondaryText)
            
            footerView
        }
    }

    var footerView: some View {
        HStack(spacing: 0) {
            Text("₩").font(.footnote)
                + Text("\(product.price)").font(.headline)
            Spacer()
            
            FavoriteButton(product: product)
            Image(systemName: "cart")
                .foregroundColor(Color.peach)
                .frame(width: 32, height: 32)
        }
        .padding([.leading, .bottom], 12)
        .padding([.top, .trailing])
    }
    

}

struct ProductRow_Previews: PreviewProvider {
    static var previews: some View {
        ProductRow(product: productSamples[0])
    }
}
