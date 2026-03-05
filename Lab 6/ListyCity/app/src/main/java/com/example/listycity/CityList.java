package com.example.listycity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is a class that maintains a list of City objects.
 * @author Abdullah Khurram Vohra
 * @see City
 */
public class CityList {
    /**
     * a list of City objects
     */
    private List<City> cities = new ArrayList<City>();

    /**
     * Adds a city to the list, if it is not already present.
     * @param city
     *      This is a City object, to be added to the list.
     */
    public void addCity(City city) {

        if (city == null || cities.contains(city))
        {
            throw new IllegalArgumentException();
        }

        cities.add(city);
    }


    /**
     * This method returns a sorted list of cities
     * @return
     *      The sorted list.
     */
    public List<City> getSortedCities() {
        List<City> list = cities;
        Collections.sort(list);
        return list;
    }

    // ----------

    /**
     * This method checks if a city is in the list.
     * @param city
     *      City to be checked.
     * @return
     *      Flag indicating status (Boolean).
     */
    public boolean hasCity(City city)
    {
        return cities.contains(city);
    }

    /**
     * This method deletes a city from the list.
     * @param city
     *      City to be deleted from the list.
     */
    public void delete(City city)
    {
        if (hasCity(city))
        {
            cities.remove(city);
        }

        else
        {
            throw new IllegalArgumentException();
        }
    }

    /**
     * This method returns the number of cities in the list.
     * @return
     *      The size of the list of cities.
     */
    public int countCities()
    {
        return cities.size();
    }
}
