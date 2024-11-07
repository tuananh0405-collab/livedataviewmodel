package com.example.livedataviewmodel.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.livedataviewmodel.R;
import com.example.livedataviewmodel.adapter.ProductAdapter;
import com.example.livedataviewmodel.databinding.FragmentListBinding;
import com.example.livedataviewmodel.model.Product;
import com.example.livedataviewmodel.viewmodel.ProductViewModel;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    private FragmentListBinding binding;
    private ProductViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListBinding.inflate(inflater, container, false);

        binding.rcvProduct.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false)
        );
        ProductAdapter adapter = new ProductAdapter(new ArrayList<>(), this::onProductClick);
        binding.rcvProduct.setAdapter(adapter);

        viewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);
        viewModel.getProducts().observe(getViewLifecycleOwner(), adapter::setProducts);

        return binding.getRoot();
    }

    private void onProductClick(Product product) {
        viewModel.setSelectedProduct(product);
        ViewPager2 viewPager2 = getActivity().findViewById(R.id.view_pager_2);
        viewPager2.setCurrentItem(1,true);
    }
}