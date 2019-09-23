package com.example.myapplication.root.child

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link ChildBuilder.ChildScope}.
 *
 */
class ChildRouter(
    view: ChildView,
    interactor: ChildInteractor,
    component: ChildBuilder.Component) : ViewRouter<ChildView, ChildInteractor, ChildBuilder.Component>(view, interactor, component)
