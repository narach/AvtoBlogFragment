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
import androidx.fragment.app.activityViewModels
import com.example.avtoblogfragment.R
import com.example.avtoblogfragment.data.AvtoblogItem
import com.example.avtoblogfragment.databinding.FragmentEditBinding
import com.example.avtoblogfragment.helpers.UriToDrawableConverter
import com.example.avtoblogfragment.interfaces.IFragmentCommunication
import com.example.avtoblogfragment.viewmodels.AvtoblogListviewModel

class EditFragment(private val navigation: IFragmentCommunication) : Fragment(R.layout.fragment_edit) {

    private var imgUri: Uri? = null
    private val selImgCode = -1

    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    var radio1:RadioButton?=null
    var radio2:RadioButton?=null
    var startPoint = 0
    var endPoind = 0


    private val avtoblogListViewModel: AvtoblogListviewModel by activityViewModels()
    private var selectedItem: AvtoblogItem = AvtoblogItem(null, "", "","","","","","","")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selItemId = avtoblogListViewModel.selectedIndex.value!!

                selectedItem = avtoblogListViewModel.getAvtoblogAtPosition(selItemId)
                        //   var radio1=binding.radioGroup2.checkedRadioButtonId


        with(binding) {

            etModelAdd.setText(selectedItem.model)
            etBrandAdd.setText(selectedItem.brand)
            etYearAdd.setText(selectedItem.year)
            radio1?.setText(selectedItem.motor)
            radio2?.setText(selectedItem.kpp)
            seekBar3.setProgress(selectedItem.motorsize.toInt())
            seekBar2.setProgress(selectedItem.power.toInt())
            seekBar.setProgress(selectedItem.mileage.toInt())
            ivAvtoImage.setImageDrawable(selectedItem.img)

            sbVolume3.setText(selectedItem.motorsize)
            sbVolume2.setText(selectedItem.power)
            sbVolume.setText(selectedItem.mileage)


            binding.btnSave.setOnClickListener {



                selectedItem.model = etModelAdd.text.toString()
                selectedItem.brand = etBrandAdd.text.toString()
                selectedItem.year = etYearAdd.text.toString()

                selectedItem.motor = radio1?.text.toString()
                selectedItem.kpp = radio2?.text.toString()

                selectedItem.motorsize = seekBar3.progress.toString()
                selectedItem.power = seekBar2.progress.toString()

                        //avtoblogListViewModel.updateAvtoAtPosition(avtoblog,-1)
                navigation.listAvto()
            }

            radioGroup2.setOnCheckedChangeListener { _, checkedid ->
                if (checkedid==R.id.rbElAdd) {

                    radio1?.text="??????????????"
                    seekBar3.visibility = View.INVISIBLE
                    tvObDvi.visibility = View.INVISIBLE
                    sbVolume3.visibility = View.INVISIBLE
                    seekBar3.progress = 0
                }
                if (checkedid==R.id.rbBenAdd) {
                   radio1?.text="????????????"
                    seekBar3.visibility = View.VISIBLE
                    tvObDvi.visibility = View.VISIBLE
                    sbVolume3.visibility=View.VISIBLE
                }
                if (checkedid==R.id.rbDizAdd) {
                   radio1?.text="????????????"
                    seekBar3.visibility = View.VISIBLE
                    tvObDvi.visibility = View.VISIBLE
                    sbVolume3.visibility=View.VISIBLE

                }
            }

            radioGroup3.setOnCheckedChangeListener { _, checkedid ->
                if (checkedid==R.id.rbAvtAdd)
                    radio2?.text="??????????????"
                if (checkedid==R.id.rbMehAdd)
                    radio2?.text="????????????????"
            }

            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    sbVolume.text = progress.toString()                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    if (seekBar != null)  {
                        startPoint = seekBar.progress
                    }
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    if (seekBar != null)  {
                        endPoind = seekBar.progress
                    }
                }

            })
            seekBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    sbVolume2.text = progress.toString()                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    if (seekBar2 != null)  {
                        startPoint = seekBar2.progress
                    }
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    if (seekBar2 != null)  {
                        endPoind = seekBar2.progress
                    }
                }

            })

            seekBar3.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    sbVolume3.text = progress.toString()                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    if (seekBar3 != null)  {
                        startPoint = seekBar3.progress
                    }
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    if (seekBar3 != null)  {
                        endPoind = seekBar3.progress
                    }
                }

            })

            ivAvtoImage.setOnClickListener {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent, selImgCode)
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == AppCompatActivity.RESULT_OK && requestCode == selImgCode) {
            imgUri = data?.data
            binding.ivAvtoImage.setImageURI(imgUri)
            selectedItem.img = UriToDrawableConverter.uriToDrawable(imgUri.toString(), requireContext())
        }
    }

}