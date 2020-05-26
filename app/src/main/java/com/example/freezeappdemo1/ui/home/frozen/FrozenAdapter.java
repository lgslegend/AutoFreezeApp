package com.example.freezeappdemo1.ui.home.frozen;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freezeappdemo1.R;
import com.example.freezeappdemo1.backend.entitys.AppsCategory;
import com.example.freezeappdemo1.entity.AppInfo;
import com.example.freezeappdemo1.backend.viewmodel.HomeViewModel;

import java.util.List;

public class FrozenAdapter extends ListAdapter<AppsCategory, FrozenAdapter.MyViewHolder> {

    private HomeViewModel homeViewModel;
    Context context;
    FrozenFragment frozenFragment;

    public FrozenAdapter(Context context, HomeViewModel homeViewModel, FrozenFragment frozenFragment) {
        super(new DiffUtil.ItemCallback<AppsCategory>() {
            @Override
            public boolean areItemsTheSame(@NonNull AppsCategory oldItem, @NonNull AppsCategory newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull AppsCategory oldItem, @NonNull AppsCategory newItem) {
                return oldItem.getCategoryName().equals(newItem.getCategoryName());
            }
        });
        this.context = context;
        this.homeViewModel = homeViewModel;
        this.frozenFragment = frozenFragment;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cell_category, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final AppsCategory item = getItem(position);
        holder.textViewCategoryName.setText(item.getCategoryName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putLong("id", item.getId());
                Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_frozenAppByCategoryFragment, bundle);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new EditCategoryDialog(homeViewModel, getItem(position), frozenFragment, position)
                        .show(frozenFragment.requireActivity().getSupportFragmentManager(), "edit category");
                return true;
            }
        });
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCategoryName;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCategoryName = itemView.findViewById(R.id.tv_category_name);
            imageView = itemView.findViewById(R.id.imageViewFolder);
        }
    }
}
