package me.mudkip.moememos.data.api

import com.skydoves.sandwich.ApiResponse
import me.mudkip.moememos.data.model.Memo
import me.mudkip.moememos.data.model.MemosRowStatus
import me.mudkip.moememos.data.model.MemosVisibility
import me.mudkip.moememos.data.model.User
import retrofit2.http.*

data class MemosOutput<T>(
    val data: T
)

data class SignInInput(
    val email: String,
    val password: String
)

data class CreateMemoInput(
    val content: String,
    val visibility: MemosVisibility? = null
)

data class UpdateMemoOrganizerInput(
    val pinned: Boolean
)

interface MemosApi {
    @POST("/api/auth/signin")
    suspend fun signIn(@Body body: SignInInput): ApiResponse<MemosOutput<User>>

    @POST("/api/auth/logout")
    suspend fun logout(): ApiResponse<Unit>

    @GET("/api/user/me")
    suspend fun me(): ApiResponse<MemosOutput<User>>

    @GET("/api/memo")
    suspend fun listMemo(
        @Query("creatorId") creatorId: Long? = null,
        @Query("rowStatus") rowStatus: MemosRowStatus? = null,
        @Query("visibility") visibility: MemosVisibility? = null
    ): ApiResponse<MemosOutput<List<Memo>>>

    @POST("/api/memo")
    suspend fun createMemo(@Body body: CreateMemoInput): ApiResponse<MemosOutput<Memo>>

    @GET("/api/tag")
    suspend fun getTags(@Query("creatorId") creatorId: Long? = null): ApiResponse<MemosOutput<List<String>>>

    @POST("/api/memo/{id}/organizer")
    suspend fun updateMemoOrganizer(@Path("id") memoId: Long, @Body body: UpdateMemoOrganizerInput): ApiResponse<MemosOutput<Memo>>
}