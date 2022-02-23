package LoginApiCalls;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {

    @GET("customers/getAll")
    //Call<List <LoginResponse>> userLogin( UserLogin loginRequest);
    Call<List <LoginResponse>> userLogin( );
}
