package com.example.mareu.utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Utils extends AppCompatActivity {
    public static void attachNewFragment(Fragment newFragment, int container){
        FragmentManager fragmentManager =  getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(container, newFragment);
        fragmentTransaction.commit();
    }
}
