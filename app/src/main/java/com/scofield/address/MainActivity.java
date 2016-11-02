package com.scofield.address;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBaseConfig;
import com.scofield.address.model.City;
import com.scofield.address.model.County;
import com.scofield.address.model.Province;
import com.scofield.address.model.Street;
import com.scofield.address.widget.AddressSelector;
import com.scofield.address.widget.BottomDialog;
import com.scofield.address.widget.OnAddressSelectedListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnAddressSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AssetsDatabaseManager.initManager(this);

        AssetsDatabaseManager mg = AssetsDatabaseManager.getManager();
        mg.getDatabase("area.db");
        DataBaseConfig config = new DataBaseConfig(this, mg.getDatabaseFile("area.db"));
        LiteOrm liteOrm = LiteOrm.newSingleInstance(config);
        ArrayList<Province> provinceArrayList = liteOrm.query(Province.class);

        for (Province province : provinceArrayList) {
            Log.e("Province", "Province: "+ province.name==null?"null":province.name);
        }




        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frameLayout);

        AddressSelector selector = new AddressSelector(this);
        selector.setOnAddressSelectedListener(this);
        //        selector.setAddressProvider(new DefaultAddressProvider());

        assert frameLayout != null;
        frameLayout.addView(selector.getView());

        Button buttonBottomDialog = (Button) findViewById(R.id.buttonBottomDialog);
        assert buttonBottomDialog != null;
        buttonBottomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                BottomDialog.show(MainActivity.this, MainActivity.this);
                BottomDialog dialog = new BottomDialog(MainActivity.this);
                dialog.setOnAddressSelectedListener(MainActivity.this);
                dialog.show();
            }
        });

    }

    @Override
    public void onAddressSelected(Province province, City city, County county, Street street) {
        String s =
                (province == null ? "" : province.name) +
                        (city == null ? "" : "\n" + city.name) +
                        (county == null ? "" : "\n" + county.name) +
                        (street == null ? "" : "\n" + street.name);

        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
