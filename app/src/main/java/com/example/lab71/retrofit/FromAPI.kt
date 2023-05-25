import com.example.lab71.retrofit.GetFacts
import retrofit2.Call
import retrofit2.http.GET

interface FromAPI {
    @GET("activity")
    fun getFact(): Call<GetFacts>
}
