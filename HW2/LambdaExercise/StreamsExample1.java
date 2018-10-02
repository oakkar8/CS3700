package io.javalambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamsExample1 {

	public static void main(String[] args ) {
		List<Person> people = Arrays.asList(
				new Person("Charles", "Dickends", 50),
				new Person("Lewis", "Carolls", 42),
				new Person("Thomas", "Carlyle", 51),
				new Person("Charlotte", "Bronte", 45),
				new Person("Matthew", "Arnold", 39));
		people.parallelStream()
//		.filter(p-> p.getLastName().startsWith("C"))
		.forEach(p->System.out.println(p.getFirstName()));
		
		long count = people.stream()
		.filter(p -> p.getLastName().startsWith("D"))
		.count();
		
		System.out.println(count);
	
	}
}
