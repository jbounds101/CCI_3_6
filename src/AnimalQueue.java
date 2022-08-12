import java.util.LinkedList;

public class AnimalQueue {
    int order = 0;
    LinkedList<Animal> cats = new LinkedList<>();
    LinkedList<Animal> dogs = new LinkedList<>();

    public void enqueue(Animal a) {
        if (a instanceof Cat) {
            cats.add(a);
        }
        if (a instanceof Dog) {
            dogs.add(a);
        }
        a.setOrder(++order);
    }

    public Animal dequeueAny() {
        if (cats.isEmpty()) {
            return dequeueDog();
        }
        if (dogs.isEmpty()) {
            return dequeueCat();
        }

        if (cats.peekFirst().getOrder() < dogs.peekFirst().getOrder()) {
            return cats.removeFirst();
        } else return dogs.removeFirst();
    }

    public Animal dequeueCat() {
        return cats.removeFirst();
    }
    public Animal dequeueDog() {
        return dogs.removeFirst();
    }

    public static void main(String[] args) {
        AnimalQueue q = new AnimalQueue();
        q.enqueue(new Dog("Woofer"));
        q.enqueue(new Cat("Whiskers"));
        q.enqueue(new Cat("Jigsaw"));
        q.enqueue(new Dog("Pickle"));
        System.out.println(q.dequeueDog());
    }

}

abstract class Animal { // Abstract cannot be used to make objects
    String name;
    int order;
    public Animal(String name) {
        this.name = name;
    }
    public void setOrder(int order) {
        this.order = order;
    }
    public int getOrder() {
        return order;
    }
    public boolean isOlderThan(Animal a) {
        return this.order < a.getOrder();
    }

    @Override
    public String toString() {
        return this.name;
    }
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
}

class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }
}
