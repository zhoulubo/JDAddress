package com.scofield.address.widget;

import android.content.Context;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBaseConfig;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.scofield.address.AssetsDatabaseManager;
import com.scofield.address.model.City;
import com.scofield.address.model.County;
import com.scofield.address.model.Province;
import com.scofield.address.model.Street;


class DefaultAddressProvider implements AddressProvider {
    private LiteOrm liteOrm;

    DefaultAddressProvider(Context context) {
        AssetsDatabaseManager mg = AssetsDatabaseManager.getManager();
        mg.getDatabase("area.db");
        DataBaseConfig config = new DataBaseConfig(context, mg.getDatabaseFile("area.db"));
        liteOrm = LiteOrm.newSingleInstance(config);
    }

    @Override
    public void provideProvinces(final AddressReceiver<Province> addressReceiver) {
        addressReceiver.send(liteOrm.query(Province.class));
    }

    @Override
    public void provideCitiesWith(int provinceId, final AddressReceiver<City> addressReceiver) {
        addressReceiver.send(liteOrm.query(new QueryBuilder<City>(City.class).where("province_id"+"= ?",new String[]{String.valueOf(provinceId)})));
    }

    @Override
    public void provideCountiesWith(int cityId, final AddressReceiver<County> addressReceiver) {
        addressReceiver.send(liteOrm.query(new QueryBuilder<County>(County.class).where("city_id"+"= ?",new String[]{String.valueOf(cityId)})));
    }

    @Override
    public void provideStreetsWith(int countyId, final AddressReceiver<Street> addressReceiver) {
        addressReceiver.send(liteOrm.query(new QueryBuilder<Street>(Street.class).where("county_id"+"= ?",new String[]{String.valueOf(countyId)})));
    }

}
