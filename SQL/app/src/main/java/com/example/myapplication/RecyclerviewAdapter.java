package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {

    Context context;
    List<PersonBean> listPersonInfo;
    OnUserClickListener listener;

    public RecyclerviewAdapter(Context context, List<PersonBean> listPersonInfo, OnUserClickListener listener) {
        this.context = context;
        this.listPersonInfo = listPersonInfo;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PersonBean person = listPersonInfo.get(position);
        holder.txtName.setText(person.getName());
        holder.txtAge.setText(String.valueOf(person.getAge()));

        holder.imgEdit.setOnClickListener(v -> listener.onUserClick(person, "Edit"));
        holder.imgDelete.setOnClickListener(v -> listener.onUserClick(person, "Delete"));
    }

    @Override
    public int getItemCount() {
        return listPersonInfo.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtAge;
        ImageView imgEdit, imgDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.ctxtName);
            txtAge = itemView.findViewById(R.id.ctxtAge);
            imgEdit = itemView.findViewById(R.id.imgedit);
            imgDelete = itemView.findViewById(R.id.imgdelete);
        }
    }

    public interface OnUserClickListener {
        void onUserClick(PersonBean currentPerson, String action);
    }
}
