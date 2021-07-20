package com.mnsh.h551yolbelgilari

import Adapters.RvAdapter
import Adapters.RvItemClick
import DB.MyDbHelper
import Kesh.MySharedPreference
import Models.Belgi
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.mnsh.h551yolbelgilari.databinding.FragmentHeartBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HeartFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentHeartBinding
    lateinit var rvAdapter: RvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeartBinding.inflate(LayoutInflater.from(context))


        return binding.root
    }


lateinit var myDbHelper:MyDbHelper
    override fun onResume() {
        super.onResume()
        myDbHelper = MyDbHelper(context)
        val list = myDbHelper.getAllLabel()
        val likeList = ArrayList<Belgi>()
        for (belgi in list) {
            if (belgi.like == 1){
                likeList.add(belgi)
            }
        }
        rvAdapter = RvAdapter(likeList, object : RvItemClick{
            override fun edit(belgi: Belgi) {
                findNavController().navigate(R.id.editFragment, bundleOf("label" to belgi))
            }

            override fun delete(belgi: Belgi) {
                val dialog = AlertDialog.Builder(context)
                dialog.setTitle("Rozimisiz?")
                dialog.setMessage("${belgi.name} o'chirilsinmi?")
                dialog.setPositiveButton("Ha "
                ) { dialog, which ->
                    myDbHelper.deleteLabel(belgi)
                    onResume()
                }

                dialog.setNegativeButton("Yo'q "
                ) { dialog, which ->
                    dialog.cancel()
                }
                dialog.show()
            }

            override fun like(belgi: Belgi) {
                if (belgi.like == 0) {
                    belgi.like = 1
                } else {
                    belgi.like = 0
                }
                myDbHelper.editLabel(belgi)
                onResume()
            }

            override fun itemClick(belgi: Belgi) {
                findNavController().navigate(R.id.showBelgiFragment, bundleOf("keyBelgi" to belgi))
            }
        })
        binding.rvHeart.adapter = rvAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HeartFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}