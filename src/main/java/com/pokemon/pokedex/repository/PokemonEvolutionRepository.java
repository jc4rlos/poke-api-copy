package com.pokemon.pokedex.repository;

import com.pokemon.pokedex.model.entity.PokemonEvolution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * <b>Class</b>:PokemonEvolutionRepository <br/>.
 *
 * @author Carlos
 */
@Repository
public interface PokemonEvolutionRepository extends JpaRepository<PokemonEvolution, Long> {
  PokemonEvolution findByPokemonName(String pokemonName);
}
