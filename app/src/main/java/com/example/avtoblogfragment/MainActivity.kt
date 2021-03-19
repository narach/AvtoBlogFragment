package com.example.avtoblogfragment

import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.avtoblogfragment.data.AvtoblogItem
import com.example.avtoblogfragment.databinding.ActivityMainBinding
import com.example.avtoblogfragment.fragments.AddFragment
import com.example.avtoblogfragment.fragments.EditFragment
import com.example.avtoblogfragment.fragments.ListFragment
import com.example.avtoblogfragment.helpers.InitHelper
import com.example.avtoblogfragment.interfaces.IFragmentCommunication
import com.example.avtoblogfragment.viewmodels.AvtoblogListviewModel


class MainActivity : AppCompatActivity(), IFragmentCommunication {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fAvtoList: ListFragment
    private lateinit var fAvtoEdit: EditFragment
    private lateinit var fAvtoAdd: AddFragment




    private val avtoblogListviewModel: AvtoblogListviewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val rootView = binding.root
        setContentView(rootView)



        avtoblogListviewModel.loadAvtoblog(InitHelper.initAvtoList(this))

        fAvtoList = ListFragment(this)
        fAvtoEdit = EditFragment(this)
        fAvtoAdd = AddFragment(this)

        binding.bnView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.miList -> setCurrentFragment(fAvtoList)
                R.id.miEdit -> setCurrentFragment(fAvtoEdit)
                R.id.miAdd -> setCurrentFragment(fAvtoAdd)
            }
            true
        }

        setCurrentFragment(fAvtoList)
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
    }

    override fun createAvto() {
        binding.bnView.selectedItemId = R.id.miAdd
                setCurrentFragment(fAvtoAdd)
    }

    override fun updateAvto(index: Int) {
        avtoblogListviewModel.selectItem(index)
        binding.bnView.selectedItemId = R.id.miEdit
        setCurrentFragment(fAvtoEdit)
    }

    override fun listAvto() {
        binding.bnView.selectedItemId = R.id.miEdit
        setCurrentFragment(fAvtoList)
    }

    override fun onAvtoCreated(Avtoblog: AvtoblogItem?) {
    }
}