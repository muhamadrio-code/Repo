package com.riopermana.settings.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.riopermana.settings.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ThemeSelectionDialog : DialogFragment() {
    private val viewModel: ThemeSelectionDialogViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        subscribeCollector()
        return getDialogBuilder().create()
    }

    private fun getDialogBuilder() : AlertDialog.Builder {
        val themes = arrayOf(
            getString(R.string.default_system),
            getString(R.string.light),
            getString(R.string.dark)
        )

        return AlertDialog.Builder(requireContext()).apply {
            setTitle(getString(R.string.select_theme))
            setSingleChoiceItems(
                themes, viewModel.getCurrentPosition()
            ) { _, which ->
                viewModel.onSelectTheme(which)
            }
            setCancelable(false)
            setNegativeButton(getString(R.string.cancel)) { _, _ ->
                dismiss()
            }
            setPositiveButton(getString(R.string.select)) { _, _ ->
                viewModel.onSubmitTheme()
            }
            setOnDismissListener{
                onDismiss(it)
            }
        }
    }


    private fun subscribeCollector() {
        lifecycleScope.launch {
            viewModel.darkThemeConfig.collect()
        }
    }

}