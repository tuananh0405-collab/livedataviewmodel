package com.example.livedataviewmodel.view;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.livedataviewmodel.R;
import com.example.livedataviewmodel.databinding.FragmentFormBinding;
import com.example.livedataviewmodel.model.Product;
import com.example.livedataviewmodel.viewmodel.ProductViewModel;

public class FormFragment extends Fragment {

    private FragmentFormBinding binding;
    private ProductViewModel viewModel;
    private int selectedImg = R.drawable.ic_default_image;
//    Product product;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFormBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        viewModel.getSelectedProduct().observe(getViewLifecycleOwner(), this::populateForm);
        if (binding.getProduct() == null) {
            binding.setProduct(new Product());
        }
        viewModel.getImageResId().observe(getViewLifecycleOwner(), resId -> {
            if (resId != null) {
                binding.imvProductImg.setImageResource(resId);
//                product.setImageResId(resId);
                selectedImg = resId;
            }
        });

//        product = new Product();

//        binding.setProduct(product);


        binding.btnConfirm.setOnClickListener(view -> {
            if (isValidInput()) {
                Product newProduct = binding.getProduct();
//                newProduct.setCode(Integer.parseInt(binding.edtProductCode.getText().toString()));
//                newProduct.setName(binding.edtProductName.getText().toString());
//                newProduct.setDescription(binding.edtProductDesc.getText().toString());
//                newProduct.setPrice(Float.parseFloat(binding.edtProductPrice.getText().toString()));
                newProduct.setImageResId(selectedImg);

                if (viewModel.getSelectedProduct().getValue() != null) {
                    viewModel.updateProduct(newProduct);
                    viewModel.clearSelectedProduct();
                } else {
                    viewModel.addProduct(newProduct);
                }

                binding.setProduct(new Product());
                selectedImg = R.drawable.ic_default_image;
                binding.imvProductImg.setImageResource(selectedImg);
            }
            binding.edtProductCode.setEnabled(true);

            ViewPager2 viewPager2 = getActivity().findViewById(R.id.view_pager_2);
            viewPager2.setCurrentItem(0, true);
        });


        return binding.getRoot();
    }

    private void populateForm(Product product) {
        if (product != null) {
            binding.setProduct(product);
            selectedImg = product.getImageResId();
            binding.edtProductCode.setEnabled(false);
        } else {
            binding.setProduct(new Product());
            selectedImg = R.drawable.ic_default_image;
            binding.imvProductImg.setImageResource(selectedImg);
        }
    }

    private boolean isValidInput() {
        return !binding.edtProductCode.getText().toString().isEmpty() &&
                !binding.edtProductName.getText().toString().isEmpty() &&
                !binding.edtProductDesc.getText().toString().isEmpty() &&
                !binding.edtProductPrice.getText().toString().isEmpty();
    }
}