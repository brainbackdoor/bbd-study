//
//  DataStructure.swift
//  HelloSwiftUI
//
//  Created by cu on 2020/11/26.
//

import SwiftUI

struct DataStructure: View {
    var body: some View {
        let fruits = ["사과", "바나나", "포도", "배"]
        let drinks = ["물", "우유", "탄산수"]
        VStack{
//            List{
//                Text("List").font(.largeTitle)
//                Circle().frame(width: 100, height: 100)
//                Color(.red).frame(width: /*@START_MENU_TOKEN@*/100/*@END_MENU_TOKEN@*/, height: /*@START_MENU_TOKEN@*/100/*@END_MENU_TOKEN@*/)
//            }
            
            List{
                Text("Fruits").font(.largeTitle)
                ForEach(fruits, id: \.self) {
                    Text($0)
                }
                
                Text("Drinks").font(.largeTitle)
                ForEach(drinks, id: \.self) {
                    Text($0)
                        .font(.body)
                        .fontWeight(.bold)
                        .foregroundColor(Color.blue)
                }
            }
        }

    }
}

struct DataStructure_Previews: PreviewProvider {
    static var previews: some View {
        DataStructure()
    }
}
