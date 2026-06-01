package com.example.bus;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DriverValidationTest {

    @Test
    void testValidDriverID() {

        assertTrue(
                ValidationUtils.validDriverID(
                        "23@@##45AB"));
    }

    @Test
    void testInvalidDriverID() {

        assertFalse(
                ValidationUtils.validDriverID(
                        "11AAAAAAAB"));
    }

    @Test
    void testValidBirthdate() {

        assertTrue(
                ValidationUtils.validBirthdate(
                        "01-01-2000"));
    }

    @Test
    void testInvalidBirthdate() {

        assertFalse(
                ValidationUtils.validBirthdate(
                        "2000-01-01"));
    }

    @Test
    void testValidAddress() {

        assertTrue(
                ValidationUtils.validAddress(
                        "12|MainStreet|Melbourne|VIC|Australia"));
    }

    @Test
    void testInvalidAddress() {

        assertFalse(
                ValidationUtils.validAddress(
                        "InvalidAddress"));
    }
}