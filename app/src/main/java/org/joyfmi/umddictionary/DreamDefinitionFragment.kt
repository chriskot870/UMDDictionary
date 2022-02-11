package org.joyfmi.umddictionary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.joyfmi.umddictionary.databinding.FragmentCategoryListBinding
import org.joyfmi.umddictionary.databinding.FragmentDreamDefinitionBinding

/**
 * A simple [Fragment] subclass.
 * Use the [DreamDefinitionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DreamDefinitionFragment : Fragment() {
    private var _binding: FragmentDreamDefinitionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Retrieve and inflate the layout for this fragment
        _binding = FragmentDreamDefinitionBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    /**
     * Frees the binding object when the Fragment is destroyed.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}