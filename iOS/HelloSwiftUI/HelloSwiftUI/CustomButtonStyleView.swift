//
//  CustomButtonStyleView.swift
//  HelloSwiftUI
//
//  Created by cu on 2020/12/03.
//

import SwiftUI

struct CustomButtonStyleView: View {
    var body: some View {
        VStack(spacing: 20) {
            Button("커스텀 버튼 스타일 1") {}
            .buttonStyle(CustomButtonStyle(backgroundColor: .green))
            
            Button("커스텀 버튼 스타일 2") {}
                .buttonStyle(CustomButtonStyle(cornerRadius: 20))
            
            Button("프리미티브 버튼 스타일 1") {}
                .buttonStyle(CustomPrimitiveButtonStyle())
            
            Button("프리미티브 버튼 스타일 2") {}
                .buttonStyle(CustomPrimitiveButtonStyle(minimumDuration: 1))
            
        }

    }
}

struct CustomButtonStyle: ButtonStyle {
    var backgroundColor: Color = .blue
    var cornerRadius: CGFloat = 6
    
    func makeBody(configuration: Configuration) -> some View {
        configuration
            .label
            .foregroundColor(.white)
            .padding()
            .background(RoundedRectangle(cornerRadius: cornerRadius)
                            .fill(backgroundColor))
            .scaleEffect(configuration.isPressed ? 0.7 : 1.0 )
    }
}

struct CustomPrimitiveButtonStyle: PrimitiveButtonStyle {
    var minimumDuration = 0.5
    
    func makeBody(configuration: Configuration) -> some View {
        ButtonStyleBody(configuration: configuration, minimumDuration: minimumDuration)
    }
    
    private struct ButtonStyleBody: View {
        let configuration: Configuration
        let minimumDuration: Double
        @GestureState private var isPressed = false
        
        var body: some View {
            let longPress = LongPressGesture(minimumDuration:minimumDuration)
                .updating($isPressed) {
                    value, state, _ in state = value
                }
                .onEnded{_ in self.configuration.trigger() }
            
            return configuration.label
                .foregroundColor(.white)
                .padding()
                .background(RoundedRectangle(cornerRadius: 10).fill(Color.green))
                .scaleEffect(isPressed ? 0.8 : 1.0)
                .opacity(isPressed ? 0.6 : 1.0)
                .gesture(longPress)
        }
    }
}

struct CustomButtonStyleView_Previews: PreviewProvider {
    static var previews: some View {
        CustomButtonStyleView()
    }
}
