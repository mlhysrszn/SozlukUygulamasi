package com.mlhysrszn.sozlukapp.fragments

import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mlhysrszn.sozlukapp.activity.MainActivity
import com.mlhysrszn.sozlukapp.R
import com.mlhysrszn.sozlukapp.adapter.KelimelerAdapter
import com.mlhysrszn.sozlukapp.data.Database
import com.mlhysrszn.sozlukapp.data.Kelimeler
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
        setHasOptionsMenu(true)

        dbh = Database(requireContext())
        kelimelerListe = KelimelerDAO().tumKelimeler(dbh)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(this, callback)
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

        val toolbarAna = binding.toolbar
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbarAna)

        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(context)

        adapter = KelimelerAdapter(kelimelerListe)
        rv.adapter = adapter

        binding.fabEkle.setOnClickListener {
            val action = AnaFragmentDirections.actionAnaFragmentToEkleFragment()
                Navigation.findNavController(it).navigate(action)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)

        val searchView = SearchView(((context as MainActivity).supportActionBar?.themedContext ?: context)!!)
        menu.findItem(R.id.action_ara).apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
            actionView = searchView
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isNotEmpty()){
                    arama(query)
                }
                else{
                    kelimelerListe = KelimelerDAO().tumKelimeler(dbh)
                    adapter = KelimelerAdapter(kelimelerListe)
                    rv.adapter = adapter
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isNotEmpty()){
                    arama(newText)
                }
                else{
                    kelimelerListe = KelimelerDAO().tumKelimeler(dbh)
                    adapter = KelimelerAdapter(kelimelerListe)
                    rv.adapter = adapter
                }
                return true
            }
        })

        return super.onCreateOptionsMenu(menu, inflater)
    }

    fun arama(kelime: String){
        kelimelerListe = KelimelerDAO().kelimeGetir(dbh, kelime)
        adapter = KelimelerAdapter(kelimelerListe)
        rv.adapter = adapter
    }
}