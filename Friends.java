import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
/* I worked on the code myself */
public class Friends {
    public static final String filename = "smallNetwork"; // name of the file to read from
    Map<String, Person> friendsMap;

    public Friends(Scanner input) {
        friendsMap = new HashMap<>(); // to look up a person with a given name
        while (input.hasNext()) { // while there is more input
            String name1 = input.next();
            String name2 = input.next();
            Person person1 = createPerson(name1);
            Person person2 = createPerson(name2);
            person1.addFriend(person2);
            person2.addFriend(person1);
        }
    }

    private Person createPerson(String Name) {
        if (!friendsMap.containsKey(Name)) {
            Person person = new Person(Name);
            friendsMap.put(Name, person);
        }
        return friendsMap.get(Name);
    }

    public void summarize() { // method to print the number of people seen
        Iterator<String> keyIterator = friendsMap.keySet().iterator();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            Person p = friendsMap.get(key);
            p.print();
        }
    }

    public static void main(String[] args) {
        Scanner input = null;
        try {
            input = new Scanner(new File(filename));
        } catch (FileNotFoundException ex) {
            System.err.println("Unable to find input file: " + filename);
            System.exit(1);
        }

        Friends f = new Friends(input);
        f.summarize();

        Queue<Person> queue = new LinkedList<>();
        Person alice = f.friendsMap.get("Alice");
        alice.distance = 0;
        queue.offer(alice);//use the offer method to add

        while (!queue.isEmpty()) {
            Person currentPerson = queue.poll();
            int size = currentPerson.nameList.size();

            for (int i = 0; i < size; i++) {
                Person friendPerson = currentPerson.nameList.get(i);

                if (friendPerson.distance==-1) {
                    friendPerson.distance = currentPerson.distance + 1;
                    queue.add(friendPerson);
                }
            }

            currentPerson.printWithDistance();
        }
    }
}
