package net.luckyvalenok.myapplication.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.luckyvalenok.myapplication.R
import net.luckyvalenok.myapplication.domain.data.CardType

class CardAdapter : ListAdapter<CardType, CardAdapter.CardViewHolder>(CardDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder =
        CardViewHolder(
            LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        )

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is CardType.WithImage -> R.layout.list_item_large
            is CardType.WithImageAndBackground -> R.layout.list_item_large
            is CardType.WithImageAndCircle -> R.layout.list_item_medium
            is CardType.WithTitleAndSubtitle -> R.layout.list_item_small
            else -> throw IllegalArgumentException("invalid card")
        }
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val header by lazy { itemView.findViewById<TextView>(R.id.card_header) }
        private val subhead by lazy { itemView.findViewById<TextView>(R.id.card_subheader) }
        private val image by lazy { itemView.findViewById<ImageView?>(R.id.card_image) }
        private val textInfo by lazy { itemView.findViewById<LinearLayout?>(R.id.text_info) }

        fun bind(card: CardType) {
            header.text = card.title
            subhead.text = card.subtitle
            try {
                when (card) {
                    is CardType.WithImage -> {
                        Picasso.get().load(card.image)
                            .error(R.drawable.broken_image)
                            .into(image)
                        textInfo.setBackgroundColor(Color.TRANSPARENT)
                    }

                    is CardType.WithImageAndBackground -> {
                        Picasso.get().load(card.image)
                            .error(R.drawable.broken_image)
                            .into(image)
                        textInfo.setBackgroundColor(Color.parseColor(card.background))
                    }

                    is CardType.WithImageAndCircle -> {
                        Picasso.get().load(card.image)
                            .error(R.drawable.broken_image)
                            .into(image)
                    }

                    else -> {}
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    class CardDiffUtil : DiffUtil.ItemCallback<CardType>() {
        override fun areItemsTheSame(oldItem: CardType, newItem: CardType): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CardType, newItem: CardType): Boolean {
            return oldItem == newItem
        }
    }
}