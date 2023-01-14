package com.example.taazakhabar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    List<Listitem> listitems;
    String url="https://newscatcher.p.rapidapi.com/v1/latest_headlines?lang=en&country=IN&media=True";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,null);

        LinearLayoutCompat progressBar=view.findViewById(R.id.progressBarHome);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView = view.findViewById(R.id.recyclerViewHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        listitems = new ArrayList<Listitem>();

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                OnResponse
                try {
                    progressBar.setVisibility(View.GONE);
                    JSONArray jsonArray=response.getJSONArray("articles");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        Listitem listitem=new Listitem(jsonObject.getString("title"),
                                jsonObject.getString("link"),
                                jsonObject.getString("published_date"),
                                jsonObject.getString("author"),
                                jsonObject.getString("media"));

                        listitems.add(listitem);
                    }
                    recyclerViewAdapter = new RecyclerViewAdapter(listitems, getContext());

                    recyclerView.setAdapter(recyclerViewAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error occured", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap header=new HashMap();
                header.put("X-RapidAPI-Key","bf63d5c917msh42dd9cdabbffa68p1fb4d9jsnff4de5aec2a6");
                header.put("X-RapidAPI-Host", "newscatcher.p.rapidapi.com");
                return header;
            }
        };

        requestQueue.add(jsonObjectRequest);

        return view;
    }
}