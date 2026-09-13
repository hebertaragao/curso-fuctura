package br.com.fuctura.infrastructura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConnectionFactory {
	
	private static EntityManagerFactory entityManagerFactory; 

	public Connection getConnection() {
		Properties props = new DatabaseConfig().loadProperties();

		try {
			return DriverManager.getConnection(props.getProperty("url"), props);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static EntityManagerFactory getEntityManagerFactory() {
		if(entityManagerFactory == null 
				|| !entityManagerFactory.isOpen()) 
		{
			//abrir xml
			//leitura das credencias de banco
			//leitura estrategia
			// se create-drop => apaga tudo e cria do zero
			// abre a conexao
			System.out.println("Abrindo o PU");
			entityManagerFactory = Persistence
				.createEntityManagerFactory("meuPU");
		}
		
		return entityManagerFactory;
	}
	
}
