package com.example.listycity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CityListTest {

    private CityList mockCityList()
    {
        CityList cityList = new CityList();
        cityList.addCity(mockCity());
        return cityList;
    }

    private City mockCity()
    {
        return new City("Lahore", "Punjab");
    }

    @Test
    public void testAdd()
    {
        CityList cityList = mockCityList();
        assertEquals(1, cityList.countCities());

        // simple add logic testing
        City city = new City("Quetta", "Balochistan");
        cityList.addCity(city);

        assertEquals(2, cityList.getSortedCities().size());
        assertTrue(cityList.getSortedCities().contains(city));
    }

    @Test
    void testAddException()
    {
        CityList cityList = mockCityList();
        City city = new City("Gujrat", "Punjab");
        cityList.addCity(city);

        // test for exception-throw
        assertThrows(IllegalArgumentException.class, () -> {
            cityList.addCity(city);
        });
    }

    @Test
    void testGetCities()
    {
        CityList cityList = mockCityList();
        assertEquals(1, cityList.getSortedCities().size());

        // check if the city is actually the mock city
        assertEquals(0, mockCity().compareTo(cityList.getSortedCities().get(0)));

        // add another city
        City city = new City("Jacobabad", "Sindh");
        cityList.addCity(city);

        assertEquals(0, city.compareTo(cityList.getSortedCities().get(0)));
        assertEquals(0, mockCity().compareTo(cityList.getSortedCities().get(1)));
    }


    // ----------

    @Test
    void testHasCity()
    {
        // check if mock city exists
        CityList cityList = mockCityList();
        assertTrue(cityList.hasCity(mockCity()));

        // check if this exists
        City city = new City("Quetta", "Balochistan");
        cityList.addCity(city);

        assertTrue(cityList.hasCity(city));

        // sanity check (I'm insane)
        assertTrue(cityList.hasCity(mockCity()));

    }

    @Test
    void testDelete()
    {
        CityList cityList = mockCityList();
        City city = new City("Quetta", "Balochistan");
        cityList.addCity(city);

        // check if Quetta is in the list
        assertTrue(cityList.hasCity(city));

        // delete Quetta
        cityList.delete(city);
        assertFalse(cityList.hasCity(city));

        // delete mock city
        cityList.delete(mockCity());
        assertFalse(cityList.hasCity(mockCity()));

        // delete non-existing city
        City finalCity = city;
        assertThrows(IllegalArgumentException.class, () -> {
            cityList.delete(finalCity);
        });

        // add another city
        cityList.addCity(city);
        assertTrue(cityList.hasCity(city));

        // delete a different city not in the list
        city = new City("Peshawar", "Khyber Pakhtunkhwa");
        City finalCity1 = city;
        assertThrows(IllegalArgumentException.class, () -> {
            cityList.delete(finalCity1);
        });

    }

    @Test
    void testCountCities()
    {
        CityList cityList = mockCityList();
        assertEquals(1, cityList.countCities());

        City city = new City("Quetta", "Balochistan");
        cityList.addCity(city);
        assertEquals(2, cityList.countCities());

        // delete 1
        cityList.delete(mockCity());
        assertEquals(1, cityList.countCities());

        // delete 2
        cityList.delete(city);
        assertEquals(0, cityList.countCities());

    }
}
