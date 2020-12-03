//
//  CustomViewModifier.swift
//  HelloSwiftUI
//
//  Created by cu on 2020/12/03.
//

import SwiftUI

struct TestView: View {
    var body: some View {
//        Text(/*@START_MENU_TOKEN@*/"Hello, World!"/*@END_MENU_TOKEN@*/)
//            .modifier(CustomViewModifier(borderColor: .blue))
//        ModifiedContent(content: Text("Custom"), modifier: CustomViewModifier(borderColor: .blue))
        
//          Text(/*@START_MENU_TOKEN@*/"Hello, World!"/*@END_MENU_TOKEN@*/)
//            .modifier(MyModifier1().concat(MyModifier2()).concat(CustomViewModifier()))
        Text("Custom 3")
            .customModifier(borderColor: .orange)
    }
}


struct CustomViewModifier: ViewModifier {
    var borderColor: Color = .red
    
    func body(content: Content) -> some View {
        content
            .font(.title)
            .foregroundColor(.white)
            .padding()
            .background(Rectangle().fill(Color.gray))
            .border(borderColor, width: 2)
    }
}

struct MyModifier1: ViewModifier {
    func body(content: Content) -> some View {
        content.font(.title)
    }
}

struct MyModifier2: ViewModifier {
    func body(content: Content) -> some View {
        content.foregroundColor(.blue)
    }
}

extension View {
    func customModifier(borderColor: Color = .red) -> some View {
        self
            .modifier(CustomViewModifier(borderColor: borderColor))
    }
}

struct CustomViewModifier_Previews: PreviewProvider {
    static var previews: some View {
        TestView()
    }
}
