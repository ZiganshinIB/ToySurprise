package com.example.toysurprise;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.example.toysurprise.Toy;

public class ToyStore {
    private List<Toy> toys = new ArrayList<>();
    private Random random = new Random();

    public void addToy(int id, String name, int quantity, double weight) {
        toys.add(new Toy(id, name, quantity, weight));
    }

    public void changeWeight(int id, double weight) {
        for (Toy toy : toys) {
            if (toy.getId() == id) {
                toy.setWeight(weight);
                break;
            }
        }
    }

    public void play() {
        double totalWeight = toys.stream().mapToDouble(Toy::getWeight).sum();
        double randomValue = random.nextDouble() * totalWeight;

        for (Toy toy : toys) {
            randomValue -= toy.getWeight();
            if (randomValue <= 0) {
                if (toy.getQuantity() > 0) {
                    System.out.println("Congratulations! You've won a " + toy.getName());
                    toy.quantity--;
                } else {
                    System.out.println("Sorry, this toy is out of stock.");
                }
                break;
            }
        }
    }

    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();

        toyStore.addToy(1, "Teddy bear", 5, 20);
        toyStore.addToy(2, "Doll", 3, 10);
        toyStore.addToy(3, "Toy car", 8, 30);

        toyStore.play();
        toyStore.play();
        toyStore.play();
    }
}