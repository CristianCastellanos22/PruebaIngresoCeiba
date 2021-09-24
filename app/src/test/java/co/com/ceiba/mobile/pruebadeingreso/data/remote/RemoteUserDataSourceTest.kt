package co.com.ceiba.mobile.pruebadeingreso.data.remote

import co.com.ceiba.mobile.pruebadeingreso.data.model.*
import co.com.ceiba.mobile.pruebadeingreso.repository.WebService
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RemoteUserDataSourceTest: TestCase() {
    @Mock
    private lateinit var webService: WebService

    @InjectMocks
    private lateinit var remoteUserDataSource: RemoteUsersDataSource

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
            val expect = false
            val returnResponse = createUserResponse()
            Mockito.`when`(webService.getUsers()).thenReturn(returnResponse)
            val result = remoteUserDataSource.getUsers()
            assertEquals(expect, result.isEmpty())
        }
    }

    @Test
    fun `getPost`() {
        runBlocking {
            val userId = 1
            val expect = false
            val returnResponse = createPostResponse()
            Mockito.`when`(webService.getPost(userId)).thenReturn(returnResponse)
            val result = remoteUserDataSource.getPost(userId)
            assertEquals(expect, result.isEmpty())
        }
    }
}