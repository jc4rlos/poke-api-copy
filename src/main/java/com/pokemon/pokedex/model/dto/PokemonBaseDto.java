package com.pokemon.pokedex.model.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <b>Class</b>:PokemonBaseDto <br/>.
 *
 * @author Carlos
 */
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class PokemonBaseDto {
  private Long pokedexId;
  private String name;
  private Integer order;
  private Integer cp;
  private Integer hp;
  private BigDecimal weight;
  private BigDecimal height;
  private String type;
  private String gender;
  private Integer candy;
  private Integer starDust;
  private String image;
  private boolean favorite;
  private boolean shiny;

}
