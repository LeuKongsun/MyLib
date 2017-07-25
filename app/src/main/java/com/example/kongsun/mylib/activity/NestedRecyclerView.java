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
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.example.kongsun.mylib.R;
import com.example.kongsun.mylib.adapter.Book;
import com.example.kongsun.mylib.adapter.OnRclView2Dimen;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NestedRecyclerView extends Fragment implements OnRclView2Dimen{
    Context context;
    RecyclerView recyclerView;
    OuterRecycler outerAdapter;
    //InnerRecyclerView innerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview_outer, container, false);
  //      prepareBookData();
        loadBookFromServer();
        recyclerView = (RecyclerView) view.findViewById(R.id.outer_recyclerview);
        //Layout Manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        outerAdapter = new OuterRecycler(this);
        recyclerView.setAdapter(outerAdapter);
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
    private void loadBookFromServer()
    {
        String url = "http://192.168.0.136/test.php";
        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("ckcc", response.toString());
                List<Book> bookList = new ArrayList<>();
                try{
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int id = jsonObject.getInt("ID");
                        String title = jsonObject.getString("Title");
                        String author = jsonObject.getString("Author");
                        String thumbnailUrl = jsonObject.getString("ThumnailUrl");
                        Book book = new Book(id,title,author,thumbnailUrl);
                        bookList.add(book);
                    }
                    outerAdapter.setBookList(bookList);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Loading data from server error", Toast.LENGTH_LONG).show();
            }
        };
        JsonArrayRequest request = new JsonArrayRequest(url, listener, errorListener);
        Myapp.getInstance(getActivity()).addRequest(request);


    }


    @Override
    public void OnRclView2Dimen(Book selectedBook) {
        Log.d("Hello","position:"+selectedBook.getTitle());
    }



    /*------------------------------------------------------------------------------------*/
    // Outer RecyclerView
    class OuterRecycler extends RecyclerView.Adapter<OuterRecycler.ViewHolder> {
        InnerRecyclerView innerRCAdapter;
        private List<Book> bookList;

        OuterRecycler(OnRclView2Dimen onRclView2Dimen) {
            innerRCAdapter = new InnerRecyclerView( onRclView2Dimen);
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
            return 1;
        }

        public void setBookList(List<Book> bookList) {
            this.bookList = bookList;
            innerRCAdapter.setBooks(bookList);
            notifyDataSetChanged();
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

        public void setBooks(List<Book> books) {
            this.books = books;
            notifyDataSetChanged();
        }

        private OnRclView2Dimen onRclView2Dimen;

        InnerRecyclerView(OnRclView2Dimen onRclView2Dimen) {
            this.onRclView2Dimen = onRclView2Dimen;
            books = new ArrayList<>();
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
            holder.imgthumnail.setImageUrl(book.getThumnailUrl(),Myapp.getInstance(context).getImageLoader());
        }

        @Override
        public int getItemCount() {
            return books.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView title;
            private TextView author;
            private NetworkImageView imgthumnail;

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
                imgthumnail = (NetworkImageView) itemView.findViewById(R.id.img_thumnail);
            }
        }
    }
}