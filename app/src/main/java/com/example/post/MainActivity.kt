package com.example.post

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.isVisible
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var addbut: Button
    lateinit var vbut:Button
    lateinit var inname: EditText
    lateinit var inloc:EditText
    lateinit var prog:ProgressBar
    lateinit var tvw:TextView
    lateinit var ed:Button
    val apif = APIClient().getClient()?.create(APIInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        addbut.setOnClickListener {

            var f = dat.People(inname.text.toString(), inloc.text.toString())

            if(addpeople(f)){
                inname.setText("")
                inloc.setText("")

            }
        }
        vbut.setOnClickListener{
            viewusers()
        }
        ed.setOnClickListener{
            intent = Intent(applicationContext, deledit::class.java)
            intent.putExtra("back to",1)
            startActivity(intent)
        }
    }
    fun init(){
        addbut=findViewById(R.id.addbut)
        vbut= findViewById(R.id.view)
        inname=findViewById(R.id.addnam)
        inloc=findViewById(R.id.addloc)
        tvw=findViewById(R.id.wait)
        prog=findViewById(R.id.progressBar)
        ed=findViewById(R.id.eddel)
    }

    private fun addpeople(f: dat.People):Boolean {
        var re:Boolean = false
        wait(true)

        if (apif != null) {
            apif.adddat(f).enqueue(object : Callback<dat.People> {
                override fun onResponse(call: Call<dat.People>, response: Response<dat.People>) {
                    wait(false)
                    re=true
                    Toast.makeText(applicationContext, "Save Success!", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<dat.People>, t: Throwable) {
                    Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show();
                    wait(false)
                    re=false
                }
            })
        }
        return re
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