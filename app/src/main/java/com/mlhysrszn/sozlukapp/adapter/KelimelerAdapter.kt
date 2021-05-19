package com.mlhysrszn.sozlukapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.animation.AnimationUtils
import com.mlhysrszn.sozlukapp.R
import com.mlhysrszn.sozlukapp.data.Kelimeler
import com.mlhysrszn.sozlukapp.databinding.CardTasarimBinding
import com.mlhysrszn.sozlukapp.fragments.AnaFragmentDirections

class KelimelerAdapter(private val kelimelerListe: List<Kelimeler>) :
    RecyclerView.Adapter<KelimelerAdapter.ViewHolder>() {

    inner class ViewHolder(binding: CardTasarimBinding) : RecyclerView.ViewHolder(binding.root) {
        val ingilizceText = binding.cardTextIngilizce
        val turkceText = binding.cardTextTurkce
        val kelimeCard = binding.root
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = CardTasarimBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val kelime = kelimelerListe[position]

        holder.ingilizceText.text = kelime.ingilizce
        holder.turkceText.text = kelime.turkce

        holder.kelimeCard.animation = android.view.animation.AnimationUtils.loadAnimation(holder.kelimeCard.context,R.anim.rv_anim)

        holder.kelimeCard.setOnClickListener {
            val action = AnaFragmentDirections.actionAnaFragmentToDetayFragment(kelime.ingilizce,kelime.turkce,kelime.kelime_id)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return kelimelerListe.size
    }

}