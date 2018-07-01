package com.manzil.allinonemartadmin.DataManager;

import com.manzil.allinonemartadmin.Model.AdvertiseImageClass;
import com.manzil.allinonemartadmin.Model.ForntImageClass;
import com.manzil.allinonemartadmin.Model.ImageClass;
import com.manzil.allinonemartadmin.Model.ListofConfirmedUser;
import com.manzil.allinonemartadmin.Model.ListofPasal;
import com.manzil.allinonemartadmin.Model.ListofRequest;
import com.manzil.allinonemartadmin.Model.ListofStatus;
import com.manzil.allinonemartadmin.Model.Model_AddPasal;
import com.manzil.allinonemartadmin.Model.ListofCategory;
import com.manzil.allinonemartadmin.Model.Model_DeletePasal;
import com.manzil.allinonemartadmin.Model.Model_UpdatePasal;
import com.manzil.allinonemartadmin.Model.UpdateStatus;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("listofcategory.php")
    Call<List<ListofCategory>> getCategoryList();

    @GET("listofstatus.php")
    Call<List<ListofStatus>> getStatusList();


    @POST("addpasal.php")
    @FormUrlEncoded
    Call<Model_AddPasal> addPasal(@Field("p_no") String PasalNo, @Field("c_id") Integer Categoryid,
                                  @Field("status") Integer Status, @Field("phone") String Phone,
                                  @Field("email") String Email, @Field("price") String Price,
                                  @Field("description") String Description);

    @GET("getPasal.php")
    Call<List<ListofPasal>> getpasalName();


    @POST("imageupload.php")
    @FormUrlEncoded
    Call<ImageClass> uploadImage(@Field("p_no") String pasalNo, @Field("image") String Image);

    @POST("fornt_upload.php")
    @FormUrlEncoded
    Call<ForntImageClass> uploadForntImage(@Field("image") String Image);

    @POST("advertise_upload_image.php")
    @FormUrlEncoded
    Call<AdvertiseImageClass> uploadAdvertiseImage(@Field("image") String Image);


    @POST("deletepasal.php")
    @FormUrlEncoded
    Call<Model_DeletePasal> deletePasal(@Field("p_no") String P_no);

    @POST("update.php")
    @FormUrlEncoded
    Call<Model_UpdatePasal> updatePasal(@Field("p_no") String PasalNo, @Field("c_id") Integer C_id,
                                        @Field("status") Integer Status, @Field("description") String Description,
                                        @Field("price") String Price, @Field("phone") String Phone,
                                        @Field("email") String Email);

    @GET("getuserreq.php")
    Call<List<ListofRequest>> getuserreq();

    @POST("updatestatus.php")
    @FormUrlEncoded
    Call<UpdateStatus> updatestatus(@Field("p_no") String PasalNo);

    @GET("getconfirmeduser.php")
    Call<List<ListofConfirmedUser>> getconfirmeduser();

    @POST("getstatusinfo.php")
    @FormUrlEncoded
    Call<List<ListofPasal>> getstatusinfo(@Field("item_type") String Item_type);

}

