//
//  ContentView.swift
//  HelloSwiftUI
//
//  Created by cu on 2020/11/23.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        Text("Hello, swiftUI! 👋🏻\ntitle")
            .font(.largeTitle)
            .foregroundColor(.blue)
            .fontWeight(.heavy)
            .lineLimit(1)
            .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

