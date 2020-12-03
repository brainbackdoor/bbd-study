//
//  ContentStateView.swift
//  HelloSwiftUI
//
//  Created by cu on 2020/11/30.
//

import SwiftUI

struct ContentStateView: View {
    @State private var framework: String = "UIKit"
    @State private var isFavorite = true
    @State private var count = 0
    
    @ObservedObject var user: User
    
    var body: some View {
        HStack {
            Button(framework) {
                self.framework = "SwiftUI"
            }
            
            VStack(spacing: 30) {
                Toggle(isOn: $isFavorite) {
                    Text("isFavorite: \(isFavorite.description)")
                }
                Stepper("Count: \(count)", value: $count)
                
                Text(user.name)
                Button(action: {self.user.score += 1} , label: {
                    Text(user.score.description)
                })
            }
        }
    }
}

class User: ObservableObject {
    let name = "User Name"
    @Published var score = 0
}

struct ContentStateView_Previews: PreviewProvider {
    static var previews: some View {
        ContentStateView(user: User())
    }
}
