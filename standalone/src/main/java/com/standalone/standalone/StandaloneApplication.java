package com.standalone.standalone;

import com.standalone.standalone.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class StandaloneApplication implements CommandLineRunner {

	@Autowired
	private ProductService productService;

	@Autowired
	private DbCreateInterface dbcreate;



	private final static String DB_PATH ="src/main/resources/tescoDB_02";

	public static void main(String[] args) {
		SpringApplication.run(StandaloneApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
/*
		String string1 = "0,00%";
		String string2 = "10%";
		String string3 = "VALAMI10";
		String string4 = "10,10%";
		String string5 = "asdasdasd 10%";
		List<String> stringList = new ArrayList<>();
		stringList.add(string1);
		stringList.add(string2);
		stringList.add(string3);
		stringList.add(string4);
		stringList.add(string5);
		for (String string: stringList) {
			System.out.println(string+"\t"+string.replaceAll("[0-9]?[0-9]?,?[0-9]?[0-9]?%","")+"\n");
		}

 */


		dbcreate.create(DB_PATH);
		//productService.initDb("src/main/resources/tescoDB_02");



	}



}
