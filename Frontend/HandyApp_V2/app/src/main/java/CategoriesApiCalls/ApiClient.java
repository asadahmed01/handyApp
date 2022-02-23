package CategoriesApiCalls;

import LoginApiCalls.UserService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit getRetrofit() {
        HttpLoggingInterceptor httploggingInterceptor = new HttpLoggingInterceptor();
        httploggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httploggingInterceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://handymendapi.herokuapp.com/categories/")
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    public static CategoryService getCategoryService(){
        CategoryService categoryService =  getRetrofit().create(CategoryService.class);
        return categoryService;
    }
}
