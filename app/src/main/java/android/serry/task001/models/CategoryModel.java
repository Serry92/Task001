package android.serry.task001.models;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.serry.task001.connection.ApiClient;
import android.serry.task001.connection.Apis;
import android.serry.task001.listeners.OnCategoryListener;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryModel implements Parcelable {
    private String Id;
    private String TitleEN;
    private String TitleAR;
    private String Photo;
    private String ProductCount;
    private String HaveModel;
    private ArrayList<CategoryModel> SubCategories;

    public CategoryModel(Parcel in) {
        Id = in.readString();
        TitleEN = in.readString();
        TitleAR = in.readString();
        Photo = in.readString();
        ProductCount = in.readString();
        HaveModel = in.readString();
        SubCategories = in.createTypedArrayList(CategoryModel.CREATOR);
    }

    public static final Creator<CategoryModel> CREATOR = new Creator<CategoryModel>() {
        @Override
        public CategoryModel createFromParcel(Parcel in) {
            return new CategoryModel(in);
        }

        @Override
        public CategoryModel[] newArray(int size) {
            return new CategoryModel[size];
        }
    };

    public CategoryModel() {

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitleEN() {
        return TitleEN;
    }

    public void setTitleEN(String titleEN) {
        TitleEN = titleEN;
    }

    public String getTitleAR() {
        return TitleAR;
    }

    public void setTitleAR(String titleAR) {
        TitleAR = titleAR;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public String getProductCount() {
        return ProductCount;
    }

    public void setProductCount(String productCount) {
        ProductCount = productCount;
    }

    public String getHaveModel() {
        return HaveModel;
    }

    public void setHaveModel(String haveModel) {
        HaveModel = haveModel;
    }

    public ArrayList<CategoryModel> getSubCategories() {
        return SubCategories;
    }

    public void setSubCategories(ArrayList<CategoryModel> subCategories) {
        SubCategories = subCategories;
    }

    public void getCategories(Context context, final OnCategoryListener onCategoryListener) {
        final ProgressDialog pd = ProgressDialog.show(context, null, "Please wait");
        Apis apis = ApiClient.getApiClient().create(Apis.class);
        Call<List<CategoryModel>> responseCall = apis.getAllCategories();
        responseCall.enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<CategoryModel>> call, @NonNull Response<List<CategoryModel>> response) {
                Log.w("response ", new Gson().toJson(response));
                pd.dismiss();
                if (response.body() != null)
                    onCategoryListener.onCategoryListener(response.body());
                else
                    onCategoryListener.onError();
            }

            @Override
            public void onFailure(@NonNull Call<List<CategoryModel>> call, @NonNull Throwable t) {
                pd.dismiss();
            }
        });
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Id);
        parcel.writeString(TitleEN);
        parcel.writeString(TitleAR);
        parcel.writeString(Photo);
        parcel.writeString(ProductCount);
        parcel.writeString(HaveModel);
        parcel.writeTypedList(SubCategories);
    }
}
