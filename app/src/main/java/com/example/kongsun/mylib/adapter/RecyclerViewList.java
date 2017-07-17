package com.example.kongsun.mylib.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kongsun.mylib.R;

import java.util.List;

/**
 * Created by kongsun on 7/14/17.
 */

public class RecyclerViewList extends RecyclerView.Adapter<RecyclerViewList.FavoriteViewHolder> {
    private List<Book> books;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    public void RecyclerViewList(List<Book> bookList, OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.books = bookList;
        this.onRecyclerViewItemClickListener= onRecyclerViewItemClickListener;
    }


    @Override
    public FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorite_fragment, parent, false);
        return new FavoriteViewHolder(view);
    }


    @Override
    public void onBindViewHolder(FavoriteViewHolder holder, int position) {
        Book book = books.get(position);
        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthor());
        holder.rate.setText(book.getRate());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView author;
        private TextView rate;
        public FavoriteViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRecyclerViewItemClickListener.onRecyclerViewItemClickListener(getLayoutPosition());
                }
            });
            title = (TextView) itemView.findViewById(R.id.txt_title);
            author = (TextView) itemView.findViewById(R.id.txt_author);
            rate = (TextView) itemView.findViewById(R.id.txt_rate);
        }
    }
}
