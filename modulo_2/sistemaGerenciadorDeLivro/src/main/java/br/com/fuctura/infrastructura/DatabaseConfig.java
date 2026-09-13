package br.com.fuctura.infrastructura;

import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {

	private static final String FILE_NAME = "application.properties";

	public Properties loadProperties() {
		Properties props = new Properties();

		try (InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream(FILE_NAME)) {

			if (input == null) {
				throw new RuntimeException("Arquivo db.properties não encontrado");
			}

			props.load(input);

		} catch (Exception e) {
			throw new RuntimeException("Erro ao carregar propriedades", e);
		}

		return props;

	}

}
