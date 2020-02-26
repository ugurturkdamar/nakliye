package com.example.nakliyeson;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class yukRecyclerAdapter extends RecyclerView.Adapter<yukRecyclerAdapter.YukHolder> {

    private ArrayList<String> yukAdiList;
    private ArrayList<String> yukCesidiList;
    private ArrayList<String> parcaYukList;
    private ArrayList<String> yonList;
    private ArrayList<String> fiyatList;
    private ArrayList<String> telList;

    private OnYukListener mOnYukListener;

    public yukRecyclerAdapter(ArrayList<String> yukAdiList, ArrayList<String> yukCesidiList, ArrayList<String> parcaYukList, ArrayList<String> yonList,ArrayList<String> fiyatList,ArrayList<String> telList, OnYukListener onYukListener) {
        this.yukAdiList = yukAdiList;
        this.yukCesidiList = yukCesidiList;
        this.parcaYukList = parcaYukList;
        this.yonList = yonList;
        this.fiyatList = fiyatList;
        this.telList = telList;
        this.mOnYukListener = onYukListener;
    }

    @NonNull
    @Override
    public YukHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_row,parent,false);

        return new YukHolder(view,mOnYukListener);
    }

    @Override
    public void onBindViewHolder(@NonNull YukHolder holder, int position) {
        holder.yukAdiText.setText(yukAdiList.get(position));
        holder.yukCesidiText.setText(yukCesidiList.get(position));
        holder.parcaYukText.setText(parcaYukList.get(position));
        holder.yonText.setText(yonList.get(position));
        holder.fiyatText.setText(fiyatList.get(position));
        holder.telText.setText(telList.get(position));
    }

    @Override
    public int getItemCount() {
        return yukAdiList.size();
    }

    class YukHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView yukAdiText;
        TextView yukCesidiText;
        TextView parcaYukText;
        TextView yonText;
        TextView fiyatText;
        TextView telText;

        OnYukListener onYukListener;

        public YukHolder(@NonNull View itemView,OnYukListener onYukListener) {
            super(itemView);

            yukAdiText = itemView.findViewById(R.id.recyclerview_row_yukAdiText);
            yukCesidiText = itemView.findViewById(R.id.recyclerview_row_yukCesidiText);
            parcaYukText = itemView.findViewById(R.id.recyclerview_row_parcaYukseText);
            yonText = itemView.findViewById(R.id.recyclerview_row_yonText);
            fiyatText = itemView.findViewById(R.id.recyclerview_row_fiyatText);
            telText = itemView.findViewById(R.id.recyclerview_row_telText);

            this.onYukListener = onYukListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onYukListener.onYukClick(getAdapterPosition());
        }
    }

    public interface OnYukListener{
        void onYukClick(int posiiton);
    }

}
