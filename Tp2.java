package com.tactfactory.javaniveau2.tps.tp2;

import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.tactfactory.javaniveau2.tps.tp2.entities.Car;
import com.tactfactory.javaniveau2.tps.tp2.entities.User;
import com.tactfactory.javaniveau2.tps.tp2.utils.ItemsGenerator;

/**
 *  - Creer un programme qui permet, en utilisant la fonction "stream()" sur la variable users,
 *    d'afficher les informations suivantes :
 *    - 1 : Le nombre d'utilisateur
 *    - 2 : Le nombre d'utilisateur dans la premiere lettre du prenom commence par "a"
 *    - 3 : Le nombre d'utilisateur dont le nom et le prenom contienent exactement 5 caracteres
 *    - 4 : Le nombre d'utilisateur dont le full name contient entre 13 et 16 caracteres
 *    - 5 : Les utilisateurs possédant une voiture de la marque "marque1"
 *    - 6 : La première voiture avec le plus haut kilometrage
 *    - 7 : La liste des voitures avec le plus haut kilometrage
 *    - 8 : La marque possédant le plus de voiture avec le nombre de voiture possédé
 *    - 9 : Les marques possédant le moins de voiture avec chacune des voitures pour chaque marque
 *    - 10 : La liste des utilisateurs trié par nom puis par prenom
 */

public class Tp2 {

	public static void main(String[] args) {
		List<User> users = ItemsGenerator.generateUsers();

		// 1 : Le nombre d'utilisateur
		long nbUsers = users.stream().count();
		System.out.println("Nombre d'utilisateurs : " + nbUsers + "\n");

		// 2 : Le nombre d'utilisateur dans la première lettre du prénom commence par "a"
		Stream<User> stream = users.stream().filter(x -> x.getFirstname().startsWith("a"));
		long nbUsersA = stream.count();
		System.out.println("Nombres d'utilisateur dans la première lettre du prénom commence par \"a\" : " + nbUsersA + "\n");

		// 3 : Le nombre d'utilisateur dont le nom et le prénom contiennent exactement 5 caractères
		Stream<User> stream1 = users.stream()
				.filter(x -> x.getFirstname().length() == 5).filter(a -> a.getLastname().length() == 5);
		long nbUsersFLName5 = stream1.count();
		System.out.println("Les utilisateurs dont le nom et le prénom contienent exactement 5 caractères : " + nbUsersFLName5 + "\n");

		// 4 : Le nombre d'utilisateur dont le full name contient entre 13 et 16 caractères
		Stream<User> stream2 = users.stream().filter(x -> x.getFullName().length() >= 13).filter(y -> y.getFullName().length() <= 16);
		long nbUsersFullname1316 = stream2.count();
		System.out.println("Les utilisateurs dont le full name contient entre 13 et 16 caractères : " + nbUsersFullname1316 + "\n");

		// 5 : Les utilisateurs possédant une voiture de la marque "marque1"
		List<User> usersCarMarque1 = users.stream().filter(x -> x.getCar().getCarType().getMark().equals("marque1")).collect(Collectors.toList());
		System.out.println("Les utilisateurs possédant une voiture de la marque \"marque1\"");

		for (User user : usersCarMarque1) {
			System.out.println(user.getFirstname());
		}
		System.out.println();

		// 6 : La première voiture avec le plus haut kilométrage
		Car carMaxMileage = users.stream().collect(Collectors.maxBy(Comparator.comparing(x -> x.getCar().getMileage()))).get().getCar();
		System.out.println("La voiture avec le plus haut kilométrage : " + carMaxMileage.getCarType().getName() + "\n");

		// 7 : La liste des voitures avec le plus haut kilométrage
		List<Car> carMaxMileages = users.stream().filter(x -> x.getCar().getMileage() > 100000)
				.sorted(Comparator.comparing(x -> x.getCar().getMileage())).map(User::getCar)
				.collect(Collectors.toList());
		System.out.println("Les voiture avec le plus haut kilométrage : ");
		for (Car car : carMaxMileages) {
			System.out.println(car.getCarType().getName());
		}
		System.out.println("\n");

		// 8 : La marque possédant le plus de voiture avec le nombre de voiture possédé
		//    Entry<String, List<User>> markMaxCar = users.stream();
		//    System.out.println(String.format("%s est %s avec %d voitures", "La marque possédant le plus de voiture",
		//        markMaxCar.getKey(), markMaxCar.getValue().size()));

		// 9 : Les marques possédant le moins de voiture avec chacune des voitures pour chaque marque
		//    int minCars = users.stream();
		//    List<Entry<String, List<Car>>> markMinCars = users.stream();
		//    System.out.println("Les marques possédant le moins de voiture avec chacune des voitures pour chaque marque");
		//    for (Entry<String, List<Car>> entry : markMinCars) {
		//      System.out.println("\t" + entry.getKey() + " :");
		//      for (Car car : entry.getValue()) {
		//        System.out.println("\t\t" + car);
		//      }
		//    }

		// 10 : La liste des utilisateurs triée par nom puis par prénom
		//    List<User> sortedUsers = users.stream();
		//    System.out.println("La liste des utilisateurs triée par nom puis par prénom");
		//    for (User user : sortedUsers) {
		//      System.out.println("\t" + user);
		//    }
	}

}