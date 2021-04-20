package com.mlhysrszn.sozlukapp.data

import android.content.ContentValues

class KelimelerDAO {

    fun tumKelimeler(dbh: Database): ArrayList<Kelimeler> {
        val kelimelerListe = ArrayList<Kelimeler>()

        val db = dbh.readableDatabase
        val c = db.rawQuery("SELECT * FROM kelimeler", null)

        while (c.moveToNext()) {
            val kelime = Kelimeler(
                c.getInt(c.getColumnIndex("kelime_id")),
                c.getString(c.getColumnIndex("ingilizce")),
                c.getString(c.getColumnIndex("turkce"))
            )
            kelimelerListe.add(kelime)
        }
        return kelimelerListe
    }

    fun kelimeGetir(dbh: Database, arananKelime: String): ArrayList<Kelimeler> {
        val kelimelerListe = ArrayList<Kelimeler>()

        val db = dbh.readableDatabase
        val c = db.rawQuery("SELECT * FROM kelimeler WHERE ingilizce LIKE '$arananKelime'", null)

        while (c.moveToNext()) {
            val kelime = Kelimeler(
                c.getInt(c.getColumnIndex("kelime_id")),
                c.getString(c.getColumnIndex("ingilizce")),
                c.getString(c.getColumnIndex("turkce"))
            )
            kelimelerListe.add(kelime)
        }
        return kelimelerListe
    }

    fun kelimeKaydet(dbh: Database, kelimeIngilizce: String, kelimeTurkce: String) {
        //todo
        val db = dbh.writableDatabase
        val values = ContentValues()

        values.put("ingilizce", kelimeIngilizce)
        values.put("turkce", kelimeTurkce)

        db.insertOrThrow("kisiler",null,values)
        db.close()

    }
}