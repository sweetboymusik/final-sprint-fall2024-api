package com.keyin.city;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CityFormattedDTOTest {

    @Test
    void testConstructorAndGetter() {
        // Arrange
        String name = "SampleCity";
        String state = "SampleState";

        // Act
        CityFormattedDTO cityFormattedDTO = new CityFormattedDTO(name, state);

        // Assert
        assertThat(cityFormattedDTO.getFormattedAddress())
                .isEqualTo("SampleCity, SampleState");
    }

    @Test
    void testSetter() {
        // Arrange
        CityFormattedDTO cityFormattedDTO = new CityFormattedDTO("OldCity", "OldState");

        // Act
        cityFormattedDTO.setFormattedAddress("NewCity, NewState");

        // Assert
        assertThat(cityFormattedDTO.getFormattedAddress())
                .isEqualTo("NewCity, NewState");
    }
}