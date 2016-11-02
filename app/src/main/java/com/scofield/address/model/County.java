package com.scofield.address.model;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;

@Table("County")
public class County implements Serializable {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    public int id;
    public int city_id;
    public String name;
}