package com.example.bus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class DriverRepositoryIntegrationTest {

    private DriverRepository repo;

    @BeforeEach
    void setup() {

        File file = new File("testDriver.txt");

        if (file.exists()) {
            file.delete();
        }

        repo = new DriverRepository("testDriver.txt");
    }

    @Test
    void testValidDriverStoredCorrectly() {

        Driver driver =
                new Driver(
                        "23@@##45AB",
                        "John Smith",
                        5,
                        "Heavy",
                        "12|MainStreet|Melbourne|VIC|Australia",
                        "01-01-1995"
                );

        assertTrue(repo.add(driver));

        Driver retrieved =
                repo.retrieve("23@@##45AB");

        assertNotNull(retrieved);

        assertEquals("John Smith",
                retrieved.getName());
    }

    @Test
    void testDuplicateDriverRejected() {

        Driver d1 =
                new Driver(
                        "23@@##45AB",
                        "John",
                        5,
                        "Heavy",
                        "12|MainStreet|Melbourne|VIC|Australia",
                        "01-01-1995"
                );

        Driver d2 =
                new Driver(
                        "23@@##45AB",
                        "Mike",
                        7,
                        "PublicTransport",
                        "20|KingStreet|Sydney|NSW|Australia",
                        "05-05-1990"
                );

        assertTrue(repo.add(d1));

        assertFalse(repo.add(d2));
    }

    @Test
    void testUpdateDriverDetails() {

        Driver driver =
                new Driver(
                        "23@@##45AB",
                        "John",
                        5,
                        "Heavy",
                        "12|MainStreet|Melbourne|VIC|Australia",
                        "01-01-1995"
                );

        repo.add(driver);

        Driver updated =
                new Driver(
                        "23@@##45AB",
                        "John",
                        5,
                        "Heavy",
                        "99|NewStreet|Melbourne|VIC|Australia",
                        "01-01-1995"
                );

        assertTrue(repo.update(updated));

        Driver retrieved =
                repo.retrieve("23@@##45AB");

        assertEquals(
                "99|NewStreet|Melbourne|VIC|Australia",
                retrieved.getAddress()
        );
    }

    @Test
    void testDriverCountUpdatedCorrectly() {

        repo.add(
                new Driver(
                        "23@@##45AB",
                        "John",
                        5,
                        "Heavy",
                        "12|MainStreet|Melbourne|VIC|Australia",
                        "01-01-1995"
                ));

        repo.add(
                new Driver(
                        "34$$%%67CD",
                        "Mike",
                        8,
                        "PublicTransport",
                        "55|KingStreet|Sydney|NSW|Australia",
                        "05-05-1990"
                ));

        assertEquals(2, repo.count());
    }
}