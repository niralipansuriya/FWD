package com.swipefwd.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding


/**base class for fragment*/
abstract class BaseBindingFragment<VB : ViewBinding> : Fragment() {
    private var _binding: VB? = null

    abstract fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = onCreateViewBinding(inflater, container, savedInstanceState)
        return binding().root
    }//onCreateView

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }//onDestroyView


    // This property is only valid between onCreateView and
    // onDestroyView.
    fun binding() = _binding!!
}