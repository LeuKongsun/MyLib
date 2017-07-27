package com.example.kongsun.mylib.fragment;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.kongsun.mylib.R;
import com.example.kongsun.mylib.activity.Myapp;
import com.example.kongsun.mylib.adapter.Book;
import com.example.kongsun.mylib.adapter.EntertainmentAdapter;
import com.example.kongsun.mylib.adapter.OnRecyclerViewItemClickListener;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by kongsun on 7/21/17.
 */

public class Entertainment extends Fragment implements OnRecyclerViewItemClickListener,SwipeRefreshLayout.OnRefreshListener{
    private RecyclerView recyclerView;
    private EntertainmentAdapter adapter;
    private SwipeRefreshLayout lytLoading;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_entertainment, container, false);

        lytLoading = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);
        lytLoading.setOnRefreshListener(this);

        recyclerView = (RecyclerView) view.findViewById(R.id.rcl_entertainment);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new EntertainmentAdapter(getActivity());
        adapter.setOnRecyclerViewItemClickListener(this);
        recyclerView.setAdapter(adapter);
        loadBookFromServer();
        return view;
    }

    private void loadBookFromServer()
    {
        String url = "http://172.20.10.2/test.php";
        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("ckcc", response.toString());
                showLoading(false);
                try{
                    Book[] bookList = new Book[response.length()];
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int id = jsonObject.getInt("ID");
                        String title = jsonObject.getString("Title");
                        String author = jsonObject.getString("Author");
                        String thumbnailUrl = jsonObject.getString("ThumnailUrl");
                        Book book = new Book(id,title,author,thumbnailUrl);
                        bookList[i] = book;
                    }
                    adapter.setBookList(bookList);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Loading data from server error", Toast.LENGTH_LONG).show();
                showLoading(false);
            }
        };
        JsonArrayRequest request = new JsonArrayRequest(url, listener, errorListener);
        Myapp.getInstance(getActivity()).addRequest(request);
    }
    private void showLoading(boolean state){lytLoading.setRefreshing(state);}
    @Override
    public void onRecyclerViewItemClickListener(int position) {
        for (int i=0 ; i< position ; i++)
            if (position == i) Log.d("Elib","click success"+ position);
    }

    @Override
    public void onRefresh() {
        loadBookFromServer();
    }
}
