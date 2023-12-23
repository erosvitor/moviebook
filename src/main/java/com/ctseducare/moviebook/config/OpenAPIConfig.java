package com.ctseducare.moviebook.config;


import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import jakarta.ws.rs.core.Application;

@OpenAPIDefinition(
  info = @Info(
    title = "API para gerenciamento de filmes assistidos",
    description = "Esta API disponibiliza recursos para gerenciamento de filmes assistidos.",
    version = "1.0.0",
    contact = @Contact(
      name = "Eros Vitor Bornatowski",
      email = "erosborna@gmail.com",
      url = "https://github.com/erosvitor"
    ),
    license = @License(
      name = "MIT License",
      url = "https://choosealicense.com/licenses/mit/"
    ),
    termsOfService = "https://www.ctseducare.com/terms"
  ),
  servers = {
    @Server(url = "http://localhost:8080")
  })
public class OpenAPIConfig extends Application {

}