//
//  NavigationView.swift
//  HelloSwiftUI
//
//  Created by cu on 2020/11/26.
//

import SwiftUI

struct NavigationView: View {
    var body: some View {
        VStack {
            
            let trailingItem = HStack {
                Button(action: {print("Trailing")}){
                    Image(systemName: "bell").imageScale(.large)
                }
                
                Button(action: {print("Leading Item tappped")}) {
                    Image(systemName: "gear").imageScale(.large)
                }
            }
                
            NavigationView {
                VStack {
                    NavigationLink(destination: Text("뒤로 가기")) {
                        Circle()
                            
                            .frame(width: 80, height: 80)
                            
                    }.navigationBarTitle("내비게이션 링크")
                    .buttonStyle(PlainButtonStyle())
                    Circle()
                        .navigationBarItems(trailing: trailingItem)
                        .navigationBarTitle("내비게이션 바 타이틀")
                        .frame(width: 80, height: 80)
                }
            }
        }
    }
}

struct NavigationView_Previews: PreviewProvider {
    static var previews: some View {
        NavigationView()
    }
}
