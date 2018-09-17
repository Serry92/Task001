package android.serry.task001.adapters;

import android.content.Context;
import android.serry.task001.R;
import android.serry.task001.listeners.OnCategoryClickListener;
import android.serry.task001.models.CategoryModel;
import android.serry.task001.utilities.Constants;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> {
    private List<CategoryModel> categoryModelList;
    private Context context;
    private OnCategoryClickListener onCategoryListener;
    private Constants constants;

    public CategoriesAdapter(Context context, List<CategoryModel> categoryModelList, OnCategoryClickListener onCategoryClickListener) {
        this.context = context;
        this.categoryModelList = categoryModelList;
        onCategoryListener = onCategoryClickListener;
    }

    public CategoriesAdapter(Context context, List<CategoryModel> subCategories) {
        this.context = context;
        this.categoryModelList = subCategories;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);
        myViewHolder.ivCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCategoryListener.onCategoryClicked(categoryModelList.get(myViewHolder.getAdapterPosition()).getSubCategories());
            }
        });
        constants = new Constants();
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get().load(categoryModelList.get(position).getPhoto()).into(holder.ivCategory);
        if (constants.isAppInEnglish())
            holder.tvTitle.setText(categoryModelList.get(position).getTitleEN());
        else
            holder.tvTitle.setText(categoryModelList.get(position).getTitleAR());
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCategory;
        TextView tvTitle;

        MyViewHolder(View itemView) {
            super(itemView);
            ivCategory = itemView.findViewById(R.id.iv_category);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }
}
