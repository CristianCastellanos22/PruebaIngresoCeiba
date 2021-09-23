package co.com.ceiba.mobile.pruebadeingreso.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.core.BaseViewHolder
import co.com.ceiba.mobile.pruebadeingreso.data.model.User
import co.com.ceiba.mobile.pruebadeingreso.databinding.UserListItemBinding
import co.com.ceiba.mobile.pruebadeingreso.view.MainActivity

class UserAdapter(private val userList: MutableList<User>, private val itemClickListener: OnClickListenerUser, private var listTemp: List<User> = userList.toList()): RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = UserListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder) {
            is UserViewHolder -> holder.bind(userList[position])
        }
    }

    override fun getItemCount(): Int = userList.size

    fun userFilter(filterText: String): List<User> {
        if (filterText.isEmpty()) return userList
        userList.clear()
        for (user in listTemp) {
            if (user.name!!.lowercase().contains(filterText.lowercase())) {
                userList.add(user)
            }
        }
        notifyDataSetChanged()
        return userList
    }

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