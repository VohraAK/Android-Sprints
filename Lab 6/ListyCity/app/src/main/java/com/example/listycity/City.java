package com.example.listycity;
import java.util.Objects;

/**
 * This is a class that defines a city with its province.
 * @author Abdullah Khurram Vohra
 */
public class City implements Comparable {
    /**
     * The name of the city.
     */
    private String city;

    /**
     * The name of the province.
     */
    private String province;

    /**
     * Constructor for the City class
     * @param city
     *      The name of the city
     * @param province
     *      The name of the province
     */
    public City(String city, String province) {
        this.city = city;
        this.province = province;
    }

    // getters
    /** Gets the name of the city
     * @return
     *      The name of the city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets the name of the province
     * @return
     *      The name of the province.
     */
    public String getProvince() {
        return province;
    }


    // overrides
    /**
     * This method overrides compareTo for City class.
     */
    @Override
    public int compareTo(Object o) {
        City city = (City) o;
        return this.getCity().compareTo(city.getCity());
    }

    // generated via Android Studio

    /**
     * This method overrides equals for City class.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        City city1 = (City) o;
        return Objects.equals(city, city1.city) && Objects.equals(province, city1.province);
    }

    /**
     * This method overrides hashCode for City class.
     */
    @Override
    public int hashCode() {
        return Objects.hash(city, province);
    }
}
