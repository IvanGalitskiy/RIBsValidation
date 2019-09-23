package com.example.myapplication.root.child

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link ChildBuilder.ChildScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class ChildRouter(
    view: ChildView,
    interactor: ChildInteractor,
    component: ChildBuilder.Component) : ViewRouter<ChildView, ChildInteractor, ChildBuilder.Component>(view, interactor, component)
