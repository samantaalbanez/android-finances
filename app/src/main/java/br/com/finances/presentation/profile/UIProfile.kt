package br.com.finances.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.finances.data.database.ApplicationDataBase
import br.com.finances.databinding.FragmentUiprofileBinding
import br.com.finances.presentation.base.UIFragmentBase

class UIProfile : UIFragmentBase() {

    private var _binding: FragmentUiprofileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: VMProfile by viewModels {
        VMProfileFactory((requireActivity().application as ApplicationDataBase).repositoryUser)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUiprofileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onObservers() {
        viewModel.user.observe(viewLifecycleOwner) { user ->
            user?.let {
                binding.edName.setText(it.name)
            }
        }
        viewModel.status.observe(viewLifecycleOwner) { status ->
            status?.let {
                configureStatus(status)
            }
        }
    }

    override fun onActions() {
        binding.btSave.setOnClickListener {
            viewModel.save(binding.edName.text.toString())
        }

        binding.ivClose.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}