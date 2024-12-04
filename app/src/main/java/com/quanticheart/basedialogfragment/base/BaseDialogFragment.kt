package com.quanticheart.basedialogfragment.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.quanticheart.basedialogfragment.R
import com.quanticheart.basedialogfragment.databinding.DialogBaseBinding
import java.lang.reflect.ParameterizedType
import java.util.UUID

typealias Inflate<T> = (LayoutInflater) -> T

abstract class BaseDialogFragment<VM : ViewModel, VB : ViewBinding>(
    private val inflate: Inflate<VB>,
    private val fullScreen: Boolean = false,
    private val animate: Boolean = false,
    private val cancelable: Boolean = true,
) :
    DialogFragment() {

    private lateinit var internalBiding: DialogBaseBinding

    lateinit var viewModel: VM
        private set
    lateinit var binding: VB
        private set

    abstract fun view(binding: VB, bundle: Bundle?)
    abstract fun viewModel(viewModel: VM)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (fullScreen)
            setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        internalBiding = DialogBaseBinding.inflate(inflater, container, false)

        val parameterizedType = javaClass.genericSuperclass as? ParameterizedType

        @Suppress("UNCHECKED_CAST")
        val vmClass = parameterizedType?.actualTypeArguments?.getOrNull(0) as Class<VM>
        viewModel = ViewModelProvider(this)[vmClass]
        binding = inflate.invoke(layoutInflater)

        return internalBiding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view(binding, arguments)
        internalBiding.containerBase.addView(binding.root)
        viewModel(viewModel)
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height =
                if (fullScreen) ViewGroup.LayoutParams.MATCH_PARENT else ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window!!.setLayout(width, height)
            if (animate)
                dialog.window!!.setWindowAnimations(R.style.Slide)

            setCancelable(cancelable)
        }
    }

    fun setToolbarTitle(title: String, showBack: Boolean = true) {
        if (showBack)
            internalBiding.back.setOnClickListener {
                dismissAllowingStateLoss()
            }
        else
            internalBiding.back.visibility = View.GONE

        internalBiding.title.text = title
        internalBiding.toolbar.visibility = View.VISIBLE
    }

    fun show(activity: AppCompatActivity, bundle: Bundle? = null) {
        val randomTag = UUID.randomUUID().toString()
        bundle?.let {
            arguments = it
        }
        show(activity.supportFragmentManager.beginTransaction(), randomTag)
    }

    fun show(fragment: Fragment, bundle: Bundle? = null) {
        val randomTag = UUID.randomUUID().toString()
        bundle?.let {
            arguments = it
        }
        show(fragment.parentFragmentManager.beginTransaction(), randomTag)
    }
}
