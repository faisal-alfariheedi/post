package com.example.post

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.isVisible
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class deledit : AppCompatActivity() {
    lateinit var inid: EditText
    lateinit var inname:EditText
    lateinit var inloc:EditText
    lateinit var delbut: Button
    lateinit var edbut:Button
    lateinit var prog: ProgressBar
    lateinit var tvw: TextView
    lateinit var bacbut:Button
    lateinit var clean:Button
    val apif = APIClient().getClient()?.create(APIInterface::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deledit)
        init()
        edbut.setOnClickListener{
            var f = dat.People(inname.text.toString(), inloc.text.toString())
            editpeople(f)
        }
        delbut.setOnClickListener{
            deletepeople()
        }
        bacbut.setOnClickListener {
            if (intent.getIntExtra("back to", 0) == 1) {
                intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)

            } else if (intent.getIntExtra("back to", 0) == 2) {
                intent = Intent(applicationContext, ac2::class.java)
                startActivity(intent)
            }

        }
        clean.setOnClickListener{
            death()
        }

    }
    fun init(){
        inid=findViewById(R.id.inid)
        inname=findViewById(R.id.inname)
        inloc=findViewById(R.id.inloc)
        delbut= findViewById(R.id.delbut)
        edbut= findViewById(R.id.edbut)
        tvw=findViewById(R.id.wait)
        prog=findViewById(R.id.progressBar)
        bacbut= findViewById(R.id.back)
        clean=findViewById(R.id.cl)
    }

    private fun editpeople(f: dat.People) {

        wait(true)

        if (apif != null) {
            apif.updatedat(inid.text.toString().toInt(),f).enqueue(object : Callback<dat.People> {
                override fun onResponse(call: Call<dat.People>, response: Response<dat.People>) {
                    wait(false)
                    inid.setText("")
                    inname.setText("")
                    inloc.setText("")
                    Toast.makeText(applicationContext, "Save Success!", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<dat.People>, t: Throwable) {
                    Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show();
                    wait(false)

                }
            })
        }

    }
    private fun deletepeople() {

        wait(true)

        if (apif != null) {
            apif.deldat(inid.text.toString().toInt()).enqueue(object : Callback<dat.People> {
                override fun onResponse(call: Call<dat.People>, response: Response<dat.People>) {
                    wait(false)
                    inid.setText("")
                    inname.setText("")
                    inloc.setText("")
                    Toast.makeText(applicationContext, "Save Success!", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<dat.People>, t: Throwable) {
                    Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show();
                    wait(false)

                }
            })
        }

    }
    private fun death() {

        wait(true)

        if (apif != null) {
            for (i in 0..inid.text.toString().toInt())
            apif.deldat(i).enqueue(object : Callback<dat.People> {
                override fun onResponse(call: Call<dat.People>, response: Response<dat.People>) {
                    wait(false)
                    inid.setText("")
                    inname.setText("")
                    inloc.setText("")
                    Toast.makeText(applicationContext, "Save Success!", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<dat.People>, t: Throwable) {
                    Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show();
                    wait(false)

                }
            })
        }

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