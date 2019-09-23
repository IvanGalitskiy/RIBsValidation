package com.example.myapplication.root.child

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import io.reactivex.Single
import kotlinx.android.synthetic.main.child.view.*

/**
 * Top level view for {@link ChildBuilder.ChildScope}.
 */
class ChildView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), ChildInteractor.ChildPresenter {
    // defer is important here!
    override fun getCurrentText(): Single<String> =
        Single.defer { Single.just(vChildEditText.text.toString()) }
}
