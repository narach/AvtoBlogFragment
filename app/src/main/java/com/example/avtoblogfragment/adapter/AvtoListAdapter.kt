package com.example.avtoblogfragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.avtoblogfragment.data.AvtoblogItem
import com.example.avtoblogfragment.databinding.ItemAvtoblogBinding
import com.example.avtoblogfragment.interfaces.IFragmentCommunication

class AvtoListAdapter(
    private var avtoList: List<AvtoblogItem>,
    private val navigation: IFragmentCommunication
) :
    RecyclerView.Adapter<AvtoListAdapter.AvtoViewHolder>()  {
    inner class AvtoViewHolder(private val itemAvtoblogBinding: ItemAvtoblogBinding)
        : RecyclerView.ViewHolder(itemAvtoblogBinding.root) {

        fun bind(avtoblogItem: AvtoblogItem) {
            itemAvtoblogBinding.ivAvto.setImageDrawable(avtoblogItem.img)
            itemAvtoblogBinding.tvBrand.text = avtoblogItem.brand
            itemAvtoblogBinding.tvModel.text = avtoblogItem.model
            itemAvtoblogBinding.tvYear.text = avtoblogItem.year
            itemAvtoblogBinding.tvMotorsize.text = avtoblogItem.motorsize
            itemAvtoblogBinding.tvMotor.text = avtoblogItem.motor
            itemAvtoblogBinding.tvKpp.text = avtoblogItem.kpp
            itemAvtoblogBinding.tvMileage.text = avtoblogItem.mileage
            itemAvtoblogBinding.tvPower.text = avtoblogItem.power
            itemAvtoblogBinding.tvKpp.text = avtoblogItem.kpp
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvtoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemAvtoblogBinding = ItemAvtoblogBinding.inflate(layoutInflater, parent, false)
        return AvtoViewHolder(itemAvtoblogBinding)
    }

    override fun getItemCount(): Int {
        return avtoList.size
    }

    override fun onBindViewHolder(holder: AvtoViewHolder, position: Int) {
        holder.bind(avtoList[position])
        holder.itemView.setOnClickListener {
            navigation.updateAvto(position)
        }
    }
}