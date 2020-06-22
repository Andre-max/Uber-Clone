package com.example.startapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.model.Tip
import kotlinx.android.synthetic.main.activity_tips.*

class TipsActivity : AppCompatActivity() {

    var listOfTips: ArrayList<Tip> = ArrayList()
    var listOfTipsStrings: ArrayList<String> = ArrayList()
    var adapter: ArrayAdapter<String>?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tips)

        var t1 = Tip(1, "Tip1", "PlaceHolder for Tip1" )
        var t2 = Tip(1, "Tip2", "PlaceHolder for Tip2" )
        var t3 = Tip(1, "Tip3", "PlaceHolder for Tip3" )
        var t4 = Tip(1, "Tip4", "PlaceHolder for Tip4" )

        listOfTips.addAll(arrayListOf(t1,t2,t3,t4))
        listOfTipsStrings.add(t1.tipName.toString())
        listOfTipsStrings.add(t2.tipName.toString())
        listOfTipsStrings.add(t3.tipName.toString())
        listOfTipsStrings.add(t4.tipName.toString())

        adapter = ArrayAdapter(this.baseContext, R.layout.tips_row, R.id.txt_tip_row,
                listOfTipsStrings )
        lst_tips.adapter = adapter

        lst_tips.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this.baseContext, listOfTips[position].tipDescription, Toast.LENGTH_LONG).show()
        }
    }
}


