package android.serry.task001.connection;

import android.serry.task001.models.CategoryModel;
import android.serry.task001.utilities.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Apis {
    @GET(Constants.GET_CATEGORIES)
    Call<List<CategoryModel>> getAllCategories();
}
