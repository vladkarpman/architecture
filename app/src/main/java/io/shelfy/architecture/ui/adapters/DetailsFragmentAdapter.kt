package io.shelfy.architecture.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import io.shelfy.architecture.ui.DetailsFragment
import io.shelfy.architecture.domain.model.Movie

class DetailsFragmentAdapter(
    fragmentManager: FragmentManager,
    private val movies: List<Movie>
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return DetailsFragment.newInstance(movies[position])
    }

    override fun getCount(): Int {
        return movies.size
    }
}
