package com.keyin.airport;

import com.keyin.city.CityFormattedDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AirportFormattedDTOTest {

    @Test
    void testConstructorWithNameAndCode() {
        String name = "Test Airport";
        String code = "TST";

        AirportFormattedDTO airportFormattedDTO = new AirportFormattedDTO(name, code);

        assertEquals("Test Airport (TST)", airportFormattedDTO.getFormattedName());
    }

    @Test
    void testConstructorWithNameCodeAndGate() {
        String name = "Test Airport";
        String code = "TST";
        String gate = "G1";

        AirportFormattedDTO airportFormattedDTO = new AirportFormattedDTO(name, code, gate);

        assertEquals("Test Airport (TST) | Gate G1", airportFormattedDTO.getFormattedName());
    }

    @Test
    void testConstructorWithNameCodeCityAndGate() {
        String name = "Test Airport";
        String code = "TST";
        CityFormattedDTO city = new CityFormattedDTO("Test City", "Test State");
        String gate = "G1";

        AirportFormattedDTO airportFormattedDTO = new AirportFormattedDTO(name, code, city, gate);

        assertEquals("Test Airport (TST) | Test City, Test State | Gate G1", airportFormattedDTO.getFormattedName());
    }

    @Test
    void testSetFormattedName() {
        AirportFormattedDTO airportFormattedDTO = new AirportFormattedDTO("Initial", "INIT");
        String updatedFormattedName = "Updated Airport (UPD)";

        airportFormattedDTO.setFormattedName(updatedFormattedName);

        assertEquals(updatedFormattedName, airportFormattedDTO.getFormattedName());
    }
}