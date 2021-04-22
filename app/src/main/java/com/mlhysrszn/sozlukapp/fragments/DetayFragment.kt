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
import com.mlhysrszn.sozlukapp.databinding.FragmentDetayBinding

class DetayFragment : Fragment() {

    private lateinit var binding: FragmentDetayBinding
    private lateinit var dbh: Database
    private var kelimeId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dbh = Database(requireContext())
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

        binding.toolbarDetay.title = "Sözlük Uygulaması"
        binding.toolbarDetay.setNavigationIcon(R.drawable.ic_back)
        binding.toolbarDetay.setNavigationOnClickListener {
            val action = DetayFragmentDirections.actionDetayFragmentToAnaFragment()
            Navigation.findNavController(it).navigate(action)
        }

        arguments?.let {
            val ingilizce = DetayFragmentArgs.fromBundle(it).ingilizce
            val turkce = DetayFragmentArgs.fromBundle(it).turkce
            kelimeId = DetayFragmentArgs.fromBundle(it).kelimeId

            binding.textViewIngilizce.text = ingilizce
            binding.textViewTurkce.text = turkce
        }

        binding.buttonSil.setOnClickListener {
            Toast.makeText(context,"Silindi",Toast.LENGTH_SHORT).show()

            KelimelerDAO().kelimesil(dbh,kelimeId)
            val action = DetayFragmentDirections.actionDetayFragmentToAnaFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}