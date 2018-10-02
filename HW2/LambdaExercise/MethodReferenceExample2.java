package io.javalambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class MethodReferenceExample2 {

	public static void main(String[] args) {
		List<Person> people = Arrays.asList(
				new Person("Charles", "Dickends", 50),
				new Person("Lewis", "Carolls", 42),
				new Person("Thomas", "Carlyle", 51),
				new Person("Charlotte", "Bronte", 45),
				new Person("Matthew", "Arnold", 39));
		
		//step 1: sort by last name in java 7
		Collections.sort(people, new Comparator<Person>() {
			public int compare(Person p1, Person p2) {
				return (p1.getLastName().compareTo(p2.getLastName()));
			};
		});
		performConditionally(people,p->true, System.out::println);
	}
	
	private static void performConditionally(List<Person> people, Predicate<Person> predicate, Consumer<Person> consumer) {
		System.out.println("print name with conditions");
		for(Person p : people) {
			if(predicate.test(p)) {
				consumer.accept(p);
			}
		}
	}
	
	public static void printMessage() {
		System.out.println("Hello");
	}
}
