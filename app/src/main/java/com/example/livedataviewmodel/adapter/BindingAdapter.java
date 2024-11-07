package com.example.livedataviewmodel.adapter;

import android.widget.EditText;
import android.widget.ImageView;

import androidx.databinding.InverseBindingAdapter;

public class BindingAdapter {
    @androidx.databinding.BindingAdapter("app:imageResId")
    public static void setImageResource(ImageView imageView, int imageResId) {
        imageView.setImageResource(imageResId);
    }
    @androidx.databinding.BindingAdapter("android:text")
    public static void setFloatInText(EditText view, float value) {
        if (value != 0.0f) {
            String text = view.getText().toString();
            if (!text.equals(String.valueOf(value))) {
                view.setText(String.valueOf(value));
            }
        } else {
            view.setText("");
        }
    }


    @InverseBindingAdapter(attribute = "android:text")
    public static float getFloatFromText(EditText view) {
        try {
            return Float.parseFloat(view.getText().toString());
        } catch (NumberFormatException e) {
            return 0f;
        }
    }

    @androidx.databinding.BindingAdapter("android:text")
    public static void setIntInText(EditText view, int value) {
        if (value != 0) {
            String text = view.getText().toString();
            if (!text.equals(String.valueOf(value))) {
                view.setText(String.valueOf(value));
            }
        } else {
            view.setText("");
        }
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static int getIntFromText(EditText view) {
        try {
            return Integer.parseInt(view.getText().toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
