package com.example.lab2ejemplo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.OnClickListener
import android.widget.Button

import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.appcompat.widget.Toolbar
import com.example.lab2ejemplo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),ContadorListener {
    var fra1:FragmentUno? = null
    var fra2:FragmentDos? = null
    var btnMain1:Button? = null
    var btnMain2: Button? = null

    lateinit var binding: ActivityMainBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)
        setContentView(R.layout.activity_main)


        super.onCreate(savedInstanceState)


        val toolbar = findViewById<Toolbar>(R.id.includeToolbar)



        setSupportActionBar(toolbar)


        fra1 = FragmentUno()
        fra1?.addContadorListener(this)
        fra2 = FragmentDos()
        fra2?.addListener(this)

        //btnMain1 = findViewById(R.id.btnMain1)
        btnMain1 = binding.btnMain1;
       // btnMain2 = findViewById(R.id.btnMain2)
        btnMain2 = binding.btnMain2

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


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val infla = menuInflater
        infla.inflate(R.menu.menutoolbar,menu)


        val item = menu?.findItem(R.id.itemBuscar)


        val sv = item?.actionView as android.widget.SearchView?
        sv?.setOnQueryTextListener( object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String?): Boolean {
                val activity = sv.context as MainActivity
                Toast.makeText(activity,newText,Toast.LENGTH_SHORT).show()

                return true
            }


            override fun onQueryTextSubmit(query: String?): Boolean {
                val activity = sv.context as MainActivity
                Toast.makeText(activity,"Buscar: "+query,Toast.LENGTH_SHORT).show()
                return true
            }

        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId

        if(itemId == R.id.itemGuardar){

            Toast.makeText(applicationContext,"Hizo clic en el item Guardar",Toast.LENGTH_SHORT).show()
        }else if(itemId == R.id.itemAjustes){
            Toast.makeText(applicationContext,"Hizo clic en el item Ajustes",Toast.LENGTH_SHORT).show()
        }





        return super.onOptionsItemSelected(item)


    }

}