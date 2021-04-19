package com.mlhysrszn.sozlukapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.mlhysrszn.sozlukapp.R
import com.mlhysrszn.sozlukapp.databinding.FragmentEkleBinding

class EkleFragment : Fragment() {
    
    private lateinit var binding: FragmentEkleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ekle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEkleBinding.bind(view)
        
        val kelimeTurkce = binding.editTextTurkce.text
        val kelimeIngilizce = binding.editTextIngilizce.text
        
        binding.buttonKaydet.setOnClickListener{
            Toast.makeText(context,"Eklendi",Toast.LENGTH_SHORT).show()
            // TODO: 4/18/2021

            it.findNavController().navigate(R.id.action_ekleFragment_to_anaFragment)
        }
    }


}