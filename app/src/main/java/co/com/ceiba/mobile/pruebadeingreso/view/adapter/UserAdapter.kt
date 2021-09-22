package co.com.ceiba.mobile.pruebadeingreso.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.core.BaseViewHolder
import co.com.ceiba.mobile.pruebadeingreso.data.model.User
import co.com.ceiba.mobile.pruebadeingreso.databinding.UserListItemBinding

class UserAdapter(private val userList: List<User>, private val itemClickListener: OnClickListenerUser): RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = UserListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = UserViewHolder(itemBinding)

        itemBinding.root.setOnClickListener {
            val position =
                holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                    ?: return@setOnClickListener
            itemClickListener.onUserClick(userList[position])
        }
        return  holder;
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder) {
            is UserViewHolder -> holder.bind(userList[position])
        }
    }

    override fun getItemCount(): Int = userList.size

    private inner class UserViewHolder(val binding: UserListItemBinding):
            BaseViewHolder<User>(binding.root) {
        override fun bind(item: User) {
            binding.name.text = item.name
            binding.phone.text = item.phone
            binding.email.text = item.email
            binding.btnViewPost.setOnClickListener {
                itemClickListener.onUserClick(item)
            }
        }
    }

}