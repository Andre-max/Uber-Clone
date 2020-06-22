package com.example.startapp

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.model.Place
import kotlinx.android.synthetic.main.activity_places.*
import kotlinx.android.synthetic.main.place_view_row.view.*


class PlacesActivity : AppCompatActivity() {

    var listOfPlaces: ArrayList<Place>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places)



        var place1 = Place(1,"Paris","Paris is the Capital of France asdasd asd asd dqwdwq da sd " +
                "asdqwd a  qwnjoqwnflaskfnlaskf  sd awdwf askfmalkfma;mf;asf;lasf;"
                , R.drawable.place_1)
        var place2 = Place(2,"Spain","Spain has a mediterranean country", R.drawable.place_2)
        var place3 = Place(3,"Turkey","Turkey is a middle eastern country", R.drawable.place_3)
        var place4 = Place(4,"Italy","Italy is in Europe", R.drawable.place_4)
        var place5 = Place(5,"Rio De Janeiro","Rio De Janeiro is in south America", R.drawable
                .place_5)
        var place6 = Place(6,"Jerusalem","Jerusalem is the capital of State of Palestine", R.drawable
                .place_6)

        listOfPlaces?.add(place1)
        listOfPlaces?.add(place2)
        listOfPlaces?.add(place3)
        listOfPlaces?.add(place4)
        listOfPlaces?.add(place5)
        listOfPlaces?.add(place6)

        lst_places.adapter = PlacesAdater(this)
        lst_places.setOnItemClickListener{ adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->
            Toast.makeText(this, this.listOfPlaces!![i].placeDescription, Toast.LENGTH_LONG).show()

        }
    }

    // POJO   /// POKO : Is a Pure kotlin class
    internal class ViewHolder {
        var img: ImageView? = null
        var title: TextView? = null
        var description: TextView? = null
    }


    inner class PlacesAdater(context: Context) : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var convertView = convertView
            var holder: ViewHolder? = ViewHolder()

            if(convertView == null) {
                convertView = layoutInflater.inflate(R.layout.place_view_row, null)
            }

            holder?.img = convertView!!.img_place as ImageView
            holder?.description = convertView!!.tv_place_Description as TextView
            holder?.title = convertView!!.tv_place_title as TextView

            if (holder != null) {
                listOfPlaces?.get(position)?.placeImg?.let { holder.img?.setBackgroundResource(it) }
                listOfPlaces?.get(position)?.placeName?.let { holder.title?.text = it }
                listOfPlaces?.get(position)?.placeDescription?.let { holder.description?.text = it }
            }

            return convertView
        }

        override fun getItem(position: Int): Any {
          return position
        }

        override fun getItemId(position: Int): Long {
           return listOfPlaces?.size!!.toLong()
        }

        override fun getCount(): Int {
            return listOfPlaces!!.size
        }

    }
}
