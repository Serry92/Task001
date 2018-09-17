package android.serry.task001.presenters;

import android.serry.task001.models.CategoryModel;
import android.view.View;

import java.util.List;

public interface SubCategoriesPresenter {

    void onCreate(View view);

    void setValues(List<CategoryModel> subCategories);
}
