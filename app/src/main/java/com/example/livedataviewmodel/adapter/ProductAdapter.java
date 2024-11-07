package com.example.livedataviewmodel.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.livedataviewmodel.databinding.ItemProductBinding;
import com.example.livedataviewmodel.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    List<Product> products;
    OnProductClick onProductClick;

    public interface OnProductClick {
        void onProductClick(Product product);
    }

    public ProductAdapter(List<Product> products, OnProductClick onProductClick) {
        this.products = products;
        this.onProductClick = onProductClick;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductBinding binding = ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductViewHolder(binding, onProductClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bind(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        ItemProductBinding binding;
        OnProductClick onProductClick;

        public ProductViewHolder(@NonNull ItemProductBinding binding, OnProductClick onProductClick) {
            super(binding.getRoot());
            this.binding = binding;
            this.onProductClick = onProductClick;
        }

        public void bind(Product product) {
            binding.setProduct(product);
            binding.getRoot().setOnClickListener(view -> onProductClick.onProductClick(product));
        }
    }
}
