package co.com.ceiba.mobile.pruebadeingreso.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: Int = -1,
    val name: String = "",
    val username: String = "",
    val email:String = "",
    val address: Address?,
    val phone: String = "",
    val website: String = "",
    val company: Company?,
): Parcelable

data class UsersList(val result: List<User> = listOf())

@Parcelize
data class Address(
    val street: String = "",
    val suite: String = "",
    val city: String = "",
    val zipcode: String = "",
    val geo: Geo,
): Parcelable

@Parcelize
data class Geo(
    val lat: String = "",
    val lng: String = "",
): Parcelable

@Parcelize
data class Company(
    val name: String = "",
    val catchPhrase: String = "",
    val bs: String = "",
): Parcelable

@Entity
data class UserEntity(
    @PrimaryKey
    val id: Int = -1,
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "username")
    val userName: String = "",
    @ColumnInfo(name = "email")
    val email:String = "",
    //@ColumnInfo(name = "address")
    //val address: Address?,
    @ColumnInfo(name = "phone")
    val phone: String = "",
    @ColumnInfo(name = "website")
    val website: String = "",
    //@ColumnInfo(name = "company")
    //val company: Company?,
)

fun List<UserEntity>.toUserList(): UsersList {
    val resultList = mutableListOf<User>()
    this.forEach { userEntity ->
        resultList.add(userEntity.toUser())
    }
    return UsersList(resultList)
}

fun UserEntity.toUser(): User = User(
    this.id,
    this.name,
    this.userName,
    this.email,
    //this.address,
    null,
    this.phone,
    this.website,
    null
    //this.company
)

fun User.toUserEntity(): UserEntity = UserEntity(
    this.id,
    this.name,
    this.username,
    this.email,
    //(this.address.takeIf { it != null } ?:"") as Address,
    this.phone,
    this.website,
    //(this.company.takeIf { it != null } ?:"") as Company,
)
