package com.mlhysrszn.sozlukapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mlhysrszn.sozlukapp.data.Kelimeler
import com.mlhysrszn.sozlukapp.R
import com.mlhysrszn.sozlukapp.adapter.KelimelerAdapter
import com.mlhysrszn.sozlukapp.data.Database
import com.mlhysrszn.sozlukapp.data.KelimelerDAO
import com.mlhysrszn.sozlukapp.databinding.FragmentAnaBinding

class AnaFragment : Fragment() {

    private lateinit var binding: FragmentAnaBinding
    private lateinit var adapter: KelimelerAdapter
    private lateinit var kelimelerListe: ArrayList<Kelimeler>
    private lateinit var rv: RecyclerView
    private lateinit var dbh: Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dbh = Database(requireContext())
        kelimelerListe = KelimelerDAO().tumKelimeler(dbh)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ana, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAnaBinding.bind(view)
        rv = binding.rv

        binding.toolbar.title = "Sözlük Uygulaması"

        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(context)

        adapter = KelimelerAdapter(kelimelerListe)
        rv.adapter = adapter

        binding.fabEkle.setOnClickListener {
            val action = AnaFragmentDirections.actionAnaFragmentToEkleFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}