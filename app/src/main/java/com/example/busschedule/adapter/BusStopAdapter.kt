package com.example.busschedule.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.busschedule.database.schedule.Schedule
import com.example.busschedule.databinding.BusStopItemBinding
import java.text.SimpleDateFormat
import java.util.*

/*
* バス停名と時刻のアダプター
* DiffCallback：変更があった箇所のみを更新するためのオブジェクト
* */
class BusStopAdapter(private val onItemClicked: (Schedule) -> Unit) :
    ListAdapter<Schedule, BusStopAdapter.BusStopViewHolder>(DiffCallback) {

    /*
    * ビューホルダーを生成
    * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusStopViewHolder {
        val viewHolder = BusStopViewHolder(
            BusStopItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    /*
    *ビューホルダーに表示用データを設定（ここでは、ビューホルダー内に定義されたbind関数を呼び出し）
    * */
    override fun onBindViewHolder(holder: BusStopViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    /*
    * ビューホルダー（バス停名と時刻のデータとレイアウトを対応させる）
    * */
    class BusStopViewHolder(private var binding: BusStopItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(schedule: Schedule) {
            binding.stopNameTextView.text = schedule.stopName
            binding.arrivalTimeTextView.text =
                SimpleDateFormat("h:mm a").format(Date(schedule.arrivalTime.toLong() * 1000))
        }
    }

    /*
    * ListAdapterが挿入、更新、削除された項目だけをUI更新するためのオブジェクト
    * */
    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Schedule>() {

            // idが同じか
            override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem.id == newItem.id
            }

            // Scheduleオブジェクトが同じか
            override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem == newItem
            }
        }
    }
}