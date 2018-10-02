package io.javalambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Unit1Exercise {

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
		
		//print all ppl from list
		printALL(people);
		
		
		//print last name begin with C
		printNameBeginWithC(people);
		
		//print conditionnaly
		printConditionally(people, new Condition() {
			
			@Override
			public boolean test(Person p) {
				return p.getLastName().startsWith("C");
			}
		});
		printConditionally(people, new Condition() {
			
			@Override
			public boolean test(Person p) {
				return p.getFirstName().startsWith("C");
			}
		});
	}
	private static void printConditionally(List<Person> people, Condition condition) {
		System.out.println("print name with conditions");
		for(Person p : people) {
			if(condition.test(p)) {
				System.out.println(p);
			}
		}
	}
	
	private static void printALL(List<Person> people) {
		for(Person p : people) {
			System.out.println(p);
		}
	}
	private static void printNameBeginWithC(List<Person> people) {
		System.out.println("print name starts with C");
		for(Person p : people) {
			if(p.getLastName().startsWith("C")) {
				System.out.println(p);
			}
		}
	}
}
interface Condition{
	boolean  test(Person p);
}
