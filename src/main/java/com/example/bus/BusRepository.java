package com.example.bus;

import java.io.*;
import java.util.*;

public class BusRepository {

    private final String filename;
    private final Map<String, Bus> buses = new HashMap<>();

    public BusRepository(String filename) {

        this.filename = filename;
        load();
    }

    public boolean add(Bus bus) {

        if (!ValidationUtils.validBusID(bus.getBusID()))
            return false;

        if (buses.containsKey(bus.getBusID()))
            return false;

        buses.put(bus.getBusID(), bus);

        save();

        return true;
    }

    public Bus retrieve(String id) {

        return buses.get(id);
    }

    public int count() {

        return buses.size();
    }

    public boolean update(Bus updated) {

        Bus existing = buses.get(updated.getBusID());

        if (existing == null)
            return false;

        // B2
        if (updated.getCapacity() > existing.getCapacity())
            return false;

        buses.put(updated.getBusID(), updated);

        save();

        return true;
    }

    public boolean assignDriver(String busID,
                                String driverID) {

        return true;
    }

    private void save() {

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {

            for (Bus b : buses.values()) {

                writer.println(
                        b.getBusID() + "," +
                        b.getCapacity() + "," +
                        b.getFuelLevel() + "," +
                        b.getFuelType()
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

                Bus b = new Bus(
                        parts[0],
                        Integer.parseInt(parts[1]),
                        Double.parseDouble(parts[2]),
                        parts[3]
                );

                buses.put(b.getBusID(), b);
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}