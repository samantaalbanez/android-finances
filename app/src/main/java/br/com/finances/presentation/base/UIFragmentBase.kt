package br.com.finances.presentation.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.finances.R
import br.com.finances.data.utils.StatusDataBase

abstract class UIFragmentBase: Fragment() {

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onObservers()
        onActions()
    }

    private fun showStatus(text: Int) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    protected fun configureStatus(status: StatusDataBase) {
        when (status) {
            StatusDataBase.SUCCESS -> {
                showStatus(R.string.registration_success)
                findNavController().navigateUp()
            }
            StatusDataBase.IDLE -> {}
            else -> showStatus(R.string.registration_error)
        }
    }

    abstract fun onActions()

    abstract fun onObservers()
}