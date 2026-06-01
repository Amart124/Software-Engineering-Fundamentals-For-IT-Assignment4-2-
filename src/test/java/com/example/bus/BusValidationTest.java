package com.example.bus;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BusValidationTest {

    @Test
    void testValidBusID() {

        assertTrue(
                ValidationUtils.validBusID("12345678"));
    }

    @Test
    void testInvalidBusIDLetters() {

        assertFalse(
                ValidationUtils.validBusID("12AB5678"));
    }

    @Test
    void testInvalidBusIDLength() {

        assertFalse(
                ValidationUtils.validBusID("1234"));
    }
}