package io.javalambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionIterationExample {

	public static void main(String[] args) {
		List<Person> people = Arrays.asList(
				new Person("Charles", "Dickends", 50),
				new Person("Lewis", "Carolls", 42),
				new Person("Thomas", "Carlyle", 51),
				new Person("Charlotte", "Bronte", 45),
				new Person("Matthew", "Arnold", 39));
		
		
		System.out.println("using for loop");
		for( int i=0; i<people.size(); i++) {
			System.out.println(people.get(i));
		}
		System.out.println("using for in loop");
		for(Person p: people) {
			System.out.println(p);
		}
		System.out.println("using lambda for each loop");
		people.forEach(System.out::println);
		

	}

}
