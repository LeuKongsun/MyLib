package com.example.kongsun.mylib.activity;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kongsun.mylib.R;
import com.example.kongsun.mylib.adapter.Book;
import com.example.kongsun.mylib.adapter.OnRclView2Dimen;

import java.util.ArrayList;
import java.util.List;

public class NestedRecyclerView extends Fragment implements OnRclView2Dimen{
    Context context;
    RecyclerView recyclerView;
    OuterRecycler outerAdapter;
    //InnerRecyclerView innerAdapter;
    private List<Book> bookList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview_outer, container, false);
        prepareBookData();
        recyclerView = (RecyclerView) view.findViewById(R.id.outer_recyclerview);
        //Layout Manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        //Adapter outer
        outerAdapter = new OuterRecycler(this);
        recyclerView.setAdapter(outerAdapter);
        return view;



    }
    // End of Activity
    private void prepareBookData() {
        Book book = new Book("Microsoft Office", "Bill Gate", "4.5", "");
        bookList.add(book);
        book = new Book("Advance Access", "Leu Kongsun", "4.9", "");
        bookList.add(book);
        book = new Book("C# 2017", "Steve Job", "4.5", "");
        bookList.add(book);
        book = new Book("Windows10", "Linus", "4.5", "");
        bookList.add(book);
        book = new Book("Advance Excel", "Paul Allen", "4.5", "");
        bookList.add(book);
        book = new Book("VB.Net 2017", "Jeff Bezos", "4.5", "");
        bookList.add(book);
        book = new Book("Command Prompt", "Jack Ma", "4.5", "");
        bookList.add(book);
    }

    @Override
    public void OnRclView2Dimen(Book selectedbook) {
        Log.d("Hello","position:"+selectedbook.getTitle());
    }

    // Outer RecyclerView
    class OuterRecycler extends RecyclerView.Adapter<OuterRecycler.ViewHolder> {
        InnerRecyclerView innerRCAdapter;
        OuterRecycler(OnRclView2Dimen onRclView2Dimen) {
            innerRCAdapter = new InnerRecyclerView(bookList, onRclView2Dimen);
        }

        @Override
        public OuterRecycler.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_inner, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(OuterRecycler.ViewHolder holder, int position) {
        }

        @Override
        public int getItemCount() {
            return 3;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            RecyclerView innerRecyclerView;

            ViewHolder(View itemView) {
                super(itemView);
                innerRecyclerView = (RecyclerView) itemView.findViewById(R.id.inner_recyclerview);
                innerRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                innerRecyclerView.setAdapter(innerRCAdapter);
            }

        }
    }
    //Inner Recycler View

    class InnerRecyclerView extends RecyclerView.Adapter<InnerRecyclerView.ViewHolder> {
        private List<Book> books;
        private OnRclView2Dimen onRclView2Dimen;
        InnerRecyclerView(List<Book> bookList, OnRclView2Dimen onRclView2Dimen) {
            this.books = bookList;
            this.onRclView2Dimen = onRclView2Dimen;
        }


        @Override
        public InnerRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.book_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Book book = books.get(position);
            holder.title.setText(book.getTitle());
            holder.author.setText(book.getAuthor());
            holder.rate.setText(book.getRate());
        }

        @Override
        public int getItemCount() {
            return bookList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView title;
            private TextView author;
            private TextView rate;

            ViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onRclView2Dimen.OnRclView2Dimen(books.get(getAdapterPosition()));
                    }
                });
                title = (TextView) itemView.findViewById(R.id.txt_title);
                author = (TextView) itemView.findViewById(R.id.txt_author);
                rate = (TextView) itemView.findViewById(R.id.txt_rate);
            }
        }
    }
}