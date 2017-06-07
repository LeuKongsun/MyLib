package com.example.kongsun.mylib.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kongsun.mylib.R;
import com.example.kongsun.mylib.adapter.Book;
import com.example.kongsun.mylib.adapter.OnRecyclerViewItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kongsun on 6/1/17.
 */

public class BookNestedRCViewActivity extends AppCompatActivity implements OnRecyclerViewItemClickListener{

    RecyclerView outerRecyclerView;
    OuterRecyclerView outerAdapter;
    private List<Book> bookList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_outer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.outer_toolbar);
        setSupportActionBar(toolbar);
        setTitle("Home");
        prepareBookData();

        outerAdapter = new OuterRecyclerView();
        outerRecyclerView = (RecyclerView) findViewById(R.id.outer_recyclerview);
        outerRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        outerRecyclerView.setItemAnimator(new DefaultItemAnimator());
        outerRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        outerRecyclerView.setAdapter(outerAdapter);
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
    public void onRecyclerViewItemClickListener(int position) {
        for (int i=0 ; i<10 ; i++)
        if (position==i) {
            Toast.makeText(getApplicationContext(), "Downloading ", Toast.LENGTH_SHORT).show();
        }
    }


    class OuterRecyclerView extends RecyclerView.Adapter<OuterRecyclerView.ViewHolder> {
        InnerRecyclerView innerRCAdapter;
        OuterRecyclerView() {
            innerRCAdapter = new InnerRecyclerView(bookList);
        }

        @Override
        public OuterRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_inner, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(OuterRecyclerView.ViewHolder holder, int position) {
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            RecyclerView innerRecyclerView;

            public ViewHolder(View itemView) {
                super(itemView);
                innerRecyclerView = (RecyclerView) itemView.findViewById(R.id.inner_recyclerview);
                innerRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                innerRecyclerView.setAdapter(innerRCAdapter);
            }

        }
    }

    class InnerRecyclerView extends RecyclerView.Adapter<InnerRecyclerView.ViewHolder> {
        private List<Book> books;
        private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
        public InnerRecyclerView(List<Book> bookList) {
            this.books = bookList;
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

        public class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView thumnail;
            private TextView title;
            private TextView author;
            private TextView rate;

            public ViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onRecyclerViewItemClickListener.onRecyclerViewItemClickListener(getLayoutPosition());
                    }
                });
                thumnail = (ImageView) itemView.findViewById(R.id.item_book);
                title = (TextView) itemView.findViewById(R.id.txt_title);
                author = (TextView) itemView.findViewById(R.id.txt_author);
                rate = (TextView) itemView.findViewById(R.id.txt_rate);
            }
        }
    }
}

