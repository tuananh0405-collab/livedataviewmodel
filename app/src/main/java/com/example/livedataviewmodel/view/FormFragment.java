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
    Product product;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFormBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);
        viewModel.getSelectedProduct().observe(getViewLifecycleOwner(), this::populateForm);

        product = new Product();
        binding.setProduct(product);

        binding.btnSelectImg.setOnClickListener(view -> showImagePicker());

        binding.btnConfirm.setOnClickListener(view -> {
            if (isValidInput()) {
                if (viewModel.getSelectedProduct().getValue() != null) {
                    viewModel.updateProduct(product);
                    viewModel.clearSelectedProduct();
                    binding.setProduct(null);
                    selectedImg = R.drawable.ic_default_image;
                    binding.imvProductImg.setImageResource(selectedImg);
                } else {
                    viewModel.addProduct(product);
                    binding.setProduct(null);
                    selectedImg = R.drawable.ic_default_image;
                    binding.imvProductImg.setImageResource(selectedImg);
                }
            }
            ViewPager2 viewPager2 = getActivity().findViewById(R.id.view_pager_2);
            viewPager2.setCurrentItem(0, true);
        });

        return binding.getRoot();
    }

    private void showImagePicker() {
        final String[] imageOptions = {"Bacon", "Chicken", "Ranch", "Beef", "Berry"};
        final int[] imageResources = {
                R.drawable.bacon_wrapped,
                R.drawable.bbq_chicken,
                R.drawable.bbq_ranch,
                R.drawable.beef_stir_fry,
                R.drawable.berry_blast
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Select Product Image")
                .setItems(imageOptions, (dialog, which) -> {
                    selectedImg = imageResources[which];
                    binding.imvProductImg.setImageResource(selectedImg);
                    product.setImageResId(selectedImg);
                });
        builder.show();
    }

    private void populateForm(Product product) {
        if (product != null) {
            binding.setProduct(product);
            selectedImg = product.getImageResId();
        } else {
            binding.setProduct(null);
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