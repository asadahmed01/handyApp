package CategoriesApiCalls;

import java.util.List;

import LoginApiCalls.LoginResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryService {

    @GET("getAll")
    Call<List<CategoriesResponse>> categoryList( );
}
