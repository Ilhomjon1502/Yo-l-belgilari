package Adapters

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import com.mnsh.h551yolbelgilari.HeartFragment
import com.mnsh.h551yolbelgilari.InfoFragment
import com.mnsh.h551yolbelgilari.MyHomeFragmet

class CategoryAdapter(fragmentActivity: FragmentActivity?)
    :FragmentStateAdapter(fragmentActivity!!){
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        Log.d("onPosition", "$position")
        return when(position){
            0 -> MyHomeFragmet()
            1 -> HeartFragment()
            2 -> InfoFragment()
            else -> MyHomeFragmet()
        }
    }

    override fun onBindViewHolder(
        holder: FragmentViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
        Log.d("onBindPosition", "$position")
    }

//    override fun getCount(): Int = 3
//
//    override fun getItem(position: Int): Fragment {
//        println("adapter $position")
//        return when(position){
//            0 -> MyHomeFragmet.newInstance("", "")
//            1 -> HeartFragment.newInstance("", "")
//            2 -> InfoFragment.newInstance("", "")
//            else -> MyHomeFragmet.newInstance("", "")
//        }
//    }
}