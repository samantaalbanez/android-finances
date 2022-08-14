package br.com.finances.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.finances.R
import br.com.finances.data.database.ApplicationDataBase
import br.com.finances.databinding.FragmentUihomeBinding
import br.com.finances.domain.model.Home
import br.com.finances.domain.utils.TextUtils

class UIHome: Fragment() {

    private var _binding: FragmentUihomeBinding? = null
    private val binding get() = _binding!!

    private var items: ArrayList<Home> = arrayListOf()
    private val homeAdapter: ItemHomeAdapter by lazy {
        ItemHomeAdapter(requireContext())
    }

    private val viewModel: VMHome by viewModels {
        VMHomeFactory(
            (requireActivity().application as ApplicationDataBase).repositoryUser,
            (requireActivity().application as ApplicationDataBase).repositorySettings
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUihomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun onObservers() {
        viewModel.selic.observe(viewLifecycleOwner) { selic ->
            addViewOnRecycler(
                Home(
                    text = TextUtils.textSelic(
                        resources.getString(R.string.home_selic_today),
                        selic.value
                    ),
                    image = R.drawable.ic_money
                )
            )
        }

        viewModel.settings.observe(viewLifecycleOwner) { settings ->
            settings?.let {
                addViewOnRecycler(
                    Home(
                        text = TextUtils.textSalary(
                            resources.getString(R.string.home_salary_total),
                            settings.salary
                        ),
                        image = R.drawable.ic_money
                    )
                )
                viewModel.calculateValues()
            }
        }

        viewModel.user.observe(viewLifecycleOwner) { user ->
            user?.let {
                activity?.title = TextUtils.textToolbar(
                    resources.getString(R.string.hello),
                    user.name
                )
            }
        }

        viewModel.valueExpenses.observe(viewLifecycleOwner) { value ->
            value?.let {
                addViewOnRecycler(
                    Home(
                        text = TextUtils.textSuggestion(
                            resources.getString(R.string.home_suggestion_expenses),
                            value
                        ),
                        image = R.drawable.ic_money
                    )
                )
            }
        }

        viewModel.valueInvestments.observe(viewLifecycleOwner) { value ->
            addViewOnRecycler(
                Home(
                    text = TextUtils.textSuggestion(
                        resources.getString(R.string.home_suggestion_investments),
                        value
                    ),
                    image = R.drawable.ic_money
                )
            )
        }
    }

    private fun onConfigureRecycler() {
        with (binding.recycler) {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun addViewOnRecycler(item: Home) {
        items.add(item)
        homeAdapter.submitList(items)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clearView()
        onObservers()
        onConfigureRecycler()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        clearView()
    }

    private fun clearView() {
        items = arrayListOf()
        items.clear()
    }
}
