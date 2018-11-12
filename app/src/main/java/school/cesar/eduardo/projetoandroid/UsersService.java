package school.cesar.eduardo.projetoandroid;

import java.util.List;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import school.cesar.eduardo.projetoandroid.model.Users;

public interface UsersService {
    @GET("/users")
    Call<List<Users>> getPeople();

    @GET("/users/{id}")
    Call<Users> getUserId(@Path("id") String id);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}

