package com.pokemon.pokedex.business.impl;

import com.pokemon.pokedex.exception.PokemonNotFoundException;
import com.pokemon.pokedex.mappers.PokemonsMapper;
import com.pokemon.pokedex.model.dto.PokemonDto;
import com.pokemon.pokedex.model.dto.PokemonPatchFavoriteDto;
import com.pokemon.pokedex.model.dto.PokemonPatchNameDto;
import com.pokemon.pokedex.model.entity.Pokemon;
import com.pokemon.pokedex.model.entity.PokemonEvolution;
import com.pokemon.pokedex.repository.PokemonEvolutionRepository;
import com.pokemon.pokedex.repository.PokemonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PokemonServiceImplTest {

  @Mock
  private PokemonRepository pokemonRepository;

  @Mock
  private PokemonEvolutionRepository pokemonEvolutionRepository;

  @Mock
  private  PokemonsMapper pokemonsMapper;

  @InjectMocks
  private PokemonServiceImpl pokemonService;

  private final Long id = 1L;


    @Test
    void whenSavePokemon_ShouldReturnPokemon() throws PokemonNotFoundException {
      //given
      final Pokemon pokemon = getPokemon();
      final PokemonDto pokemonDto = getPokemonDto();
      final PokemonEvolution pokemonEvolution= PokemonEvolution.builder().evolution("pokemon").build();
      when(pokemonEvolutionRepository.findByPokemonName(anyString()))
              .thenReturn(pokemonEvolution);

      when(pokemonRepository.save(any(Pokemon.class)))
              .thenReturn(pokemon);
      //when
       pokemonService.savePokemon(pokemonDto);

      //then
      assertThat(pokemonDto).isInstanceOf(PokemonDto.class);
    }

  @Test
  void shouldReturnAllPokemons() {
    List<Pokemon> pokemons = List.of(getPokemon());

    when(pokemonRepository.findAll()).thenReturn(pokemons);

    List<PokemonDto> expected = pokemonService.findAllPokemons();

    verify(pokemonRepository).findAll();
    assertThat(pokemons.size()).isEqualTo(expected.size());

  }

  @Test
  void whenGivenId_shouldDeletePokemon_ifFound() throws PokemonNotFoundException {
    final Pokemon pokemon = getPokemon();
    when(pokemonRepository.findById(anyLong())).thenReturn(Optional.of(pokemon));

    final PokemonDto deletedPokemon = pokemonService.deletePokemonById(id);

    verify(pokemonRepository).findById(id);
    verify(pokemonRepository).delete(pokemon);
    assertThat(pokemon.getName()).isEqualTo(deletedPokemon.getName());
  }

  @Test
  void should_throw_exception_when_deletePokemon() {
    when(pokemonRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
    assertThrows(PokemonNotFoundException.class, () -> pokemonService.deletePokemonById(id));
  }

  @Test
  void whenGivenId_shouldReturnPokemon_ifFound()throws PokemonNotFoundException {
    final Pokemon pokemon = getPokemon();
    when(pokemonRepository.findById(anyLong())).thenReturn(Optional.of(pokemon));

    final PokemonDto expected = pokemonService.findPokemonById(id);

    verify(pokemonRepository).findById(id);
    assertThat(pokemon.getName()).isEqualTo(expected.getName());

  }

  @Test
  void should_throw_exception_when_findPokemonById() {
    when(pokemonRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
    assertThrows(PokemonNotFoundException.class, () -> pokemonService.findPokemonById(id));
  }

  @Test
  void whenGivenId_shouldUpdatePokemon_ifFound() {
    final Pokemon pokemon = getPokemon();
    final PokemonDto pokemonDto = getPokemonDto();

    when(pokemonRepository.findById(anyLong())).thenReturn(Optional.of(pokemon));
    when(pokemonRepository.save(any(Pokemon.class))).thenReturn(pokemon);

    final PokemonDto updatedPokemon = pokemonService.updatePokemon(pokemonDto, id);

    verify(pokemonRepository).save(pokemon);
    verify(pokemonRepository).findById(id);
    assertThat(updatedPokemon.getName()).isEqualTo(pokemon.getName());

  }

  @Test
  void should_throw_exception_when_updatePokemon() {
    final PokemonDto pokemonDto = getPokemonDto();
    when(pokemonRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
    assertThrows(PokemonNotFoundException.class, () -> pokemonService.updatePokemon(pokemonDto, id));
  }

  @Test
  void whenGivenId_shouldPatchNamePokemon_ifFound() {
    final Pokemon pokemon = getPokemon();
    final PokemonPatchNameDto pokemonPatchNameDto = getPokemonPatchNameDto();

    when(pokemonRepository.findById(anyLong())).thenReturn(Optional.of(pokemon));
    when(pokemonRepository.save(any(Pokemon.class))).thenReturn(pokemon);

    final PokemonDto updatedPokemon = pokemonService.patchNamePokemon(pokemonPatchNameDto, id);

    verify(pokemonRepository).save(pokemon);
    verify(pokemonRepository).findById(id);
    assertThat(updatedPokemon.getName()).isEqualTo(pokemon.getName());

  }

  @Test
  void should_throw_exception_when_patchNamePokemon() {
    final PokemonPatchNameDto pokemonPatchNameDto = getPokemonPatchNameDto();

    when(pokemonRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
    assertThrows(PokemonNotFoundException.class, () -> pokemonService.patchNamePokemon(pokemonPatchNameDto, id));
  }

  @Test
  void whenGivenId_shouldPatchFavoritePokemon_ifFound() {
    final Pokemon pokemon = getPokemon();
    final PokemonPatchFavoriteDto pokemonPatchFavoriteDto = getPokemonPatchFavoriteDto();

    when(pokemonRepository.findById(anyLong())).thenReturn(Optional.of(pokemon));
    when(pokemonRepository.save(any(Pokemon.class))).thenReturn(pokemon);

    final PokemonDto updatedPokemon = pokemonService.patchFavoritePokemon(pokemonPatchFavoriteDto, id);

    verify(pokemonRepository).save(pokemon);
    verify(pokemonRepository).findById(id);
    assertThat(updatedPokemon.isFavorite()).isEqualTo(pokemon.isFavorite());

  }

  @Test
  void should_throw_exception_when_patchFavoritePokemon() {
    final PokemonPatchFavoriteDto pokemonPatchFavoriteDto = getPokemonPatchFavoriteDto();
    when(pokemonRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
    assertThrows(PokemonNotFoundException.class, () -> pokemonService.patchFavoritePokemon(pokemonPatchFavoriteDto, id));
  }

  private PokemonPatchFavoriteDto getPokemonPatchFavoriteDto() {
    return PokemonPatchFavoriteDto.builder().favorite(true).id(id).build();
  }

  private PokemonPatchNameDto getPokemonPatchNameDto() {
    return PokemonPatchNameDto.builder().name("pokemon").build();
  }

  private PokemonDto getPokemonDto() {
    return PokemonDto.builder()
            .id(id)
            .name("pokemon").build();
  }

  private Pokemon getPokemon() {
    return Pokemon.builder()
            .id(id)
            .name("pokemon").build();
  }

}