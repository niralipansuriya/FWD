package com.swipefwd.ui.swiping.dater_both

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import com.swipefwd.R
import com.swipefwd.databinding.FragmentSwipeImageBinding


class SwipeImageFragment : Fragment() {

    private var _binding: FragmentSwipeImageBinding? = null
    private val binding get() = _binding!!
    private var image: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            image = it.getString(ARG_POSITION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSwipeImageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            /* Glide.with(requireActivity())
                 .load(image)
                 .diskCacheStrategy(DiskCacheStrategy.ALL)
                 .error(R.mipmap.ic_launcher)
                 .placeholder(R.mipmap.ic_launcher)
                 .into(imgSwipeProfile)*/
            try {
                Picasso.get().load(image).placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .into(imgSwipeProfile, object : Callback {
                        override fun onSuccess() {
                        }
                        override fun onError(e: Exception?) {
                        }
                    });
            } catch (e: Exception) {
                imgSwipeProfile.setImageDrawable(
                    activity?.let {
                        ContextCompat.getDrawable(
                            it,
                            R.mipmap.ic_launcher
                        )
                    }
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_POSITION = "position"

        @JvmStatic
        fun newInstance(image: String) =
            SwipeImageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_POSITION, image)
                }
            }
    }
}