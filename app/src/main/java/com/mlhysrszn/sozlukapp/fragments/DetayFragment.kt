package com.mlhysrszn.sozlukapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.mlhysrszn.sozlukapp.R
import com.mlhysrszn.sozlukapp.databinding.FragmentDetayBinding

class DetayFragment : Fragment() {

    private lateinit var binding: FragmentDetayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detay, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetayBinding.bind(view)

        arguments?.let {
            val ingilizce = DetayFragmentArgs.fromBundle(it).ingilizce
            val turkce = DetayFragmentArgs.fromBundle(it).turkce

            binding.textViewIngilizce.text = ingilizce
            binding.textViewTurkce.text = turkce
        }

        binding.buttonSil.setOnClickListener {
            Toast.makeText(context,"Silindi",Toast.LENGTH_SHORT).show()
        }
    }
}