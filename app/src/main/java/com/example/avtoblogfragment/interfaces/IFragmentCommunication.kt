package com.example.avtoblogfragment.interfaces

import com.example.avtoblogfragment.data.AvtoblogItem

interface IFragmentCommunication {
    fun createAvto()
    fun updateAvto(index: Int)
    fun listAvto()
    fun onAvtoCreated(Avtoblog: AvtoblogItem?)
}