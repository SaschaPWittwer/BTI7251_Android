package androidcrew.bti7251_android.lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DataProvider {

    //todo Singleton

    List<Person> people;

    List<String> surnames;
    List<String> names;
    List<String> gender;

    public DataProvider(int numberofEntries) {
        surnames = Arrays.asList("Tobi", "Joris", "Boris", "Cédric", "Lukas", "Sascha");
        names = Arrays.asList("Djurdeirgendöppis", "Wittwer", "Joder", "Grey", "von Allmen", "Baiutti", "Läderach");
        gender = Arrays.asList("Male", "Female", "both", "not sure", "man titties");

        people = new ArrayList<>();
        for(int i = 0; i < numberofEntries; i++){
            people.add(createRandomPerson(i));
        }
    }

    public Person createRandomPerson(int id){
        Random rand = new Random();
        return new Person(
                names.get(rand.nextInt(names.size()-1)),
                surnames.get(rand.nextInt(surnames.size()-1)),
                rand.nextInt(70),
                gender.get(rand.nextInt(gender.size()-1)),
                id);
    }

    public List<Person> getPeople() {
        return people;
    }
}
