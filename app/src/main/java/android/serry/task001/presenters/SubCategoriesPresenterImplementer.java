package android.serry.task001.presenters;

import android.serry.task001.fragmentViews.SubCategoriesView;
import android.serry.task001.models.CategoryModel;
import android.view.View;

import java.util.List;

public class SubCategoriesPresenterImplementer implements SubCategoriesPresenter {
    private final SubCategoriesView subCategoriesView;

    public SubCategoriesPresenterImplementer(SubCategoriesView subCategoriesView) {
        this.subCategoriesView = subCategoriesView;
    }

    @Override
    public void onCreate(View view) {
        subCategoriesView.initViews(view);
    }

    @Override
    public void setValues(List<CategoryModel> subCategories) {
        subCategoriesView.setSubsView(subCategories);
    }
}
