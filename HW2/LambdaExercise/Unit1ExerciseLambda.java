package io.javalambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Unit1ExerciseLambda {

public static void main(String[] args) {
		
		List<Person> people = Arrays.asList(
				new Person("Charles", "Dickends", 50),
				new Person("Lewis", "Carolls", 42),
				new Person("Thomas", "Carlyle", 51),
				new Person("Charlotte", "Bronte", 45),
				new Person("Matthew", "Arnold", 39));
		
		//step 1: sort by last name in java 7
		
		Collections.sort(people,(p1,p2) -> p1.getLastName().compareTo(p2.getLastName()));
		
		//print all ppl from list
		printConditionally(people,p->true);
		
		
		//print last name begin with C
		printConditionally(people, p->p.getLastName().startsWith("C"));
		
		//print first name begin with C
		printConditionally(people, p->p.getFirstName().startsWith("C"));
		//print all ppl from list
		performConditionally(people,p->true, p->System.out.println(p));
		
		
		//print last name begin with C
		performConditionally(people, p->p.getLastName().startsWith("C"), p->System.out.println(p.getFirstName()));
		
		//print first name begin with C
		performConditionally(people, p->p.getFirstName().startsWith("C"), p->System.out.println(p.getFirstName()));
		
		
	}
	
	
	private static void printConditionally(List<Person> people, Predicate<Person> predicate) {
		System.out.println("print name with conditions");
		for(Person p : people) {
			if(predicate.test(p)) {
				System.out.println(p);
			}
		}
	}
	private static void performConditionally(List<Person> people, Predicate<Person> predicate, Consumer<Person> consumer) {
		System.out.println("print name with conditions");
		for(Person p : people) {
			if(predicate.test(p)) {
				consumer.accept(p);
			}
		}
	}

}
