//
//  GeometryView.swift
//  HelloSwiftUI
//
//  Created by cu on 2020/11/26.
//

import SwiftUI

struct GeometryView: View {
    var body: some View {
        GeometryReader { geometry in
            Text("Geometry Reader")
                .font(.largeTitle).bold()
                .position(x: geometry.size.width / 2,
                          y: geometry.safeAreaInsets.top + 50)
            
            VStack {
                Text("Size").bold()
                Text("width : \(Int(geometry.size.width))")
                Text("height : \(Int(geometry.size.height))")
            }
            .position(x: geometry.size.width / 2,
                      y: geometry.size.height / 2.5)
            
            VStack {
                Text("SafeAreaInsets").bold()
                Text("top : \(Int(geometry.safeAreaInsets.top))")
                Text("bottom : \(Int(geometry.safeAreaInsets.bottom))")
            }
            .position(x: geometry.size.width / 2,
                      y: geometry.size.height / 1.4)
            
        }
        .font(.title)
        .frame(height: 500)
        .border(Color.green, width: 5)
    }
}

struct GeometryView_Previews: PreviewProvider {
    static var previews: some View {
        GeometryView()
    }
}
