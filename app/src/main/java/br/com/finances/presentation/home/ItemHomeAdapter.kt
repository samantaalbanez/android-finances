package br.com.finances.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.finances.presentation.home.ItemHomeAdapter.HomeViewHolder.Companion.create
import br.com.finances.databinding.ItemHomeBinding
import br.com.finances.domain.model.Home

class ItemHomeAdapter(
    val context: Context
) : ListAdapter<Home, ItemHomeAdapter.HomeViewHolder>(WORDS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return create(parent)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, context)
    }

    class HomeViewHolder(private val binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(home: Home, context: Context) {
            binding.tvText.text = home.text
            binding.icon.setImageDrawable(context.getDrawable(home.image))
        }

        companion object {
            fun create(parent: ViewGroup): HomeViewHolder {
                val view = LayoutInflater.from(parent.context)
                val itemBinding: ItemHomeBinding =
                    ItemHomeBinding.inflate(view, parent, false)
                return HomeViewHolder(itemBinding)
            }
        }
    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<Home>() {
            override fun areItemsTheSame(oldItem: Home, newItem: Home): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Home, newItem: Home): Boolean {
                return oldItem.text == newItem.text
            }
        }
    }
}
