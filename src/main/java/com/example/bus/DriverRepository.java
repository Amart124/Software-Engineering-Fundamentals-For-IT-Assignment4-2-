package com.example.bus;

import java.io.*;
import java.util.*;

public class DriverRepository {

    private final String filename;
    private final Map<String, Driver> drivers = new HashMap<>();

    public DriverRepository(String filename) {

        this.filename = filename;
        load();
    }

    public boolean add(Driver driver) {

        if (!ValidationUtils.validDriverID(driver.getDriverID()))
            return false;

        if (!ValidationUtils.validAddress(driver.getAddress()))
            return false;

        if (!ValidationUtils.validBirthdate(driver.getBirthdate()))
            return false;

        if (drivers.containsKey(driver.getDriverID()))
            return false;

        drivers.put(driver.getDriverID(), driver);

        save();

        return true;
    }

    public Driver retrieve(String id) {

        return drivers.get(id);
    }

    public int count() {

        return drivers.size();
    }

    public boolean update(Driver updated) {

        Driver existing = drivers.get(updated.getDriverID());

        if (existing == null)
            return false;

        // D4
        if (existing.getExperienceYears() > 10 &&
                !existing.getLicenseType().equals(updated.getLicenseType()))
            return false;

        // D5
        if (!existing.getName().equals(updated.getName()))
            return false;

        drivers.put(updated.getDriverID(), updated);

        save();

        return true;
    }

    private void save() {

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {

            for (Driver d : drivers.values()) {

                writer.println(
                        d.getDriverID() + "," +
                        d.getName() + "," +
                        d.getExperienceYears() + "," +
                        d.getLicenseType() + "," +
                        d.getAddress() + "," +
                        d.getBirthdate()
                );
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private void load() {

        File file = new File(filename);

        if (!file.exists())
            return;

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(filename))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                Driver d = new Driver(
                        parts[0],
                        parts[1],
                        Integer.parseInt(parts[2]),
                        parts[3],
                        parts[4],
                        parts[5]
                );

                drivers.put(d.getDriverID(), d);
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}