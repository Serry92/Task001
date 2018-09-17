package android.serry.task001.fragmentViews;

import android.os.Bundle;
import android.serry.task001.R;
import android.serry.task001.adapters.CategoriesAdapter;
import android.serry.task001.listeners.OnCategoryClickListener;
import android.serry.task001.models.CategoryModel;
import android.serry.task001.presenters.CategoriesPresenterImplementer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoriesFragment extends Fragment implements CategoriesView, OnCategoryClickListener {
    private static CategoriesFragment instanceCategory;
    private RecyclerView rvCategories;
    private CategoriesPresenterImplementer categoriesPresenterImplementer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoriesPresenterImplementer = new CategoriesPresenterImplementer(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        categoriesPresenterImplementer.onCreate(view);
        categoriesPresenterImplementer.getCategories(getActivity());
        return view;

    }

    public static synchronized CategoriesFragment getInstance() {
        if (instanceCategory == null) {
            instanceCategory = new CategoriesFragment();
        }
        return new CategoriesFragment();
    }

    @Override
    public void initViews(View view) {
        rvCategories = view.findViewById(R.id.rv_data);
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(), 2);
        rvCategories.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(), Objects.requireNonNull(getActivity()).getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setCategoryList(List<CategoryModel> categoryModelList) {
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(getActivity(), categoryModelList, this);
        rvCategories.setAdapter(categoriesAdapter);
        categoriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCategoryClicked(ArrayList<CategoryModel> subCategories) {
        if (subCategories.size() != 0) {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("subs", subCategories);
            FragmentTransaction ft = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            SubCategoriesFragment subCategoriesFragment = new SubCategoriesFragment();
            subCategoriesFragment.setArguments(bundle);
            ft.replace(R.id.content_frame, subCategoriesFragment);
            ft.addToBackStack("categories");
            ft.commit();
        } else
            Toast.makeText(getActivity(), Objects.requireNonNull(getActivity()).getResources().getString(R.string.no_subs), Toast.LENGTH_SHORT).show();
    }
}
