package com.example.android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.R;
import com.example.android.model.Offer;

import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.OfferViewHolder> {

    private List<Offer> offers;

    public OfferAdapter(List<Offer> offers) {
        this.offers = offers;
    }

    @NonNull
    @Override
    public OfferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.offer_row, parent, false);
        OfferAdapter.OfferViewHolder viewHolder = new OfferViewHolder(listItem);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull OfferViewHolder holder, int position) {
        final Offer offer = offers.get(position);
        holder.sellerNameView.setText(offer.getSellerName());
        holder.offerPriceView.setText(offer.getPrice()+" Ft");
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"LOL",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return offers.size();

    }

    public static class OfferViewHolder extends RecyclerView.ViewHolder {
        public TextView sellerNameView;
        public TextView offerPriceView;
        public LinearLayout linearLayout;
        public OfferViewHolder(View itemView) {
            super(itemView);
            this.sellerNameView = (TextView) itemView.findViewById(R.id.sellerName);
            this.offerPriceView = (TextView) itemView.findViewById(R.id.offerPrice);
            this.linearLayout = (LinearLayout) itemView.findViewById(R.id.offerRowMainLayout);
        }
    }
}
