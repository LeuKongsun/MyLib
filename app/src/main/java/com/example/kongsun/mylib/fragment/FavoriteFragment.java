package com.example.kongsun.mylib.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kongsun.mylib.R;
import com.example.kongsun.mylib.adapter.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kongsun on 7/14/17.
 */

public class FavoriteFragment extends Fragment {
    RecyclerView recyclerView;
    private List<Book> bookList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        //recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
/*
        // Layout Manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        // Adapter
        RecyclerViewList recyclerViewList = new RecyclerViewList();
        recyclerView.setAdapter(recyclerViewList);
        prepareBookData();
        */
        return view;

    }
    /*
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
    */
}
