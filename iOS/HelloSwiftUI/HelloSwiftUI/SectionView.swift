//
//  SectionView.swift
//  HelloSwiftUI
//
//  Created by cu on 2020/11/26.
//

import SwiftUI

struct SectionView: View {

    var body: some View {
        let fruits = ["사과", "바나나", "포도", "배"]
        let drinks = ["물", "우유", "탄산수"]
        let titles = ["Fruits", "Drinks"]
        let data = [fruits, drinks]
        
        return List {
            ForEach(data.indices) { index in
                Section(
                    header: Text(titles[index]).font(.title),
                    footer: HStack {
                        Spacer();
                        Text("\(data[index].count)건")
                    }
                ) {
                    ForEach(data[index], id: \.self) {
                        Text($0).font(.body)
                    }
                }
            }
        }
        .listStyle(GroupedListStyle())
        .environment(\.horizontalSizeClass,.regular)
        

    }
}

struct SectionView_Previews: PreviewProvider {
    static var previews: some View {
        SectionView()
    }
}
