package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;


public class SecondActivitymodded extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private PieChart pieChart;
    private PieDataSet dataSet;
    private PieData data;
    private Button syncBtn;
    private TextView headerName;
    private TextView headerEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activitymodded);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        syncBtn = (Button)findViewById(R.id.syncBtn);
        pieChart = findViewById(R.id.pieChart);

        drawChart(pieChart);

        /**
         * Updates the receipt history and the pie chart when the user clicks the sync button.
         * Currently using a randomiser to randomly generate the values for each category.
         */
        syncBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateChart(pieChart);
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        headerName = (TextView)navigationView.getHeaderView(0).findViewById(R.id.headerName);
        headerEmail = (TextView)navigationView.getHeaderView(0).findViewById(R.id.headerEmail);

        headerName.setText(firebaseAuth.getCurrentUser().getDisplayName());
        headerEmail.setText(firebaseAuth.getCurrentUser().getEmail());


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.second_activitymodded, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_home) {

        } else if (id == R.id.nav_bills) {

        } else if (id == R.id.nav_food) {

        } else if (id == R.id.nav_food) {

        } else if (id == R.id.nav_misc) {

        } else if (id == R.id.nav_transport) {

        } else if (id == R.id.nav_logout) {
            Logout();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void Logout() {
        progressDialog.setMessage("Logging Out");
        progressDialog.show();
        firebaseAuth.signOut();
        finish();
        progressDialog.dismiss();
        Toast.makeText(SecondActivitymodded.this,"Logout Successful", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(SecondActivitymodded.this,MainActivity.class));
    }

    private void drawChart(PieChart pieChart) {
        pieChart.setUsePercentValues(true);

        ArrayList<PieEntry> yvalues = new ArrayList<PieEntry>();
        Random rand = new Random();
        int f = rand.nextInt(20);
        int t = rand.nextInt(25);
        int b = rand.nextInt(30);
        int m = rand.nextInt(25);

        yvalues.add(new PieEntry(f+1, "Food", 0));
        yvalues.add(new PieEntry(t+1, "Transport", 1));
        yvalues.add(new PieEntry(b+1, "Bills", 2));
        yvalues.add(new PieEntry(m+1, "Misc", 3));

        dataSet = new PieDataSet(yvalues, "");
        data = new PieData(dataSet);

        data.setValueFormatter(new PercentFormatter());
        pieChart.setData(data);
        Description description = new Description();
        description.setText("");
        pieChart.setDescription(description);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(58f);
        pieChart.setHoleRadius(58f);
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.BLACK);

    }

    private void updateChart(PieChart pieChart) {

        ArrayList<PieEntry> yvalues = new ArrayList<PieEntry>();
        Random rand = new Random();
        int f = rand.nextInt(20);
        int t = rand.nextInt(25);
        int b = rand.nextInt(30);
        int m = rand.nextInt(25);

        yvalues.add(new PieEntry(f+1, "Food", 0));
        yvalues.add(new PieEntry(t+1, "Transport", 1));
        yvalues.add(new PieEntry(b+1, "Bills", 2));
        yvalues.add(new PieEntry(m+1, "Misc", 3));

        dataSet = new PieDataSet(yvalues, "");
        data = new PieData(dataSet);

        data.setValueFormatter(new PercentFormatter());
        data.notifyDataChanged();
        pieChart.setData(data);
        pieChart.notifyDataSetChanged();
        pieChart.invalidate();
        Description description = new Description();
        description.setText("");
        pieChart.setDescription(description);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(58f);
        pieChart.setHoleRadius(58f);
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.BLACK);

    }
}
