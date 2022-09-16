package com.swipefwd.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.swipefwd.R
import com.swipefwd.databinding.FragmentMatchmakerActivityBinding

class FragmentMatchmakerActivity : Fragment() {
    private var _binding: FragmentMatchmakerActivityBinding? = null
    private val binding get() = _binding!!
    private val connectionAdapter by lazy {
        ConnectionAdapter(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatchmakerActivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.apply {
            txtConnections.text = getString(R.string.connector_connection, "6")
            rvConnections.adapter = connectionAdapter
        }
    }
}