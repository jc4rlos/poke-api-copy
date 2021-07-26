package com.pokemon.pokedex.repository;

import com.pokemon.pokedex.model.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class</b>:PokemonRepository <br/>.
 * @author Carlos
 */
@Repository
public interface PokemonRepository extends JpaRepository<Pokemon,Long> {
}
