package com.github.erf88;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Scanner;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class App {

	private static Scanner reader;
	private static MongoCollection<Document> collection;
	
	public static void main(String[] args) {
		
		MongoDatabase connection = new Connection().getConnection();
		collection = connection.getCollection("customer");
		
		reader = new Scanner(System.in);
		Integer op = 0;
		do {
			System.out.println("Choose option below");
			System.out.println("----------------------");
			System.out.println("1 - FindAll Customer");
			System.out.println("2 - FindOne Customer");
			System.out.println("3 - Create Customer");
			System.out.println("4 - Update Customer");
			System.out.println("5 - Delete Customer");
			System.out.println("0 - Exit");
			System.out.println("----------------------");

			op = reader.nextInt();
			reader.nextLine();			

			switch (op) {
			case 1:
				
				findAll();
				break;
			case 2:
				findOne();
				break;
			case 3:
				create();
				break;
			case 4:
				update();	
				break;
			case 5:
				delete();							
				break;

			default:
				break;
			}
			
		} while (op != 0);

	}
	
	public static void findAll() {		
		collection.find().map(document -> new Customer(document)).into(new ArrayList<Customer>()).forEach(System.out::println);		
	}
	
	public static void findOne() {
		System.out.println("Enter customer id: ");
		String id = reader.next();
		Customer customer = null;
		try {
			customer = collection.find(eq("_id", new ObjectId(id))).map(document -> new Customer(document)).first();
			System.out.println(customer);
		} catch (Exception e) {
			System.out.println("Customer not found.");
		}
		
	}

	public static void create() {
		System.out.println("Enter customer name: ");
		String name = reader.next();
		System.out.println("Enter customer email: ");
		String email = reader.next();			
		Document document = new Document();
		document.append("name", name);
		document.append("email", email);		
		collection.insertOne(document);
	}

	public static void update() {
		System.out.println("Enter customer id: ");
		String id = reader.next();
		try {
			collection.find(eq("_id", new ObjectId(id))).map(document -> new Customer(document)).first();
			System.out.println("Enter new customer name: ");
			String name = reader.next();
			System.out.println("Enter new customer email: ");
			Document document = new Document();
			document.append("name", name);
			String email = reader.next();
			document.append("email", email);	
			collection.updateOne(eq("_id", new ObjectId(id)), new Document("$set", document));
		} catch (Exception e) {
			System.out.println("Customer not found.");
		}

	}

	public static void delete() {
		System.out.println("Enter customer id: ");
		String id = reader.next();
		try {
			collection.find(eq("_id", new ObjectId(id))).map(document -> new Customer(document)).first();
			collection.deleteOne(eq("_id", new ObjectId(id)));
		} catch (Exception e) {
			System.out.println("Customer not found.");
		}
		
	}

}
