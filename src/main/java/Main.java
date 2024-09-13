import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long countMinors = persons.stream().filter(x -> x.getAge() < 18)
                .count();

        List<String> surnameConscripts = persons.stream().filter(x -> x.getSex().equals(Sex.MAN) &&
                        x.getAge() > 17 && x.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());

        List<Person> listWorker = persons.stream().filter(x -> x.getEducation().equals(Education.HIGHER))
                .filter(x -> x.getAge() > 17)
                .filter(x -> (x.getSex().equals(Sex.WOMAN) && x.getAge() < 60) ||
                        (x.getSex().equals(Sex.MAN) && x.getAge() < 65))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

    }
}
