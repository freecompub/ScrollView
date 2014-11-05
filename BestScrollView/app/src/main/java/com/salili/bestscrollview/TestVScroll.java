package com.salili.bestscrollview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.lib.scrollview.SynchroLayout;

import java.util.List;


public class TestVScroll extends ActionBarActivity implements SynchroLayout.OnViewsVisibilityListner {

    private TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_scroll);
        ((SynchroLayout) findViewById(R.id.container)).addListner(this);
        data = (TextView) findViewById(R.id.information);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test_scroll, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.go_to_hscrool) {
            Intent intent = new Intent(this, TestHScroll.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onChildViewVible(final List<View> visibleChild) {
        String message = "";
        for (int i = 0; i < visibleChild.size(); i++) {
            if (i == visibleChild.size() - 1) {
                message = message + visibleChild.get(i).getTag();
            } else
                message = message + visibleChild.get(i).getTag() + ",";
        }
        data.setText(message);

    }
}
