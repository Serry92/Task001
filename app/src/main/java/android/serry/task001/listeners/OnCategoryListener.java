package android.serry.task001.listeners;

import android.serry.task001.models.CategoryModel;

import java.util.List;

public interface OnCategoryListener {
    void onCategoryListener(List<CategoryModel> categoryModelList);

    void onError();
}
