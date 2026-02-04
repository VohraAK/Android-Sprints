package com.example.listycitylab3;

public class City {
    private String name;
    private String province;

    // default constructor
    public City(String name, String province) {
        this.name = name;
        this.province = province;
    }


    // getters
    public String getName() {
        return this.name;
    }

    public String getProvince() {
        return this.province;
    }


    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
