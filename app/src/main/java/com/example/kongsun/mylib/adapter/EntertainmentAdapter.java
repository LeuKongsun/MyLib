package com.example.kongsun.mylib.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.kongsun.mylib.R;
import com.example.kongsun.mylib.activity.Myapp;

/**
 * Created by kongsun on 7/21/17.
 */

public class EntertainmentAdapter extends RecyclerView.Adapter<EntertainmentAdapter.MyViewHolder> {
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    private Book[] bookList;
    private Context context;
    public EntertainmentAdapter(Context context){
        bookList = new Book[0];
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Book book = bookList[position];
        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthor());
        holder.imgthumnail.setImageUrl(book.getThumnailUrl(), Myapp.getInstance(context).getImageLoader());

    }

    @Override
    public int getItemCount() {
        return bookList.length;
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    public void setBookList(Book[] bookList) {
        this.bookList = bookList;
        notifyDataSetChanged();
    }

    public Book getBook(int position) {
        return bookList[position];
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView author;
        private NetworkImageView imgthumnail;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRecyclerViewItemClickListener.onRecyclerViewItemClickListener(getAdapterPosition());
                }
            });
            title = (TextView) itemView.findViewById(R.id.txt_titleL);
            author = (TextView) itemView.findViewById(R.id.txt_authorL);
            imgthumnail = (NetworkImageView) itemView.findViewById(R.id.img_thumnailL);
        }
    }
}
