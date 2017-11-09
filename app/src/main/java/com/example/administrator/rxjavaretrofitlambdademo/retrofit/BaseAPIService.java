package com.example.administrator.rxjavaretrofitlambdademo.retrofit;
import com.example.administrator.rxjavaretrofitlambdademo.entity.HzDataClass;
import com.example.administrator.rxjavaretrofitlambdademo.rqentity.rqHzClass;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2017/7/28 0028.
 *
 *
 *
 * 如果请求为post实现，那么最好传递参数时使用@Field、@FieldMap和@FormUrlEncoded。
 * 因为@Query和或QueryMap都是将参数拼接在url后面的，而@Field或@FieldMap传递的参数时放在请求体的。
   使用@Path时，path对应的路径不能包含”/”，否则会将其转化为%2F。在遇到想动态的拼接多节url时，还是使用@Url吧。
 */

public interface BaseAPIService
{
    /**获取合作数据@Body **/
    @POST("ModelWeb/AppModel/GetCooperateBusiness")
    Call<HzDataClass> getCooperateBusiness(@Body rqHzClass entity);
    /**获取合作数据@Query**/
    @POST("ModelWeb/AppModel/GetCooperateBusiness")
    Call<HzDataClass> getCooperateBusiness1(@Query("pageIndex") String pageIndex, @Query("PageSize") String PageSize);
    /**获取合作数据@Query**/
    @POST("ModelWeb/AppModel/GetCooperateBusiness")
    Call<HzDataClass> getCooperateBusiness2(@QueryMap Map<String, String> options);
    /**获取合作数据@FieldMap**/
    @FormUrlEncoded
    @POST("ModelWeb/AppModel/GetCooperateBusiness")
    Call<HzDataClass> getCooperateBusiness3(@FieldMap Map<String, String> options);


}
