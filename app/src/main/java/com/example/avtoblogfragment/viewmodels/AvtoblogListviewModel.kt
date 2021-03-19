package com.example.avtoblogfragment.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.avtoblogfragment.data.AvtoblogItem

class AvtoblogListviewModel : ViewModel() {
    val avtoblogListLiveData = MutableLiveData<MutableList<AvtoblogItem>>(mutableListOf())

    var selectedIndex = MutableLiveData<Int>(0)

    fun selectItem(index: Int) {
        selectedIndex.value = index
    }

    fun loadAvtoblog(avto: MutableList<AvtoblogItem>) {
        avtoblogListLiveData.value = avto
    }
    fun addAvtoblog(avtoblog: AvtoblogItem) {
        avtoblogListLiveData.value!!.add(avtoblog)
    }


    fun getAvtoblogAtPosition(position: Int) : AvtoblogItem {
        return avtoblogListLiveData.value!![position]
    }

    fun updateAvtoblogAtPosition(updatedItem: AvtoblogItem, position: Int) {
        avtoblogListLiveData.value!!.set(position, updatedItem)
    }
}