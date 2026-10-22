<?java
package com.alertnews.app;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.alertnews.app.adapters.NewsAdapter;
import com.alertnews.app.databapters.NewsItem;
import com.alertnews.app.viewmodels.NewsViewModel;

public class MainActivity extends AppCompatActivity {

    private NewsViewModel newsViewModel;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsViewModel = new NewsViewModel(this);

        RecyclerView recyclerView = findViewById(R.id.news_list);
        recyclerViewsetLayoutManager(new LinearLayoutManager(this));

        newsAdapter = new NewsAdapter();
        recyclerView.setAdapter(newsAdapter);

        NewsRepository.getInstance(this).getAll(new NewsRepository.LoadCallback() {
            @Override
            public void onLoaded(List<NewsItem> items) {
                newsAdapter.setItems(items);
            }

            @Override
            public void onError(Exception error) {
                ToastHelper.showError(MainActivity.this, "Failed to load news", error);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(v -> showAddNewsDialog());
    }

    private void showAddNewsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add News")
               .setItems(R.array.sources, (dialog, which) -> {
                   // Add source selection callback
               })
               .show();
    }
}
