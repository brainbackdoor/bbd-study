//
//  FrameView.swift
//  HelloSwiftUI
//
//  Created by cu on 2020/11/26.
//

import SwiftUI

struct FrameView: View {
    var body: some View {
        HStack {
            Rectangle().fill(Color.yellow).frame(width: 30)
            
            VStack {
                Rectangle().fill(Color.blue).frame(height: 200)
                
                GeometryReader {
                    self.contents(geometry: $0)
                }
                .background(Color.green)
                .border(Color.red, width: 4)
            
            }
            .coordinateSpace(name: "VStackCS")
        }
        .coordinateSpace(name: "HStackCS")
    }
    
    func contents(geometry g: GeometryProxy) -> some View {
        VStack {
            Text("Local").bold()
            Text(stringFormat(for: g.frame(in: .local).origin))
                .padding(.bottom)
            
            Text("Global").bold()
            Text(stringFormat(for: g.frame(in: .global).origin))
                .padding(.bottom)
            
            Text("Named VStackCS").bold()
            Text(stringFormat(for: g.frame(in: .named("VStackCS")).origin))
                .padding(.bottom)
            
            Text("Named HStackCS").bold()
            Text(stringFormat(for: g.frame(in: .named("HStackCS")).origin))
                .padding(.bottom)
            
        }
    }
    
    func stringFormat(for point: CGPoint) -> String {
        String(format: "(x: %.f, y: %.f)",arguments: [point.x, point.y])
    }
}


struct FrameView_Previews: PreviewProvider {
    static var previews: some View {
        FrameView()
    }
}
