package winecellar;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static String urlPath = "jdbc:postgresql://baasu.db.elephantsql.com:5432/twoxihzh";
	private static String userName = "twoxihzh";
	private static String password = "blF6ljpqG9j0JLOK1rruFLXxPpPSqE3y";

	private static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {

		String menuChoice = "";
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(urlPath, userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println();
		System.out.println("Bonjour et bienvenue dans l'application de gestion de cave");
		do {

			System.out.println("1) Ajouter un nouveau vin en base de données");
			System.out.println("2) Supprimer un vin de la base de la cave");
			System.out.println("3) Modifier un vin de la base de données"); // non réalisé
			System.out.println("4) Modifier le stock d'un vin de la cave");
			System.out.println("0) Terminer le programme");

			menuChoice = in.nextLine();

			switch (menuChoice) {
			case "1":
				System.out.println("Vous avez choisi :::> Ajouter un nouveau vin");
				addWine();
				break;

			case "2":
				System.out.println("Vous avez choisi :::> Supprimer des bouteilles de vin de la cave");
				supprCellarEntry(conn);
				break;

			case "3":
				// other function
				break;
			case "4":
				// other function
				break;

			case "0":
				System.out.println("Vous avez terminé le programme, merci de le relancer pour de nouvelles saisies.");
				break;

			default:
				System.out.println("Commande inconnue : Saisissez votre choix à nouveau !");

			}
		} while (!menuChoice.equals("0"));

		in.close();

	}

	public static void addWine() {
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(urlPath, userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Indiquez le nom du vin");
		String wineName = in.nextLine();
		System.out.println("Indiquez le pays d'origine");
		String wineCountry = in.nextLine();
		System.out.println("Indiquez la région d'origine (bordeaux/bourgogne/californie...)");
		String wineCounty = in.nextLine();
		System.out.println("Indiquez la classification (saint émilion/loupiac/médoc...)");
		String wineType = in.nextLine();
		System.out.println("Indiquez la couleur (rouge/blanc/liquoreux/rosé...)");
		String wineColor = in.nextLine();
		System.out.println("Indiquez l'année du vin");
		int wineYear = in.nextInt();
		in.nextLine();
		System.out.println("Indiquez le prix d'achat (ex : 15,60)");
		double winePrice = in.nextDouble();
		in.nextLine();

		Wine newWine = new Wine(wineName, wineCountry, wineCounty, wineType, wineColor, wineYear, winePrice);

		newEntryWine(conn, newWine);
		System.out.println("Votre saisie est réalisée");

	}

	public static void newEntryWine(Connection connect, Wine newWine) {

		try {
			PreparedStatement createNewWine = connect
					.prepareStatement(
							"INSERT INTO wine  VALUES ( Default,'" + newWine.getName() + "' ,'" + newWine.getCountry()
									+ "' ,'" + newWine.getCounty() + "' ,'" + newWine.getType() + "' ,'"
									+ newWine.getColor() + "' ," + newWine.getYear() + " ," + newWine.getPrice() + ");",
							Statement.RETURN_GENERATED_KEYS);

			createNewWine.execute();

			ResultSet key = createNewWine.getGeneratedKeys();
			while (key.next()) {
				newWine.setWineId(key.getLong("wine_id"));
				System.out.println(newWine);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(urlPath, userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String answer;
		System.out.println("Souhaitez vous ajouter ce vin à votre cave ?");
		answer = in.nextLine();

		if (answer.equalsIgnoreCase("oui")) {
			int bottleNb;
			System.out.println("Combien de bouteilles souhaitez vous ajouter ?");
			bottleNb = in.nextInt();
			in.nextLine();
			newEntryCellar(conn, newWine, bottleNb);
		} else if (answer.equalsIgnoreCase("non")) {
			System.out.println("Vous n'avez pas souhaité entrer ce vin en cave");
		} else {
			System.out.println("la réponse n'est pas correcte, aucun vin n'a été ajouté en cave");
		}

	}

	public static void newEntryCellar(Connection connect, Wine newWine, int bottleNb) {

		try {
			PreparedStatement insertNewCellarwine = connect.prepareStatement(
					"INSERT INTO cellar VALUES (default, '" + newWine.getWineId() + "' ," + bottleNb + ");");
			insertNewCellarwine.execute();
			System.out.println("vous venez d'entrer " + bottleNb + " bouteilles de " + newWine.getName() + " en cave");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void supprCellarEntry(Connection connect) {
		System.out.println("Lister les vin contenant");
		String choiceWine = in.nextLine();

		try {
			PreparedStatement listChoice = connect
					.prepareStatement("SELECT * FROM wine WHERE name LIKE '%" + choiceWine + "%';");
			ResultSet choice = listChoice.executeQuery(); // SELECT * FROM wine WHERE name LIKE

			ArrayList<Wine> wineList = new ArrayList<Wine>();
			while (choice.next()) {
				Wine searchedWines = new Wine(choice.getLong("wine_id"), choice.getString("name"),
						choice.getString("country"), choice.getString("county"), choice.getString("type"),
						choice.getString("color"), choice.getInt("year"), choice.getDouble("price"));

				PreparedStatement listCellar = connect
						.prepareStatement("SELECT * FROM cellar WHERE wine_id  =" + choice.getLong("wine_id") + ";");
				ResultSet choiceCellar = listCellar.executeQuery(); // SELECT * FROM wine WHERE name LIKE)
				ArrayList<Cellar> cellarList = new ArrayList<Cellar>();

				while (choiceCellar.next()) {
					Cellar searchedCellar = new Cellar(choiceCellar.getLong("cellar_id"),
							choiceCellar.getLong("wine_id"), choiceCellar.getInt("bottle_nb"));
					cellarList.add(searchedCellar);

				}
				if (cellarList.size() > 0) {
					for (int i = 0; i < cellarList.size(); i++) {
						System.out.println(cellarList.get(i));
						wineList.add(searchedWines);
					}
				}

			}
			if (wineList.size() > 0) {
				for (int i = 0; i < wineList.size(); i++) {
					System.out.println(wineList.get(i));
				}

				System.out.println("Indiquez le wine_id du vin à supprimer dans la liste ci dessus");
				int choiceCellarWinetoSuppr = in.nextInt();
				in.nextLine();
				System.out.println("Indiquez le nombre de bouteilles du vin à supprimer dans la liste ci dessus");
				int bottleNbCellarToSuppr = in.nextInt();
				in.nextLine();

				PreparedStatement cellarEntryToModify = connect
						.prepareStatement("UPDATE cellar SET bottle_nb = (bottle_nb - " + bottleNbCellarToSuppr
								+ ") WHERE wine_id = " + choiceCellarWinetoSuppr + ";");
				cellarEntryToModify.execute();

			} else {
				System.out.println("Il n'y a pas de vin de ce nom " + choiceWine);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
