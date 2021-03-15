package com.example.avtoblogfragment.helpers

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.avtoblogfragment.R
import com.example.avtoblogfragment.data.AvtoblogItem


object InitHelper {
    fun initAvtoList(context: Context): MutableList<AvtoblogItem> {
        return mutableListOf(
            AvtoblogItem(
                ContextCompat.getDrawable(context, R.drawable.bmw5_2),
                "BMW",
                "535i",
                "2015",
                "бензин",
                "автомат",
                "3000",
                "306",
                "98000"
            ),
            AvtoblogItem(
                ContextCompat.getDrawable(context, R.drawable.mersedes_e_class),
                "Mercedes",
                "e200",
                "2015",
                "дизель",
                "автомат",
                "2000",
                "150",
                "1100"
            ),
            AvtoblogItem(
                ContextCompat.getDrawable(context, R.drawable.audi_a6),
                "Audi",
                "А6",
                "2018",
                "дизель",
                "автомат",
                "2967",
                "286",
                "59000"
            )
        )
    }
}