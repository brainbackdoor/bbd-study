//
//  MainTabView.swift
//  FruitMart
//
//  Created by cu on 2020/12/07.
//  Copyright © 2020 Giftbot. All rights reserved.
//

import SwiftUI

struct MainTabView: View {
    private enum Tabs {
        case home, recipe, gallery, myPage
    }
    
    @State private var selectedTab: Tabs = .home
    
    var body: some View {
        TabView(selection: $selectedTab) {
            Group {
                home
                recipe
                imageGallery
                myPage
            }
            .accentColor(Color.primary)
        }
        .accentColor(.peach)
        .edgesIgnoringSafeArea(.top)
        .statusBar(hidden: selectedTab == .recipe)
    }
}

fileprivate extension View {
    func tabItem(image: String, text: String) -> some View {
        self.tabItem {
            Symbol(image, scale: .large)
                .font(Font.system(size: 17, weight: .light))
            Text(text)
        }
    }
}

private extension MainTabView {
    var home: some View {
        Home()
            .tag(Tabs.home)
            .tabItem (image: "house", text: "홈")
            .onAppear {
                UITableView.appearance().separatorStyle = .none
            }
    }
    
    var recipe: some View {
        RecipeView()
            .tag(Tabs.recipe)
            .tabItem(image: "book", text: "레시피")
    }
    
    var imageGallery: some View {
        Text("이미지 갤러리")
            .tag(Tabs.gallery)
            .tabItem(image: "photo.on.rectangle", text: "갤러리")
    }
    
    var myPage: some View {
        MyPage()
            .tag(Tabs.myPage)
            .tabItem(image: "person", text: "마이페이지")
            .onAppear{UITableView.appearance().separatorStyle = .singleLine}
    }
}

struct MainTabView_Previews: PreviewProvider {
    static var previews: some View {
        MainTabView()
    }
}
