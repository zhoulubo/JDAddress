package com.scofield.address.widget;

import com.scofield.address.model.City;
import com.scofield.address.model.County;
import com.scofield.address.model.Province;
import com.scofield.address.model.Street;

import java.util.List;


public interface AddressProvider {
    void provideProvinces(AddressReceiver<Province> addressReceiver);
    void provideCitiesWith(int provinceId, AddressReceiver<City> addressReceiver);
    void provideCountiesWith(int cityId, AddressReceiver<County> addressReceiver);
    void provideStreetsWith(int countyId, AddressReceiver<Street> addressReceiver);

    interface AddressReceiver<T> {
        void send(List<T> data);
    }
}