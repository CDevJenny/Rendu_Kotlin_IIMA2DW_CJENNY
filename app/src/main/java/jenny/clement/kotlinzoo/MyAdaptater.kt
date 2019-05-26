package jenny.clement.kotlinzoo

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jenny.clement.kotlinzoo.model.Post
import jenny.clement.kotlinzoo.model.User
import kotlinx.android.synthetic.main.cell.view.*

class MyAdaptater : RecyclerView.Adapter<MyAdaptater.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.cell, parent, false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    // autre option pour getItemCount, si il y seulement un return
    // override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val item: Post = list[position]
        holder.itemView.cellName.text = item.title
        holder.itemView.cellUser.text = userList.first {it.id == item.userId } .name

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, PostActivity::class.java)
            intent.putExtra("post", item)
            holder.itemView.context.startActivity(intent)
        }
    }

    var list : List<Post> = emptyList()
    var userList : List<User> = emptyList()
    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}