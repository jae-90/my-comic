package com.jw.marvelcomics.ui.characterlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jw.marvelcomics.R
import com.jw.marvelcomics.repository.api.model.Character
import com.jw.marvelcomics.util.setImage

class CharacterListAdapter(
    private var characters: List<Character> = mutableListOf(),
    private val listener: OnItemClickedListener? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CharacterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_character_item,
                parent,
                false
            ),
            listener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CharacterViewHolder -> {
                holder.bind(characters[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    fun submitList(characters: List<Character>) {
        val diffUtil = CharacterDiffCallback(this.characters, characters)
        val result = DiffUtil.calculateDiff(diffUtil)
        this.characters = characters
        result.dispatchUpdatesTo(this)
    }

    class CharacterViewHolder(
        itemView: View,
        private val listener: OnItemClickedListener?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Character) = with(itemView) {
            itemView.setOnClickListener {
                listener?.onItemSelected(bindingAdapterPosition, item)
            }
            itemView.findViewById<ImageView>(R.id.character_image)
                .setImage(item.thumbnail.getUrl())

            itemView.findViewById<TextView>(R.id.character_name).text = item.name
        }
    }

    class CharacterDiffCallback(
        private val oldCharacters: List<Character>,
        private val newCharacters: List<Character>
    ): DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldCharacters.size
        }

        override fun getNewListSize(): Int {
            return newCharacters.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldCharacters[oldItemPosition] == newCharacters[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldCharacters[oldItemPosition] == newCharacters[newItemPosition]
        }
    }

    interface OnItemClickedListener {
        fun onItemSelected(position: Int, item: Character)
    }
}