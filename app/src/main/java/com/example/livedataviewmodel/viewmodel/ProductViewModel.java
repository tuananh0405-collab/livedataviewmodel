package com.example.livedataviewmodel.viewmodel;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.livedataviewmodel.R;
import com.example.livedataviewmodel.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductViewModel extends ViewModel {
    private MutableLiveData<List<Product>> products = new MutableLiveData<>();
    private MutableLiveData<Product> selectedProduct = new MutableLiveData<>();
    private MutableLiveData<Integer> imageResId = new MutableLiveData<>(R.drawable.baseline_image_24);

    public LiveData<Integer> getImageResId() {
        return imageResId;
    }

    public void showImgDialog(Context context) {
        final String[] imageOptions = {"Bacon", "Chicken", "Ranch", "Beef", "Berry"};
        final int[] imageResources = {R.drawable.bacon_wrapped, R.drawable.bbq_chicken, R.drawable.bbq_ranch, R.drawable.beef_stir_fry, R.drawable.berry_blast};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Select Product Image")
                .setItems(imageOptions, (dialog, which) -> {
                    imageResId.setValue(imageResources[which]);
                });
        builder.show();
    }

    public ProductViewModel() {
        products = new MutableLiveData<>(new ArrayList<>());
    }

    public MutableLiveData<List<Product>> getProducts() {
        return products;
    }

    public MutableLiveData<Product> getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product product) {
        selectedProduct.setValue(product);
    }

    public void clearSelectedProduct() {
        selectedProduct.setValue(null);
    }

    public void addProduct(Product product) {
        List<Product> currentProducts = products.getValue();
        currentProducts.add(0, product);
        products.setValue(currentProducts);
    }

    public void updateProduct(Product updatedProduct) {
        List<Product> currentProducts = products.getValue();

        int idx = currentProducts.indexOf(selectedProduct.getValue());
        if (idx >= 0) {
            currentProducts.set(idx, updatedProduct);
            products.setValue(currentProducts);
        }
    }
}
