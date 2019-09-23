package com.example.myapplication.root

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.activity_main.view.*

/**
 * Top level view for {@link RootBuilder.RootScope}.
 */
class RootView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), RootInteractor.RootPresenter {

    override fun onValidateClickListener() = RxView.clicks(vValidationButton)

    override fun showValidStatus(isValid: Boolean) {
        vRootBg.setBackgroundColor(if (isValid) Color.GREEN else Color.RED)
    }
}
