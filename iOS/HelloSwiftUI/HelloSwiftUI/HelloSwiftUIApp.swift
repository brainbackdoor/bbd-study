//
//  HelloSwiftUIApp.swift
//  HelloSwiftUI
//
//  Created by cu on 2020/11/23.
//

import SwiftUI

@main
struct HelloSwiftUIApp: App {
    var body: some Scene {
        WindowGroup {
            ContentStateView(user: User())
        }
    }
}

