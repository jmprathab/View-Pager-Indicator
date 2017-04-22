package com.prathab.viewpagerindicator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  ViewPagerIndicator viewPagerIndicator = null;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    viewPagerIndicator = (ViewPagerIndicator) findViewById(R.id.viewPagerIndicator);
  }

  public void gotToNextPage(View view) {
    if (viewPagerIndicator.isLastPage()) {
      Toast.makeText(this, "You are at the last page :-)", Toast.LENGTH_SHORT).show();
      return;
    }
    viewPagerIndicator.goToNextPage();
  }

  public void gotToPreviousPage(View view) {
    if (viewPagerIndicator.isFirstPage()) {
      Toast.makeText(this, "You are at the first page :-)", Toast.LENGTH_SHORT).show();
      return;
    }
    viewPagerIndicator.goToPreviousPage();
  }
}