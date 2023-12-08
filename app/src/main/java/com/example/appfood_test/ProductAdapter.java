package com.example.appfood_test;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Product> productList;

    public ProductAdapter(Context context, ArrayList<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        holder.nameFood.setText(productList.get(position).getName());
        holder.ratingFood.setText(productList.get(position).getRating());
        holder.food_price.setText(productList.get(position).getPrice());
        holder.img_food.setImageResource(productList.get(position).getImgFood());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProductInfo.class);
                intent.putExtra("Food", productList.get(position).getName());
                intent.putExtra("Description", productList.get(position).getDescription());
                intent.putExtra("Rating", productList.get(position).getRating());
                intent.putExtra("Price", productList.get(position).getPrice());
                intent.putExtra("Image", productList.get(position).getImgFood());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void filteredList(List<Product> filteredList) {
        productList = (ArrayList<Product>) filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

    TextView nameFood, ratingFood, description, food_price;
    ImageView img_food;
    CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameFood = itemView.findViewById(R.id.txt_foodNameCard);
            ratingFood = itemView.findViewById(R.id.txt_rating);
            food_price = itemView.findViewById(R.id.price);
            description = itemView.findViewById(R.id.txt_desc);
            img_food = itemView.findViewById(R.id.img_food);

            cardView = itemView.findViewById(R.id.cardView_product);
        }
    }
}
