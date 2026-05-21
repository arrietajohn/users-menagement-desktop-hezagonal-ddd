package com.rcarmona.censo;

import com.rcarmona.censo.infrastructure.config.CensoDependencyContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

/**
 * Punto de entrada exclusivo para el mÃ³dulo de Censo (Municipio).
 * Permite iniciar la base de datos automÃ¡ticamente mediante Docker Compose
 * o conectar al MySQL local validado nativamente.
 * 
 * @author Rosary Carmona
 */
/**
 * ==========================================================================================
 * AUTOR: Rosary Carmona (rcarmona)
 * ==========================================================================================
 * EXPLICACIÓN ESTILO TUTORIAL (LENGUAJE UBICUO - DDD):
 * 
 * Función Específica de este Archivo:
 * - Punto de entrada principal (Bootstrapper) de la aplicación Censo. Inicializa el contenedor de dependencias, solicita credenciales de base de datos interactivamente al usuario, valida la conexión y lanza el menú principal.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: MainCenso
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public final class MainCenso {

  private static final Logger log = LoggerFactory.getLogger(MainCenso.class);

  public static void main(final String[] args) {
    log.info("Iniciando Sistema de GestiÃ³n de Censo (Municipios)...");
    
    // Bucle interactivo para solicitar credenciales y verificar conexiÃ³n
    promptAndSaveCredentials();

    final CensoDependencyContainer container = new CensoDependencyContainer();
    container.municipioController().displayMenu();
  }

  private static void promptAndSaveCredentials() {
      @SuppressWarnings("resource")
      Scanner scanner = new Scanner(System.in);
      boolean connected = false;
      
      while (!connected) {
          System.out.println("\n=================================================");
          System.out.println("CONFIGURACIÃ“N DE BASE DE DATOS");
          System.out.println("=================================================");
          
          System.out.print("Ingrese el motor de BD (mysql/postgresql) [mysql]: ");
          String engine = scanner.nextLine().trim().toLowerCase();
          if (engine.isEmpty()) engine = "mysql";
          
          System.out.print("Ingrese el Host [localhost]: ");
          String host = scanner.nextLine().trim();
          if (host.isEmpty()) host = "localhost";
          
          System.out.print("Ingrese el Puerto [" + (engine.equals("postgresql") ? "5432" : "3306") + "]: ");
          String port = scanner.nextLine().trim();
          if (port.isEmpty()) port = engine.equals("postgresql") ? "5432" : "3306";
          
          System.out.print("Ingrese el usuario de la BD [root/postgres]: ");
          String user = scanner.nextLine().trim();
          if (user.isEmpty()) user = engine.equals("postgresql") ? "postgres" : "root";
          
          System.out.print("Ingrese la contraseÃ±a: ");
          String pass = scanner.nextLine().trim();

          // Try to connect to the server (without specific DB) to verify credentials
          String jdbcUrl = "";
          if (engine.equals("postgresql")) {
              jdbcUrl = "jdbc:postgresql://" + host + ":" + port + "/postgres";
          } else {
              jdbcUrl = "jdbc:mysql://" + host + ":" + port + "/?serverTimezone=UTC";
          }
          
          try (java.sql.Connection conn = java.sql.DriverManager.getConnection(jdbcUrl, user, pass)) {
              log.info("Â¡ConexiÃ³n exitosa al servidor " + engine + "!");
              connected = true;
              
              // Save to properties file
              File propFile = new File("src/main/resources/censo_application.properties");
              Properties props = new Properties();
              if (propFile.exists()) {
                  try (java.io.FileInputStream fis = new java.io.FileInputStream(propFile)) {
                      props.load(fis);
                  }
              }
              props.setProperty("db.engine", engine);
              props.setProperty("db.username", user);
              props.setProperty("db.password", pass);
              props.setProperty("db.host", host);
              props.setProperty("db.port", port);
              props.setProperty("db.name", "7502523003_9_censo_nacional");
              
              if (!propFile.getParentFile().exists()) propFile.getParentFile().mkdirs();
              try (FileOutputStream fos = new FileOutputStream(propFile)) {
                  props.store(fos, "ConfiguraciÃ³n actualizada automÃ¡ticamente por MainCenso");
              }
              
              // Set system properties
              System.setProperty("censo.db.engine", engine);
              System.setProperty("censo.db.username", user);
              System.setProperty("censo.db.password", pass);
              System.setProperty("censo.db.host", host);
              System.setProperty("censo.db.port", port);
              System.setProperty("censo.db.name", "7502523003_9_censo_nacional");

          } catch (Exception e) {
              log.error("Error de conexiÃ³n: " + e.getMessage());
              System.out.println("Por favor, verifique los datos ingresados e intente nuevamente.");
          }
      }
  }
}


