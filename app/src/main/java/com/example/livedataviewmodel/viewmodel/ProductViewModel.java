package com.example.livedataviewmodel.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.livedataviewmodel.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductViewModel extends ViewModel {
    private MutableLiveData<List<Product>> products = new MutableLiveData<>();
    private MutableLiveData<Product> selectedProduct = new MutableLiveData<>();

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
