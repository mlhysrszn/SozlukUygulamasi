package com.mlhysrszn.sozlukapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.mlhysrszn.sozlukapp.R
import com.mlhysrszn.sozlukapp.data.Database
import com.mlhysrszn.sozlukapp.data.KelimelerDAO
import com.mlhysrszn.sozlukapp.databinding.FragmentEkleBinding

class EkleFragment : Fragment() {
    
    private lateinit var binding: FragmentEkleBinding
    private lateinit var dbh: Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dbh = Database(requireContext())
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

        binding.toolbarEkle.title = "Sözlük Uygulaması"

        binding.toolbarEkle.setNavigationIcon(R.drawable.ic_back)
        binding.toolbarEkle.setNavigationOnClickListener {
            val action = EkleFragmentDirections.actionEkleFragmentToAnaFragment()
            Navigation.findNavController(it).navigate(action)
        }

        
        binding.buttonKaydet.setOnClickListener{
            Toast.makeText(context,"Eklendi",Toast.LENGTH_SHORT).show()

            val kelimeTurkce = binding.editTextTurkce.text.toString()
            val kelimeIngilizce = binding.editTextIngilizce.text.toString()

            KelimelerDAO().kelimeKaydet(dbh,kelimeIngilizce,kelimeTurkce)

            val action = EkleFragmentDirections.actionEkleFragmentToAnaFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}