package br.com.finances.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.finances.R
import br.com.finances.adapter.ItemHomeAdapter
import br.com.finances.database.ApplicationDataBase
import br.com.finances.databinding.FragmentUihomeBinding
import br.com.finances.model.Home
import br.com.finances.utils.TextUtils
import br.com.finances.viewmodel.VMHome
import br.com.finances.viewmodel.factory.VMHomeFactory

class UIHome: Fragment() {

    private var _binding: FragmentUihomeBinding? = null
    private val binding get() = _binding!!

    private var recyclerView: RecyclerView? = null
    private var adapter: ItemHomeAdapter? = null
    private val items: ArrayList<Home> = arrayListOf()

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

        viewModel.user.observe(viewLifecycleOwner) { user ->
            activity?.title = TextUtils.textToolbar(
                resources.getString(R.string.hello),
                user.name
            )
        }

        viewModel.valueExpenses.observe(viewLifecycleOwner) { value ->
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
        recyclerView = binding.recycler
        recyclerView?.let { recycler ->
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = adapter
        }
    }

    private fun addViewOnRecycler(item: Home) {
        items.add(item)
        recyclerView?.let { recycler ->
            adapter = ItemHomeAdapter(items, requireContext())
            recycler.adapter = adapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onObservers()
        onConfigureRecycler()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerView = null
        adapter = null
        items.clear()
        _binding = null
    }

}
