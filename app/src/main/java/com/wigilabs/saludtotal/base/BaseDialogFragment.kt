package com.wigilabs.saludtotal.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager


abstract class BaseDialogFragment : DialogFragment() {


    override fun show(manager: FragmentManager, tag: String?) {
        val fragment = manager.findFragmentByTag(tag)
        val ft = manager.beginTransaction()
        if (fragment != null) {
            ft.remove(fragment)
            ft.commitAllowingStateLoss()
        }
        try {
            super.show(manager, tag)
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }



    override fun onStart() {
        super.onStart()
        isCancelable = false
        this.dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}