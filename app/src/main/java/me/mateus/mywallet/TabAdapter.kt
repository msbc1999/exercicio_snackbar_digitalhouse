package me.mateus.mywallet

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class TabAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val fragments = mutableListOf<TitledFragment>()


    override fun getItem(position: Int): Fragment {
        return fragments.get(position).fragment
    }

    override fun getCount(): Int {
        return fragments.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragments.add(TitledFragment(title, fragment))
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragments.get(position).title
    }

    private data class TitledFragment(val title: String, val fragment: Fragment)

}