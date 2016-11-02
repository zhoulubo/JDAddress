package com.scofield.address.widget;


import com.scofield.address.model.City;
import com.scofield.address.model.County;
import com.scofield.address.model.Province;
import com.scofield.address.model.Street;

public interface OnAddressSelectedListener {
    void onAddressSelected(Province province, City city, County county, Street street);
}
