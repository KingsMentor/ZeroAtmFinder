package xyz.belvi.zerofinder.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.atm_entry.view.*
import xyz.belvi.data.remote.model.retroObj.ResponseData
import xyz.belvi.zerofinder.R

open class AtmAdapter(
    private val results: MutableList<ResponseData.SearchResult>,
    private val itemClicked: (atmclicked: ResponseData.SearchResult) -> Unit
) :
    RecyclerView.Adapter<AtmAdapter.AtmHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AtmHolder {
        return AtmHolder(LayoutInflater.from(parent.context).inflate(R.layout.atm_entry, parent, false))
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun onBindViewHolder(holder: AtmHolder, position: Int) {
        holder.bind(results[position])
        holder.itemView.setOnClickListener {
            itemClicked(results[position])
        }
    }

    fun refreshList(results: MutableList<ResponseData.SearchResult>) {
        this.results.clear()
        this.results.addAll(results)
        notifyDataSetChanged()
    }

    inner class AtmHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(atmResult: ResponseData.SearchResult) {
            itemView.entry_address.text = atmResult.name
        }

    }
}