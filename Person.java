import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Person {
    public List<Person> nameList;
    public String Name;
    public int distance;

    public Person(String name) {
        this.Name = name;
        this.nameList = new ArrayList<>();
        this.distance = -1; // Initialize distance to -1, indicating that the person is not connected to Alice
    }

    // Add a friend to the list
    public void addFriend(Person friend) {
        if (!nameList.contains(friend)) {
            nameList.add(friend);
            friend.distance = -1; // Set the distance to -1 for the new friend
        }
    }

    // To print the list of friends
    public void print() {
        System.out.print(Name + ": ");
        for (Person friend : nameList) {
            System.out.print(friend.Name + " ");
        }
        System.out.println();
    }

    // To print the distance to Alice (if connected)
    public void printWithDistance() {
        if (distance < 0) {
            System.out.println(Name + ": Not connected to Alice");
        } else {
            System.out.println(Name + ": Distance " + distance);
        }
    }
    
    // Reverse method using BFS to calculate distances from Alice
    public static void reverse(Person alice) {
        // Start BFS from Alice
        Queue<Person> queue = new LinkedList<>();
        alice.distance = 0;  // Alice is at distance 0 from herself
        queue.add(alice);
        
        while (!queue.isEmpty()) {
            Person current = queue.poll();
            
            // For each friend of the current person, update distance if not already set
            for (Person friend : current.nameList) {
                if (friend.distance == -1) { // If distance has not been set
                    friend.distance = current.distance + 1; // Set distance to one more than current person's distance
                    queue.add(friend); // Add friend to the queue to continue BFS
                }
            }
        }
    }
}

public class SocialNetwork {
    public static void main(String[] args) {
        Person alice = new Person("Alice");
        Person bob = new Person("Bob");
        Person charlie = new Person("Charlie");
        Person dave = new Person("Dave");
        
        // Add friends (e.g., Alice -> Bob, Bob -> Charlie)
        alice.addFriend(bob);
        bob.addFriend(charlie);
        charlie.addFriend(dave);
        
        // Call reverse to calculate the distance of each person from Alice
        Person.reverse(alice);
        
        // Print the distances from Alice
        alice.printWithDistance();
        bob.printWithDistance();
        charlie.printWithDistance();
        dave.printWithDistance();
    }
}
