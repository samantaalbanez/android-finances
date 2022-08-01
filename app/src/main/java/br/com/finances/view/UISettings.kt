package br.com.finances.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.finances.database.ApplicationDataBase
import br.com.finances.databinding.FragmentUisettingsBinding
import br.com.finances.view.base.UIFragmentBase
import br.com.finances.viewmodel.VMSettings
import br.com.finances.viewmodel.factory.VMSettingsFactory

class UISettings: UIFragmentBase() {

    private var _binding: FragmentUisettingsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: VMSettings by viewModels {
        VMSettingsFactory((requireActivity().application as ApplicationDataBase).repositorySettings)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUisettingsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onObservers() {
        viewModel.settings.observe(viewLifecycleOwner) { setting ->
            setting?.let {
                binding.edSalary.setText(it.salary.toString())
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
            viewModel.save(binding.edSalary.text.toString())
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