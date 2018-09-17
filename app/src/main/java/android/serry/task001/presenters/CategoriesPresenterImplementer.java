package android.serry.task001.presenters;

import android.content.Context;
import android.serry.task001.fragmentViews.CategoriesView;
import android.serry.task001.listeners.OnCategoryListener;
import android.serry.task001.models.CategoryModel;
import android.view.View;

import java.util.List;

public class CategoriesPresenterImplementer implements CategoriesPresenter, OnCategoryListener {
    private CategoriesView categoriesView;

    public CategoriesPresenterImplementer(CategoriesView categoriesView) {
        this.categoriesView = categoriesView;
    }

    @Override
    public void onCreate(View view) {
        categoriesView.initViews(view);
    }

    @Override
    public void getCategories(Context context) {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.getCategories(context, this);
    }

    @Override
    public void onCategoryListener(List<CategoryModel> categoryModelList) {
        categoriesView.setCategoryList(categoryModelList);
    }

    @Override
    public void onError() {
        categoriesView.showError();
    }
}
