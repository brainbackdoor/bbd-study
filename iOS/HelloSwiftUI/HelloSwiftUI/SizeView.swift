//
//  SizeView.swift
//  HelloSwiftUI
//
//  Created by cu on 2020/11/26.
//

import SwiftUI

struct SizeView: View {
    var body: some View {
        HStack {
            Rectangle().fill(Color.red).frame(minWidth: 100)
            
            Rectangle().fill(Color.orange).frame(maxWidth: 15)
            
            Rectangle().fill(Color.yellow).frame(height: 150)
            Rectangle().fill(Color.green).frame(maxHeight: .infinity)
            
            Rectangle().fill(Color.blue).frame(maxWidth: .infinity, maxHeight: .infinity)
            Rectangle().fill(Color.purple)
        }
        .frame(width: 300, height: 150)
    }
}

struct SizeView_Previews: PreviewProvider {
    static var previews: some View {
        SizeView()
    }
}
