package com.example.myapplication.root.child

import com.example.myapplication.root.validation.Validable
import com.example.myapplication.root.validation.ValidationListener
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Coordinates Business Logic for [ChildScope].
 */
@RibInteractor
class ChildInteractor : Interactor<ChildInteractor.ChildPresenter, ChildRouter>(), Validable {

    @Inject
    lateinit var presenter: ChildPresenter
    @Inject
    lateinit var listener: Listener

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        // can be moved to the base class, for example ValidableInteractor
        listener.onValidationReady(validate())
    }

    override fun validate(): Single<Boolean> = presenter.getCurrentText().map { it.length > 2 }

    interface Listener : ValidationListener

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface ChildPresenter {
        fun getCurrentText(): Single<String>
    }
}
