package com.example.avtoblogfragment.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.avtoblogfragment.R
import com.example.avtoblogfragment.data.AvtoblogItem
import com.example.avtoblogfragment.databinding.FragmentAddBinding
import com.example.avtoblogfragment.databinding.FragmentEditBinding
import com.example.avtoblogfragment.helpers.UriToDrawableConverter
import com.example.avtoblogfragment.interfaces.IFragmentCommunication
import com.example.avtoblogfragment.viewmodels.AvtoblogListviewModel
class AddFragment(private val navigation: IFragmentCommunication) : Fragment(R.layout.fragment_add) {
    private var imgUri: Uri? = null
    private val selImgCode = -1

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    var radio1:RadioButton?=null
    var radio2:RadioButton?=null
    var startPoint = 0
    var endPoind = 0

    private val avtoblogListViewModel: AvtoblogListviewModel by activityViewModels()
    private var selectedItem: AvtoblogItem = AvtoblogItem(null, "", "", "", "", "", "", "", "")

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val selItemId = avtoblogListViewModel.selectedIndex.value!!


        selectedItem = avtoblogListViewModel.getAvtoblogAtPosition(selItemId)

        binding.btnSave.setOnClickListener {

            selectedItem.model = binding.etModelAdd.text.toString()
            selectedItem.brand = binding.etBrandAdd.text.toString()
            selectedItem.year = binding.etYearAdd.text.toString()

            selectedItem.motor = radio1?.text.toString()
            selectedItem.kpp = radio2?.text.toString()

            selectedItem.motorsize = binding.seekBar3.progress.toString()
            selectedItem.power = binding.seekBar2.progress.toString()
            selectedItem.mileage = binding.seekBar.progress.toString()
                  //  avtoblogListViewModel.updateAvtoblogAtPosition(selectedItem,2)
                navigation.listAvto()

        }

        binding.radioGroup2.setOnCheckedChangeListener { _, checkedid ->
            if (checkedid == R.id.rbElAdd) {

                radio1?.text = "электро"
                binding.seekBar3.visibility = View.INVISIBLE
                binding.tvObDvi.visibility = View.INVISIBLE
                binding.sbVolume3.visibility = View.INVISIBLE
                binding.seekBar3.progress = 0
            }
            if (checkedid == R.id.rbBenAdd) {
                radio1?.text = "бензин"
                binding.seekBar3.visibility = View.VISIBLE
                binding.tvObDvi.visibility = View.VISIBLE
                binding.sbVolume3.visibility = View.VISIBLE
            }
            if (checkedid == R.id.rbDizAdd) {
                radio1?.text = "дизель"
                binding.seekBar3.visibility = View.VISIBLE
                binding.tvObDvi.visibility = View.VISIBLE
                binding.sbVolume3.visibility = View.VISIBLE

            }
        }

        binding.radioGroup3.setOnCheckedChangeListener { _, checkedid ->
            if (checkedid == R.id.rbAvtAdd)
                radio2?.text = "автомат"
            if (checkedid == R.id.rbMehAdd)
                radio2?.text = "механика"
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.sbVolume.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    startPoint = seekBar.progress
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    endPoind = seekBar.progress
                }
            }

        })
        binding.seekBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.sbVolume2.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (binding.seekBar2 != null) {
                    startPoint = binding.seekBar2.progress
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (binding.seekBar2 != null) {
                    endPoind = binding.seekBar2.progress
                }
            }

        })

        binding.seekBar3.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.sbVolume3.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (binding.seekBar3 != null) {
                    startPoint = binding.seekBar3.progress
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (binding.seekBar3 != null) {
                    endPoind = binding.seekBar3.progress
                }
            }

        })

        binding.ivAvtoImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, selImgCode)
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == selImgCode) {
            imgUri = data?.data
            binding.ivAvtoImage.setImageURI(imgUri)
            selectedItem.img = UriToDrawableConverter.uriToDrawable(imgUri.toString(), requireContext())

        }
    }
}
