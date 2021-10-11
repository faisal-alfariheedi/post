package com.example.post

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class ac2 : AppCompatActivity() {
    lateinit var rv: RecyclerView
    lateinit var but: Button
    lateinit var apif: APIInterface
    var pep: ArrayList<dat.People> = arrayListOf()
    lateinit var prog: ProgressBar
    lateinit var tvw: TextView
    lateinit var ed:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act2)
        init()
        but.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        ed.setOnClickListener{
            intent = Intent(applicationContext, deledit::class.java)
            intent.putExtra("back to",2)
            startActivity(intent)
        }

        if (apif != null) {
            apif.getdat()?.enqueue(object : Callback<List<dat.People>> {
                override fun onResponse(call: Call<List<dat.People>>, response: Response<List<dat.People>>) {
                    for (i in response.body()!!) {
                        pep.add(i)

                    }
                    rv.adapter?.notifyDataSetChanged()
                    wait(false)

                }

                override fun onFailure(call: Call<List<dat.People>>, t: Throwable) {

                    wait(false)
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show();
                }
            })
        }


    }



    fun init() {
        but = findViewById(R.id.addnew)
        rv = findViewById(R.id.rvma)
        rv.adapter = RVAdapter(pep)
        rv.layoutManager = LinearLayoutManager(this)
        apif = APIClient().getClient()?.create(APIInterface::class.java)!!
        tvw=findViewById(R.id.wait)
        prog=findViewById(R.id.progressBar)
        ed=findViewById(R.id.eddel)
        wait(true)
    }
    fun viewusers() {
        intent = Intent(applicationContext, ac2::class.java)
        startActivity(intent)
    }
    fun wait(a:Boolean){
        if(a){
            prog.isVisible = true
            tvw.isVisible = true
        }else{
            prog.isVisible=false
            tvw.isVisible=false
        }

    }
}