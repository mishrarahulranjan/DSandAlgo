package designpatterns.structural;

public class Person {

    private String name;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }

    public static class PersonBuilder{

        public static Person build(String name){
            Person p = new Person();
            p.name=name;
            return p;
        }

    }
}
