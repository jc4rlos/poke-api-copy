package com.pokemon.pokedex.configuration;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <b>Class</b>:PokemonProperties <br/>.
 *
 * @author Carlos
 */
@Configuration
@ConfigurationProperties("api.pokemon")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PokemonProperties {
  private List<String> legendary;
  private String sortByCp;
  private String sortByDate;
}
