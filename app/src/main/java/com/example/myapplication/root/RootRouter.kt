package com.example.myapplication.root

import android.view.View
import com.example.myapplication.root.child.ChildBuilder

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link RootBuilder.RootScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class RootRouter(
    view: RootView,
    interactor: RootInteractor,
    component: RootBuilder.Component,
    private val childBuilder: ChildBuilder) : ViewRouter<RootView, RootInteractor, RootBuilder.Component>(view, interactor, component){
    fun attachChild(){
        val router = childBuilder.build(view)
        attachChild(router)
        view.addView(router.view)
    }
}
