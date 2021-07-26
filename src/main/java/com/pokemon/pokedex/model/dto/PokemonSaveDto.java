package com.pokemon.pokedex.model.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

/**
 * <b>Class</b>:PokemonSaveDto <br/>.
 *
 * @author Carlos
 */
@Data
@Builder
public class PokemonSaveDto  {
  private Long id;
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
  private String evolution;
}
