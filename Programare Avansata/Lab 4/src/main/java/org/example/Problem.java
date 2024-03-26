package org.example;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Problem {
    protected ArrayList<Person> randomGroup = new ArrayList<>(); // Lista pentru grupul aleatoriu de persoane
    protected List<Driver> filteredDrivers = new LinkedList<>(); // Lista pentru soferi filtrata
    protected Set<Passenger> filteredPassengers; // Set pentru pasageri filtrati
    protected List<Destination> destinations = new LinkedList<>(); // Lista destinatiilor
    protected Map<Destination, LinkedList<Person>> destinationMap = new HashMap<>(); // Mapare pentru destinatii si persoane

    /**
     * Metoda pentru generarea unui grup aleatoriu de persoane (soferi si pasageri).
     */
    public void generateRandomGroup(int howMany) {
        Random rand = new Random();

        for (int i = 0; i < howMany; i++) {
            int choice = rand.nextInt(2); // Alegerea intre 0 si 1 pentru a decide tipul de persoana
            int age = rand.nextInt(90); // Generarea unei varste aleatorii
            Faker faker = new Faker(); // Folosirea bibliotecii Faker pentru a genera date fictive

            // Crearea unei persoane si setarea atributelor ei in functie de alegere
            if (choice == 0) {
                Person A = new Passenger();
                A.setAge(age);
                A.setName(faker.name().fullName());
                randomGroup.add(A);
            } else {
                Person A = new Driver();
                int driverAge = 18 + rand.nextInt(72); // Generarea unei varste aleatorii pentru soferi
                A.setAge(driverAge);
                A.setName(faker.funnyName().name());
                randomGroup.add(A);
            }
        }

        // Filtrarea soferilor si pasagerilor din grupul aleatoriu
        List<Passenger> partialFiltered = new LinkedList<>();
        filteredDrivers = randomGroup.stream().filter(A -> A instanceof Driver).map(c -> (Driver) c).collect(Collectors.toList());
        partialFiltered = randomGroup.stream().filter(A -> A instanceof Passenger).map(c -> (Passenger) c).collect(Collectors.toList());
        filteredPassengers = new TreeSet<>(partialFiltered); // Folosirea unui TreeSet pentru a sorta pasagerii

        // Sortarea soferilor dupa varsta
        filteredDrivers.sort(new Comparator<Driver>() {
            @Override
            public int compare(Driver o1, Driver o2) {
                return Integer.compare(o1.getAge(), o2.getAge());
            }
        });
    }

    /**
     * Metoda pentru afisarea soferilor si pasagerilor.
     */
    public void displayDriversAndPassengers() {
        System.out.println("Drivers:");
        filteredDrivers.forEach(System.out::println);
        System.out.println();

        System.out.println("Passengers:");
        filteredPassengers.forEach(System.out::println);
    }

    /**
     * Metoda pentru generarea destinatiilor.
     */
    public void generateDestinations(int howMany) {
        Faker faker = new Faker();

        // Generarea destinatiilor folosind biblioteca Faker
        for (int i = 1; i <= howMany; i++) {
            String name = faker.address().fullAddress();
            Destination A = new Destination(name);
            destinations.add(A);
        }

        // Atribuirea aleatorie a destinatiilor pentru soferi si pasageri
        filteredDrivers.forEach(A -> A.setDestination(destinations.get((new Random()).nextInt(0, destinations.size() - 1))));
        filteredPassengers.forEach(A -> A.setDestination(destinations.get((new Random()).nextInt(0, destinations.size() - 1))));
    }

    /**
     * Metoda pentru generarea maparii intre destinatii si persoane.
     */
    public void generateDestinationMap() {
        destinations.forEach(destination -> {
            // Filtrarea pasagerilor si soferilor pentru fiecare destinatie si colectarea lor intr-o lista legata
            LinkedList<Person> persons = Stream.concat(
                            filteredPassengers.stream()
                                    .filter(passenger -> passenger.getDestination().equals(destination)),
                            filteredDrivers.stream()
                                    .filter(driver -> driver.getDestination().equals(destination)))
                    .collect(Collectors.toCollection(LinkedList::new));
            destinationMap.put(destination, persons); // Adaugarea listei de persoane in mapare
        });
    }

    /**
     * Metoda pentru afisarea maparii destinatiilor si persoanelor asociate lor.
     */
    public void printDestinationMap() {
        destinationMap.forEach((destination, persons) -> {
            System.out.println("Destination: " + destination.getName());
            System.out.println("Persons:");
            for (Person person : persons) {
                System.out.println(person);
            }
            System.out.println();
        });
    }

    /**
     * Metoda pentru asignarea pasagerilor la soferi in functie de destinatie.
     */
    public void assignPassengersToDrivers() {
        filteredDrivers.forEach(driver -> {
            Destination destination = driver.getDestination();
            Optional<Passenger> optionalPassenger = filteredPassengers.stream()
                    .filter(passenger -> passenger.getDestination().equals(destination))
                    .findFirst();

            // Daca exista un pasager disponibil pentru aceeasi destinatie, este asignat soferului
            if (optionalPassenger.isPresent()) {
                Passenger passenger = optionalPassenger.get();
                driver.setPassenger(passenger);
                destinationMap.get(destination).remove(passenger); // Eliminarea pasagerului din lista de persoane asociate destinatiei
                destinationMap.get(destination).remove(driver); // Eliminarea soferului din lista de persoane asociate destinatiei
            }
        });
    }

    /**
     * Metoda pentru afisarea soferilor si pasagerilor asignati.
     */
    public void printDriverPassengers() {
        for (Driver A : filteredDrivers) {
            if (A.getPassenger() != null) {
                System.out.println(A.getName() + " picked " + A.getPassenger().getName() + " as his passenger, going towards " + A.getDestination().getName());
            }
        }
        System.out.println();
    }
}