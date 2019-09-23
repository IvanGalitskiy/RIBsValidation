package com.example.myapplication.root.child

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myapplication.R
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.CLASS
import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Builder for the {@link ChildScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class ChildBuilder(dependency: ParentComponent) :
    ViewBuilder<ChildView, ChildRouter, ChildBuilder.ParentComponent>(dependency) {

    /**
     * Builds a new [ChildRouter].
     *
     * @param parentViewGroup parent view group that this router's view will be added to.
     * @return a new [ChildRouter].
     */
    fun build(parentViewGroup: ViewGroup): ChildRouter {
        val view = createView(parentViewGroup)
        val interactor = ChildInteractor()
        val component = DaggerChildBuilder_Component.builder()
            .parentComponent(dependency)
            .view(view)
            .interactor(interactor)
            .build()
        return component.childRouter()
    }

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): ChildView? {
        return inflater.inflate(R.layout.child, parentViewGroup, false) as ChildView
    }

    interface ParentComponent {
        fun listener(): ChildInteractor.Listener
    }

    @dagger.Module
    abstract class Module {

        @ChildScope
        @Binds
        internal abstract fun presenter(view: ChildView): ChildInteractor.ChildPresenter

        @dagger.Module
        companion object {

            @ChildScope
            @Provides
            @JvmStatic
            internal fun router(
                component: Component,
                view: ChildView,
                interactor: ChildInteractor
            ): ChildRouter {
                return ChildRouter(view, interactor, component)
            }
        }

        // TODO: Create provider methods for dependencies created by this Rib. These should be static.
    }

    @ChildScope
    @dagger.Component(
        modules = arrayOf(Module::class),
        dependencies = arrayOf(ParentComponent::class)
    )
    interface Component : InteractorBaseComponent<ChildInteractor>, BuilderComponent {

        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: ChildInteractor): Builder

            @BindsInstance
            fun view(view: ChildView): Builder

            fun parentComponent(component: ParentComponent): Builder
            fun build(): Component
        }
    }

    interface BuilderComponent {
        fun childRouter(): ChildRouter
    }

    @Scope
    @Retention(CLASS)
    internal annotation class ChildScope

    @Qualifier
    @Retention(CLASS)
    internal annotation class ChildInternal
}
