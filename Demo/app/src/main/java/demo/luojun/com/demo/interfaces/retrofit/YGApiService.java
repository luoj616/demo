package demo.luojun.com.demo.interfaces.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by luo.j on 2019/6/28.
 */

public interface YGApiService {
   @GET("/v_1.8/version")
   Call<String> getVersion();
}
