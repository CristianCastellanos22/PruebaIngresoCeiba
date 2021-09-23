package co.com.ceiba.mobile.pruebadeingreso.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Post(
    val id: Int = -1,
    val userId: Int = -1,
    val title: String = "",
    val body: String = "",
)

@Entity
data class PostEntity(
    @PrimaryKey
    val id: Int = -1,
    @ColumnInfo(name = "userId")
    val userId: Int = -1,
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "body")
    val body: String = ""
)

data class PostsList(val resultPost: List<Post> = listOf())

fun List<PostEntity>.toPostList(): PostsList {
    val resultPost = mutableListOf<Post>()
    this.forEach { postEntity ->
        resultPost.add(postEntity.toPost())
    }
    return PostsList(resultPost)
}

fun PostEntity.toPost(): Post = Post(
    this.id,
    this.userId,
    this.title,
    this.body
)

fun Post.toPostEntity(): PostEntity = PostEntity(
    this.id,
    this.userId,
    this.title,
    this.body
)