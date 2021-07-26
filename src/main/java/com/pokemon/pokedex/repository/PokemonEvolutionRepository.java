package com.pokemon.pokedex.repository;

import com.pokemon.pokedex.model.entity.PokemonEvolution;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * <b>Class</b>:PokemonEvolutionRepository <br/>.
 *
 * @author Carlos
 */
@Repository
public interface PokemonEvolutionRepository extends JpaRepository<PokemonEvolution, Long> {
  Optional<PokemonEvolution> findByPokemonName(String pokemonName);

  /*@Query("select p.id,p.evolution,p.pokemon_name from pokemon_evolution p where p.pokemon_name = ?1")
  PokemonEvolution findByPokemonNameWithQuery(String pokemonName);*/
}
