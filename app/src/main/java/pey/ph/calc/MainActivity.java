package pey.ph.calc;

import android.content.res.Configuration;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class MainActivity extends AppCompatActivity implements TopFragment.OnFragmentInteractionListener, KeyboardFragment.OnFragmentInteractionListener {

    private boolean inverse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inverse = false;
        initFragments(inverse);
    }

    private void initFragments(boolean inverse) {
        for(Fragment fragment:getSupportFragmentManager().getFragments()){
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(!inverse) {
            fragmentTransaction.add(R.id.main, TopFragment.newInstance());
            fragmentTransaction.add(R.id.main, KeyboardFragment.newInstance());
        } else {
            fragmentTransaction.add(R.id.main, KeyboardFragment.newInstance());
            fragmentTransaction.add(R.id.main, TopFragment.newInstance());
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void switchFragment() {
        initFragments(!inverse);
        inverse = !inverse;
    }

    public void handleButtonClick(View view) {
        String text = String.valueOf(((Button) view).getText());
        if(text.equals("="))
            switchFragment();
    }
}
