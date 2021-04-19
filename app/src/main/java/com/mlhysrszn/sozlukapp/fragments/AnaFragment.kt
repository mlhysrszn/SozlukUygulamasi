package com.mlhysrszn.sozlukapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mlhysrszn.sozlukapp.data.Kelimeler
import com.mlhysrszn.sozlukapp.R
import com.mlhysrszn.sozlukapp.adapter.KelimelerAdapter
import com.mlhysrszn.sozlukapp.databinding.FragmentAnaBinding

class AnaFragment : Fragment() {

    private lateinit var binding: FragmentAnaBinding
    private lateinit var adapter: KelimelerAdapter
    private lateinit var kelimelerListe: ArrayList<Kelimeler>
    private lateinit var rv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val k1 = Kelimeler(1,"Door","Kapı")
        val k2 = Kelimeler(2,"Door","Kapı")
        val k3 = Kelimeler(3,"Door","Kapı")
        val k4 = Kelimeler(4,"Door","Kapı")
        val k5 = Kelimeler(5,"Door","Kapı")

        kelimelerListe = ArrayList()
        kelimelerListe.add(k1)
        kelimelerListe.add(k2)
        kelimelerListe.add(k3)
        kelimelerListe.add(k4)
        kelimelerListe.add(k5)

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

        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(context)

        adapter = KelimelerAdapter(kelimelerListe)
        rv.adapter = adapter
    }
}