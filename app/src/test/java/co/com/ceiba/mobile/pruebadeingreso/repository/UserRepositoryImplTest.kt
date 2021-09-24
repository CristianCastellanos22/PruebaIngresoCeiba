package co.com.ceiba.mobile.pruebadeingreso.repository

import co.com.ceiba.mobile.pruebadeingreso.data.local.LocalUsersDataSource
import co.com.ceiba.mobile.pruebadeingreso.data.model.*
import co.com.ceiba.mobile.pruebadeingreso.data.remote.RemoteUsersDataSource
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserRepositoryImplTest: TestCase() {
    @Mock
    private lateinit var remoteUsersDataSource: RemoteUsersDataSource

    @Mock
    private lateinit var localUsersDataSource: LocalUsersDataSource

    @InjectMocks
    private lateinit var userRepositoryImpl: UserRepositoryImpl

    private fun createUserResponse(): List<User> = mutableListOf(
        User(
            1,
            "UserTest",
            "UserNameTest",
            "Usertest@mail.com",
            Address(
                "Test1",
                "TestSuite",
                "TestCity",
                "TestZipCode",
                Geo(
                    "TestLat",
                    "TestLng"
                )
            ),
            "1234567890",
            "WebSite.com.co",
            Company(
                "TestCompany",
                "TestCatchPhrase",
                "TestBs"
            )
        )
    )

    private fun createPostResponse(): List<Post> = mutableListOf(
        Post(
            1,
            1,
            "Post",
            "TestBody"
        )
    )

    @Test
    fun `getUsers`() {
        runBlocking {
            val userList = UsersList(
                createUserResponse()
            )
            Mockito.`when`(localUsersDataSource.getUsers()).thenReturn(userList)
            val result = userRepositoryImpl.getUsers()
            assertEquals(createUserResponse(), result.result)
        }
    }

    @Test
    fun `getPost`() {
        runBlocking {
            val id = 1
            val userPost = PostsList(
                createPostResponse()
            )
            Mockito.`when`(localUsersDataSource.getPost(id)).thenReturn(userPost)
            val result = userRepositoryImpl.getPost(id)
            assertEquals(createPostResponse(), result.resultPost)
        }
    }
}