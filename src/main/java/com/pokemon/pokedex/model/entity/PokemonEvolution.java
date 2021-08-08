package com.pokemon.pokedex.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <b>Class</b>: PokemonEvolution <br/>.
 *
 * @author Carlos <br/>
 */

@Entity()
@Table(name = "pokemon_evolutions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PokemonEvolution {

  @Id
  @Column
  private Long id;

  @Column(name = "pokemon_name")
  private String pokemonName;

  @Column(name = "evolution")
  private String evolution;

  @Column(name = "candy_amount")
  private Integer candyAmount;

}
