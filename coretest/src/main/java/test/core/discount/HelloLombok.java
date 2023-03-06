package test.core.discount;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String Name;
    private int age;

    public static void main(String[] args) {

        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("testName");

        String name = helloLombok.getName();
        System.out.println("name = " + name);

    }

}
