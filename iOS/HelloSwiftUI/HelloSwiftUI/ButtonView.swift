//
//  ButtonView.swift
//  HelloSwiftUI
//
//  Created by cu on 2020/11/26.
//

import SwiftUI

struct ButtonView: View {
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
//                    NavigationLink(destination: Text("뒤로 가기")) {
//                        Circle()
//                            .frame(width: 80, height: 80)
//                            
//                    }.navigationBarTitle("내비게이션 링크")
//                    .buttonStyle(PlainButtonStyle())
                    Circle()
                        .navigationBarItems(trailing: trailingItem)
                        .navigationBarTitle("내비게이션 바 타이틀")
                        .frame(width: 80, height: 80)
                }
            }
            HStack(spacing: 30) {
                Button("Button") {
                    print("Button1")
                }
                Button(action: {print("Button2")}) {
                    /*@START_MENU_TOKEN@*/Text("Button")/*@END_MENU_TOKEN@*/
                        .padding()
                        .background(
                            RoundedRectangle(cornerRadius: 10)
                                .strokeBorder())
                }
                Button(action: {print("Button3")}) {
                    Circle()
                        .stroke(lineWidth: 2)
                        .frame(width: 80, height: 80)
                        .overlay(Text("Button"))
                }
            }
            HStack(spacing: 20) {
                Button(action: {print("Button4")}){
                    Image("recipe01")
                        .renderingMode(.original)
                        .resizable()
                        .frame(width: 120, height: 120)
                }.buttonStyle(PlainButtonStyle())

                Button(action: {print("Button")}) {
                    Image(systemName: "play.circle")
                        .imageScale(.large)
                        .font(.largeTitle)
                        .onTapGesture {
                            print("onTapGesture")
                        }
                }

            }
            .padding()

        }
    }
}

struct ButtonView_Previews: PreviewProvider {
    static var previews: some View {
        ButtonView()
    }
}
