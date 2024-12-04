package com.quanticheart.basedialogfragment

import android.os.Bundle
import com.quanticheart.basedialogfragment.base.BaseDialogFragment
import com.quanticheart.basedialogfragment.databinding.DialogSimpleBinding

fun MainActivity.showTest1Dialog() {
    DialogFragmentTest1().show(this)
}

class DialogFragmentTest1 :
    BaseDialogFragment<DialogViewModel, DialogSimpleBinding>(DialogSimpleBinding::inflate) {

    override fun view(binding: DialogSimpleBinding, bundle: Bundle?) {
        binding.ok.setOnClickListener { dismissAllowingStateLoss() }
    }

    override fun viewModel(viewModel: DialogViewModel) {
        viewModel.message.observe(this) {
            binding.full.text = it
        }

        viewModel.setMessage("Hello World DialogFragmentTest1")
    }
}

fun MainActivity.showTest2Dialog(bundle: Bundle) {
    DialogFragmentTest2().show(this, bundle)
}

class DialogFragmentTest2 :
    BaseDialogFragment<DialogViewModel, DialogSimpleBinding>(DialogSimpleBinding::inflate) {

    override fun view(binding: DialogSimpleBinding, bundle: Bundle?) {
        binding.ok.setOnClickListener { dismissAllowingStateLoss() }
    }

    override fun viewModel(viewModel: DialogViewModel) {
        viewModel.message.observe(this) {
            if (arguments?.containsKey("name") == true)
                binding.full.text = arguments?.getString("name")

            binding.full.text = "${binding.full.text} -- $it"
        }

        viewModel.setMessage("Hello World DialogFragmentTest2")
    }
}

fun MainActivity.showTest3Dialog() {
    DialogFragmentTest3().show(this)
}

class DialogFragmentTest3 :
    BaseDialogFragment<DialogViewModel, DialogSimpleBinding>(DialogSimpleBinding::inflate, true) {

    override fun view(binding: DialogSimpleBinding, bundle: Bundle?) {
        binding.ok.setOnClickListener { dismissAllowingStateLoss() }
    }

    override fun viewModel(viewModel: DialogViewModel) {
        viewModel.message.observe(this) {
            if (arguments?.containsKey("name") == true)
                binding.full.text = arguments?.getString("name")

            binding.full.text = "${binding.full.text} -- $it"
        }

        viewModel.setMessage("Hello World DialogFragmentTest3")
    }
}

fun MainActivity.showTest4Dialog() {
    DialogFragmentTest4().show(this)
}

class DialogFragmentTest4 :
    BaseDialogFragment<DialogViewModel, DialogSimpleBinding>(
        DialogSimpleBinding::inflate,
        true,
        true
    ) {

    override fun view(binding: DialogSimpleBinding, bundle: Bundle?) {
        setToolbarTitle("FULLSCREEN DIALOG")
        binding.ok.setOnClickListener { dismissAllowingStateLoss() }
    }

    override fun viewModel(viewModel: DialogViewModel) {
        viewModel.message.observe(this) {
            if (arguments?.containsKey("name") == true)
                binding.full.text = arguments?.getString("name")

            binding.full.text = "${binding.full.text} -- $it"
        }

        viewModel.setMessage("Hello World DialogFragmentTest4")
    }
}

fun MainActivity.showTest5Dialog() {
    DialogFragmentTest5().show(this)
}

class DialogFragmentTest5 :
    BaseDialogFragment<DialogViewModel, DialogSimpleBinding>(
        DialogSimpleBinding::inflate,
        true,
        cancelable = false
    ) {

    override fun view(binding: DialogSimpleBinding, bundle: Bundle?) {
        setToolbarTitle("FULLSCREEN DIALOG")
        binding.ok.setOnClickListener { dismissAllowingStateLoss() }
    }

    override fun viewModel(viewModel: DialogViewModel) {
        viewModel.message.observe(this) {
            if (arguments?.containsKey("name") == true)
                binding.full.text = arguments?.getString("name")

            binding.full.text = "${binding.full.text} -- $it"
        }

        viewModel.setMessage("Hello World DialogFragmentTest5")
    }
}
