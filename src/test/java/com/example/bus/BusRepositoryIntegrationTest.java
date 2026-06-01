package com.example.bus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class BusRepositoryIntegrationTest {

    private BusRepository repo;

    @BeforeEach
    void setup() {

        File file = new File("testBus.txt");

        if (file.exists()) {
            file.delete();
        }

        repo = new BusRepository("testBus.txt");
    }

    /**
     * BIT-01
     * Verify that a valid bus can be stored and retrieved correctly.
     */
    @Test
    void testValidBusStoredCorrectly() {

        Bus bus =
                new Bus("12345678", 45, 80.0, "Diesel");

        assertTrue(repo.add(bus));

        Bus retrieved =
                repo.retrieve("12345678");

        assertNotNull(retrieved);

        assertEquals("12345678",
                retrieved.getBusID());

        assertEquals(45,
                retrieved.getCapacity());

        assertEquals("Diesel",
                retrieved.getFuelType());

        assertEquals(1,
                repo.count());
    }

    /**
     * BIT-02
     * Verify duplicate bus IDs are rejected.
     */
    @Test
    void testDuplicateBusRejected() {

        Bus bus1 =
                new Bus("12345678",
                        40,
                        75.0,
                        "Diesel");

        Bus bus2 =
                new Bus("12345678",
                        50,
                        90.0,
                        "Hybrid");

        assertTrue(repo.add(bus1));

        assertFalse(repo.add(bus2));

        assertEquals(1,
                repo.count());
    }

    /**
     * BIT-03
     * Verify invalid bus IDs are rejected.
     */
    @Test
    void testInvalidBusIDRejected() {

        Bus bus =
                new Bus("1234A678",
                        40,
                        80.0,
                        "Diesel");

        assertFalse(repo.add(bus));

        assertEquals(0,
                repo.count());
    }

    /**
     * BIT-04
     * Verify capacity decrease is persisted.
     */
    @Test
    void testCapacityDecreasePersisted() {

        Bus bus =
                new Bus("12345678",
                        50,
                        90.0,
                        "Diesel");

        repo.add(bus);

        Bus updated =
                new Bus("12345678",
                        40,
                        90.0,
                        "Diesel");

        assertTrue(repo.update(updated));

        Bus retrieved =
                repo.retrieve("12345678");

        assertNotNull(retrieved);

        assertEquals(40,
                retrieved.getCapacity());
    }

    /**
     * BIT-05
     * Verify capacity increase is rejected.
     */
    @Test
    void testCapacityIncreaseRejected() {

        Bus bus =
                new Bus("12345678",
                        40,
                        75.0,
                        "Diesel");

        repo.add(bus);

        Bus updated =
                new Bus("12345678",
                        60,
                        75.0,
                        "Diesel");

        assertFalse(repo.update(updated));

        Bus retrieved =
                repo.retrieve("12345678");

        assertNotNull(retrieved);

        assertEquals(40,
                retrieved.getCapacity());
    }

    /**
     * BIT-06
     * Verify count() reflects repository changes.
     */
    @Test
    void testRecordCountUpdatedCorrectly() {

        assertEquals(0,
                repo.count());

        repo.add(
                new Bus(
                        "11111111",
                        40,
                        80.0,
                        "Diesel"));

        assertEquals(1,
                repo.count());

        repo.add(
                new Bus(
                        "22222222",
                        45,
                        85.0,
                        "Hybrid"));

        assertEquals(2,
                repo.count());
    }
}