package com.ezmoizy.abdulmoiz.warmup2;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.ezmoizy.abdulmoiz.warmup2.com.ezmoizy.abdulmoiz.marmup2.fragments.BuildingsIndexFragment;
import com.ezmoizy.abdulmoiz.warmup2.com.ezmoizy.abdulmoiz.marmup2.fragments.GmapFragment;
import com.ezmoizy.abdulmoiz.warmup2.com.ezmoizy.abdulmoiz.marmup2.fragments.MainFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FloatingActionButton next_class_fab;
    FloatingActionButton location_fab;
    private static String[] BUILDINGS = {"Alumni Memorial Building",
            "AN Bourns Science Building ABB",
            "Burke Science Building BSB",
            "Chester New Hall CNH",
            "Commons Building",
            "Communications Research Library",
            "David Braley Athletics Centre DBAC",
            "Degroote School Of Business DSB",
            "ET Clarke Centre",
            "General Sciences",
            "Gilmour Hall GH",
            "Hamilton Hall HH",
            "HG Thode Library",
            "Information Technology Building ITB",
            "Institute For Applied Health Sciences IAHS",
            "Ivor Wynne Centre IWC",
            "John Hodgins Building JHE",
            "Kenneth Taylor Hall KTH)",
            "Life Sciences Building LSB",
            "McMaster University Student Centre MUSC",
            "Michael DeGroote Centre for Learning and Discovery MDCL",
            "Mills Library",
            "Museum Of Art",
            "Nuclear Research Building NRB",
            "Psychology Building",
            "Ron Joyce Stadium",
            "T13",
            "T28",
            "T29",
            "Tandem Accelerator",
            "Togo Salmon Hall TSH",
            "University Hall"};

    private static double[][] BUILDING_LOCATIONS = {{43.263945, -79.919631},
            {43.260480, -79.921781},
            {43.261905, -79.920228},
            {43.263840, -79.918396},
            {43.265533, -79.919194},
            {43.259150, -79.919313},
            {43.265071, -79.916544},
            {43.263983, -79.916418},
            {43.261769, -79.922046},
            {43.262337, -79.921333},
            {43.263172, -79.918529},
            {43.263192, -79.920048},
            {43.260876, -79.922310},
            {43.258751, -79.920896},
            {43.259777, -79.920398},
            {43.265683, -79.914497},
            {43.260867, -79.920181},
            {43.264001, -79.916836},
            {43.260953, -79.917795},
            {43.263157, -79.917891},
            {43.261222, -79.916954},
            {43.262840, -79.917736},
            {43.262636, -79.918135},
            {43.261313, -79.921058},
            {43.259781, -79.919677},
            {43.266341, -79.916979},
            {43.258656, -79.919534},
            {43.265561, -79.917821},
            {43.265636, -79.918380},
            {43.262027, -79.921188},
            {43.263450, -79.918986}};



    String[][] location = {{"name", "lat", "long"}, {"name"}, {"lat"}, {"long"}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("McMaster Map App");
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //button to initiate a route and plot on map (this is to test the mapNavigator library)
        next_class_fab = (FloatingActionButton) findViewById(R.id.class_fab);
        next_class_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNextPressed(view);
            }
        });

        //button to center on a location
        location_fab = (FloatingActionButton) findViewById(R.id.location_fab);
        location_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                centerLocationPressed(view);
            }
        });

        //create slide-out-menu (navigation drawer)
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //create navigation view and listener
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragManager = getFragmentManager();

        //inflate google map to main activity
        fragManager.beginTransaction().replace(R.id.content_frame, new GmapFragment()).commit();

        //Initialize search bar functionality
        searchFeature();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
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

        Intent explicitIntent;

        if (id == R.id.nav_camara) {
            // Handle the camera action
            getFragmentManager().beginTransaction().replace(R.id.content_frame, new MainFragment()).commit();
            setNavButtonsVisibility(false);

        } else if (id == R.id.nav_gallery) {
            getFragmentManager().beginTransaction().replace(R.id.content_frame, new GmapFragment()).commit();
            setNavButtonsVisibility(true);

        } else if (id == R.id.nav_slideshow) {
            getFragmentManager().beginTransaction().replace(R.id.content_frame, new BuildingsIndexFragment()).commit();
            setNavButtonsVisibility(false);

        } else if (id == R.id.nav_manage) {
            getFragmentManager().beginTransaction().replace(R.id.content_frame, new GmapFragment()).commit();
            setNavButtonsVisibility(true);

        } else if (id == R.id.nav_share) {
            getFragmentManager().beginTransaction().replace(R.id.content_frame, new MainFragment()).commit();
            setNavButtonsVisibility(false);

        } else if (id == R.id.nav_send) {
            getFragmentManager().beginTransaction().replace(R.id.content_frame, new GmapFragment()).commit();
            setNavButtonsVisibility(true);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onNextPressed(View view) {
        Snackbar.make(view, "Added new location.", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();

        //it worked!!!
        GmapFragment map_fragment = (GmapFragment) getFragmentManager().findFragmentById(R.id.content_frame);
        //map_fragment.addNewMarker(43.531334, -80.226035, "University of Guelph");
        map_fragment.navigatorTest();


    }

    public void centerLocationPressed(View view) {
        Snackbar.make(view, "This button will center the map on your location.", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();

        GmapFragment map_fragment = (GmapFragment) getFragmentManager().findFragmentById(R.id.content_frame);
        map_fragment.centerMap(43.261926, -79.919182);

    }

    public void setNavButtonsVisibility(Boolean n){
        if(next_class_fab.isShown() && n == false){
            next_class_fab.hide();
            location_fab.hide();
        }  else if (!next_class_fab.isShown() && n==true){
            next_class_fab.show();
            location_fab.show();
        }
    }


    //Method to enable autocomplete feature for search bar
    public void searchFeature(){

        //input search bar feature here
        final AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

        // Create an ArrayAdapter containing country names
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.list_item, BUILDINGS);

        // Set the adapter for the AutoCompleteTextView
        textView.setAdapter(adapter);

        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            // Display a Toast Message when the user clicks on an item in the AutoCompleteTextView
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //temporary toast
                hideSoftKeyboard(MainActivity.this);
                for(int i = 0; i < BUILDING_LOCATIONS.length; i++){
                    if(BUILDINGS[i] == adapter.getItem(arg2).toString()){
                        plotLocation(BUILDINGS[i], BUILDING_LOCATIONS[i]);
                        break;
                    }
                }

            }
        });

    }

    //Method to hide keyboard
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void plotLocation(String name, double[] location){
        GmapFragment map_fragment = (GmapFragment) getFragmentManager().findFragmentById(R.id.content_frame);
        //map_fragment.removeAllMarkers();
        map_fragment.addNewMarker(location[0], location[1], name);
        map_fragment.centerMap(location[0], location[1]);


        //method below is to test out map Navigator library
        //map_fragment.navigatorTest();

    }



}
