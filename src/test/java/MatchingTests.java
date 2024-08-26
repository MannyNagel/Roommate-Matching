import org.example.Matcher;
import org.example.Person;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class MatchingTests {

    @Test
    public void dataTest(){
        Matcher m = new Matcher("data1.csv");
        m.displayRequests();
    }

    @Test
    public void bruteForceTest1(){
        Matcher m = new Matcher("data1.csv");
        m.bruteForceSolution();
    }

    public static void main(String[] args){
        /*Person andy = new Person("andy");
        Person billy = new Person("billy");
        Person carl = new Person("carl");
        Person david = new Person("david");
        Person effie = new Person("effie");
        Person frank = new Person("frank");

        //andy.setRequests(new ArrayList<>(List.of(new Person[]{billy, carl})));
        //billy.setRequests(new ArrayList<>(List.of(new Person[]{david})));
        //carl.setRequests(new ArrayList<>(List.of(new Person[]{andy, billy})));
        //david.setRequests(new ArrayList<>(List.of(new Person[]{effie, frank})));
        //effie.setRequests(new ArrayList<>(List.of(new Person[]{david, frank})));
        //frank.setRequests(new ArrayList<>(List.of(new Person[]{david, billy})));

        List<Person> people = new ArrayList<>();
        people.add(billy);
        people.add(david);
        people.add(frank);
        people.add(carl);
        people.add(andy);
        people.add(effie);
        Matcher m = new Matcher(people, 2,3);
        System.out.println(m.maximizeRequests());
        System.out.println(m.displayRooms());
         */
    }

}
