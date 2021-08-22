package com.pokemon.pokedex.expose;

import com.pokemon.pokedex.business.PokemonService;
import com.pokemon.pokedex.exception.PokemonNotFoundException;

import com.pokemon.pokedex.model.dto.PokemonDto;
import com.pokemon.pokedex.model.dto.PokemonPatchFavoriteDto;
import com.pokemon.pokedex.model.dto.PokemonPatchNameDto;
import java.util.List;
import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




/**
 * <b>Class</b>:PokemonController <br/>.
 *
 * @author Carlos
 */
@RestController
@RequestMapping("/v1/pokemons")
@RequiredArgsConstructor
public class PokemonController {
  private final PokemonService pokemonService;

  /**
   * pokemonDto .
   *
   * @param pokemonDto .
   * @return
   */
  @PostMapping
  public ResponseEntity<PokemonDto> savePokemon(@Valid @RequestBody final PokemonDto pokemonDto) {
    return new ResponseEntity<>(pokemonService.savePokemon(pokemonDto), HttpStatus.CREATED);
  }

  /**
   * findAllPokemons .
   *
   * @return List of PokemonDto
   */
  @GetMapping
  public ResponseEntity<List<PokemonDto>> findAllPokemons(@RequestParam("orderByColumn") final String orderByColumn,
                                                          @RequestParam("ascending") final boolean ascending) {
    return new ResponseEntity<>(pokemonService.findAllPokemons(orderByColumn, ascending), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PokemonDto> findPokemonById(@PathVariable("id") final Long id) throws PokemonNotFoundException {
    return new ResponseEntity<>(pokemonService.findPokemonById(id), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PokemonDto> updatePokemon(@RequestBody final PokemonDto pokemonDto,
                                                  @PathVariable("id") final Long id) throws PokemonNotFoundException {
    return new ResponseEntity<>(pokemonService.updatePokemon(pokemonDto, id), HttpStatus.OK);
  }

  @PatchMapping("/update-name/{id}")
  public ResponseEntity<PokemonDto> patchNamePokemon(@RequestBody final PokemonPatchNameDto pokemonPatchNameDto,
                                                     @PathVariable("id") final Long id) throws PokemonNotFoundException {
    return new ResponseEntity<>(pokemonService.patchNamePokemon(pokemonPatchNameDto, id), HttpStatus.OK);
  }

  @PatchMapping("/make-favorite/{id}")
  public ResponseEntity<PokemonDto> patchFavoritePokemon(@RequestBody final PokemonPatchFavoriteDto pokemonPatchFavoriteDto,
                                                         @PathVariable("id") final Long id) throws PokemonNotFoundException {
    return new ResponseEntity<>(pokemonService.patchFavoritePokemon(pokemonPatchFavoriteDto, id), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<PokemonDto> deletePokemonById(@PathVariable("id") final Long id) throws PokemonNotFoundException {
    return new ResponseEntity<>(pokemonService.deletePokemonById(id), HttpStatus.OK);
  }

}
