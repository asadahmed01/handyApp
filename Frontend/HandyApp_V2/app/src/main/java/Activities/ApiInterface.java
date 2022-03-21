package Activities;

import java.util.List;

import Activities.models.ListModel;
import Activities.models.Review;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    String BASE_URL = "https://handymendapi.herokuapp.com/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    @GET("users/data/{id}")
    Call<List<ListModel>> getdetails(@Path("id") int id);


    @FormUrlEncoded
    @POST("/reviews/addreview")
    Call<POST> newreview(
            @Field("review") String review,
            @Field("postid") String postid,
            @Field("rate") String rate,
            @Field("uid") String uid
    );

    @FormUrlEncoded
    @POST("/users/newdata")
    Call<POST> newData(
            @Field("category") String category,
            @Field("price") String price,
            @Field("skills") String skills,
            @Field("description") String description,
            @Field("uid") String uid,
            @Field("kay") String kay,
            @Field("date") String date,
            @Field("time") String time
    );


    @GET("/users/categories/{categoryname}")
    Call<List<ListModel>> getCategory(@Path("categoryname") String category);


    @GET("/reviews/reviews/{postid}")
    Call<List<Review>> getReviews(@Path("postid") String postid);

}