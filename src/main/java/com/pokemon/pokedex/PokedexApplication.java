package com.pokemon.pokedex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * <b>Class</b>:PokedexApplication <br/>.
 *
 * @author Carlos
 */
@SpringBootApplication
@EnableJpaRepositories
public class PokedexApplication {

  public static void main(String[] args) {
    SpringApplication.run(PokedexApplication.class, args);
  }

}
