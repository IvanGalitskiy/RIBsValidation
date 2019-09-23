package com.example.myapplication.root

import com.example.myapplication.root.child.ChildInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function
import javax.inject.Inject

/**
 * Coordinates Business Logic for [RootScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class RootInteractor : Interactor<RootInteractor.RootPresenter, RootRouter>() {

    @Inject
    lateinit var presenter: RootPresenter
    private val disposables = CompositeDisposable()

    var validations = ArrayList<Single<Boolean>>()

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        router.attachChild()

        disposables.add(presenter.onValidateClickListener()
            .subscribe {
                Single.zip(validations) {
                   !it.contains(false)
                }
                    .subscribe({
                        presenter.showValidStatus(it)
                    }, {})
            })
    }

    override fun willResignActive() {
        disposables.clear()
        super.willResignActive()
    }

    inner class ChildListener : ChildInteractor.Listener {
        override fun onValidationReady(validationSingle: Single<Boolean>) {
            validations.add(validationSingle)
        }
    }

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface RootPresenter {
        fun showValidStatus(isValid: Boolean)
        fun onValidateClickListener(): Observable<Any>
    }
}
