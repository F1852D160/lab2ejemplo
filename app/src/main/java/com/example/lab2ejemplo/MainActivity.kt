package com.example.lab2ejemplo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(),ContadorListener {
    var fra1:FragmentUno? = null
    var fra2:FragmentDos? = null
    var btnMain1:Button? = null
    var btnMain2: Button? = null


    var cont = 0
    override fun incrementar() {
        cont++
    }

    override fun getValorActual(): Int {
        return cont
    }

    override fun resetear() {
        cont = 0

    }

    override fun reducir() {
        if (cont >0)cont--
        else cont = 0
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fra1 = FragmentUno()
        fra1?.addContadorListener(this)
        fra2 = FragmentDos()
        fra2?.addListener(this)

        btnMain1 = findViewById(R.id.btnMain1)
        btnMain2 = findViewById(R.id.btnMain2)

        btnMain1?.setOnClickListener(OnClickListener {
            Toast.makeText(this,"Abriendo fragmento 1",Toast.LENGTH_SHORT).show()
            val transaction = getSupportFragmentManager().beginTransaction()
            transaction.replace(R.id.fragmentContainer, fra1!!)
            transaction.commit()
        })

        btnMain2?.setOnClickListener(OnClickListener {
            Toast.makeText(this,"Abriendo fragmento 2",Toast.LENGTH_SHORT).show()
            val transaction = getSupportFragmentManager().beginTransaction()
            transaction.replace(R.id.fragmentContainer, fra2!!)
            transaction.commit()
        })

    }

}