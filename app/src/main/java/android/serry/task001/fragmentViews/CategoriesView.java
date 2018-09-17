package android.serry.task001.fragmentViews;

import android.serry.task001.models.CategoryModel;
import android.view.View;

import java.util.List;

public interface CategoriesView {
    void initViews(View view);

    void showError();

    void setCategoryList(List<CategoryModel> categoryModelList);
}
