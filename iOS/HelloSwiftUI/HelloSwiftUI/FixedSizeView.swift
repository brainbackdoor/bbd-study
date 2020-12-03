//
//  FixedSizeView.swift
//  HelloSwiftUI
//
//  Created by cu on 2020/11/26.
//

import SwiftUI

struct FixedSizeView: View {
    var body: some View {
        VStack {
//            Text("Frame Modifier")
//                .font(.title).bold().frame(width: 80, height: 30)
//            Rectangle()
//            Color.red
//            Image(systemName: "bell").resizable()
            
            Text("Frame Modifier")
                .font(.title).bold().frame(width: 80, height: 30)
                .fixedSize(horizontal: true, vertical: false)
            Rectangle().frame(idealWidth: 100).fixedSize()
            Color.red.fixedSize()
            Image(systemName: "bell").resizable().fixedSize()
            
        }
    }
}

struct FixedSizeView_Previews: PreviewProvider {
    static var previews: some View {
        FixedSizeView()
    }
}
