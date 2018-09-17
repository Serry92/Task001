package android.serry.task001.fragmentViews;

import android.os.Bundle;
import android.serry.task001.R;
import android.serry.task001.adapters.CategoriesAdapter;
import android.serry.task001.models.CategoryModel;
import android.serry.task001.presenters.SubCategoriesPresenterImplementer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class SubCategoriesFragment extends Fragment implements SubCategoriesView {
    private static SubCategoriesFragment instanceSubCategory;
    private List<CategoryModel> subCategories;
    private SubCategoriesPresenterImplementer subCategoriesPresenterImplementer;
    private RecyclerView rvSubCategories;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();

        if (null != bundle) {
            subCategories = bundle.getParcelableArrayList("subs");
        }
        subCategoriesPresenterImplementer = new SubCategoriesPresenterImplementer(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        subCategoriesPresenterImplementer.onCreate(view);
        subCategoriesPresenterImplementer.setValues(subCategories);
        return view;

    }

    public static synchronized SubCategoriesFragment getInstance() {
        if (instanceSubCategory == null) {
            instanceSubCategory = new SubCategoriesFragment();
        }
        return new SubCategoriesFragment();
    }

    @Override
    public void initViews(View view) {
        rvSubCategories = view.findViewById(R.id.rv_data);
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(), 2);
        rvSubCategories.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void setSubsView(List<CategoryModel> subCategories) {
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(getActivity(), subCategories);
        rvSubCategories.setAdapter(categoriesAdapter);
        categoriesAdapter.notifyDataSetChanged();
    }
}
