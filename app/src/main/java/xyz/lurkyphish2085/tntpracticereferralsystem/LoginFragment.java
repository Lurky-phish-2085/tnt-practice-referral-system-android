package xyz.lurkyphish2085.tntpracticereferralsystem;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class LoginFragment extends Fragment implements View.OnClickListener {

    NavController navController;
    Button loginBtn, gotoRegister;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        gotoRegister = view.findViewById(R.id.goto_register_btn);
        gotoRegister.setOnClickListener(this);
        loginBtn = view.findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == loginBtn.getId()) {
            // ToDo: Logic for validating user before moving to UserSession Activity
            Intent intent = new Intent(getActivity(), UserSessionActivity.class);
            startActivity(intent);
        }
        if (id == gotoRegister.getId()) {
            navController.navigate(R.id.action_loginFragment_to_registerFragment);
        }
    }
}