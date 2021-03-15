package com.example.avtoblogfragment.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.avtoblogfragment.R
import com.example.avtoblogfragment.adapter.AvtoListAdapter
import com.example.avtoblogfragment.data.AvtoblogItem
import com.example.avtoblogfragment.databinding.FragmentListBinding
import com.example.avtoblogfragment.interfaces.IFragmentCommunication
import com.example.avtoblogfragment.viewmodels.AvtoblogListviewModel
import java.util.Observer

class ListFragment(val navigation: IFragmentCommunication) : Fragment(R.layout.fragment_list) {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var avtoblogListAdapter: AvtoListAdapter
    private lateinit var fContext: Context

    private val avtoblogListViewModel: AvtoblogListviewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.fContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        avtoblogListAdapter = AvtoListAdapter(avtoblogListViewModel.avtoblogListLiveData.value!!, navigation)

        binding.rvAvtoList.adapter = avtoblogListAdapter
        binding.rvAvtoList.layoutManager = LinearLayoutManager(fContext)

        avtoblogListViewModel.avtoblogListLiveData.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer<MutableList<AvtoblogItem>> {
                avtoblogListAdapter.notifyDataSetChanged()
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }





}
