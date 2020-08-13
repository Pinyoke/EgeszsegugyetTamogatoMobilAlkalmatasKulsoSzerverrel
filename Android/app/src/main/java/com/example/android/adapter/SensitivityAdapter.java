package com.example.android.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.android.R;
import com.example.android.constant.Constans;
import com.example.android.model.Sensitivity;
import com.example.android.service.UserService;
import com.example.android.ui.ProfilActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SensitivityAdapter extends RecyclerView.Adapter<SensitivityAdapter.SensitivityViewHolder> {

    private List<Sensitivity> sensitivities;
    Retrofit retrofit = new Retrofit.Builder().baseUrl(Constans.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    UserService userService = retrofit.create(UserService.class);
    SharedPreferences sharedPreferences;
    ProfilActivity profilActivity;

    public SensitivityAdapter(List<Sensitivity> sensitivities, SharedPreferences sharedPreferences, ProfilActivity profilActivity) {
        this.sensitivities = sensitivities;
        this.sharedPreferences = sharedPreferences;
        this.profilActivity = profilActivity;
    }

    @NonNull
    @Override
    public SensitivityAdapter.SensitivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.sensitivity_row, parent, false);
        SensitivityAdapter.SensitivityViewHolder viewHolder = new SensitivityViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SensitivityAdapter.SensitivityViewHolder holder, int position) {
        System.out.println(sensitivities.get(position).getAllergen().getName());
        final Sensitivity sensitivity = sensitivities.get(position);
        holder.allergenName.setText(sensitivities.get(position).getAllergen().getName());
        holder.sensitivityType.setText(sensitivity.getMyType());
        holder.desc.setText(sensitivity.getDescription());
        holder.deletButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteSensitivity(sensitivity.getId());

            }


        });
    }

    private void deleteSensitivity(Long id) {
       profilActivity.deleteSensitivity(id);

}


    @Override
    public int getItemCount() {
        return sensitivities.size();
    }

    public static class SensitivityViewHolder extends RecyclerView.ViewHolder {
        public TextView allergenName;
        public TextView sensitivityType;
        public ImageButton deletButton;
        public TextView desc;
        public SensitivityViewHolder(View itemView) {
            super(itemView);
            this.allergenName = (TextView) itemView.findViewById(R.id.main_title);
            this.sensitivityType = (TextView) itemView.findViewById(R.id.row_sub_title);
            this.deletButton = (ImageButton) itemView.findViewById(R.id.deleteSensitivityButton);
            this.desc = (TextView) itemView.findViewById(R.id.description);
        }
    }
}
