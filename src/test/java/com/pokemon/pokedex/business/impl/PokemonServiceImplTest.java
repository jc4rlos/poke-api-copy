package com.pokemon.pokedex.business.impl;

import com.pokemon.pokedex.exception.PokemonNotFoundException;
import com.pokemon.pokedex.model.dto.PokemonGetDto;
import com.pokemon.pokedex.model.dto.PokemonPatchFavoriteDto;
import com.pokemon.pokedex.model.dto.PokemonPatchNameDto;
import com.pokemon.pokedex.model.dto.PokemonSaveDto;
import com.pokemon.pokedex.model.entity.Pokemon;
import com.pokemon.pokedex.repository.PokemonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PokemonServiceImplTest {

  @Mock
  private PokemonRepository pokemonRepository;

  @InjectMocks
  private PokemonServiceImpl pokemonService;

  private final Long id =1L;


  @Test
  void whenSavePokemon_ShouldReturnPokemon() {
    //given
    final Pokemon pokemon = getPokemon();
    final PokemonSaveDto pokemonSaveDto = getPokemonSaveDto();
    when(pokemonRepository.save(any(Pokemon.class))).thenReturn(pokemon);

    //when
    final PokemonGetDto createdPokemon = pokemonService.savePokemon(pokemonSaveDto);

    //then
    verify(pokemonRepository).save(pokemon);
    assertThat(createdPokemon.getName()).isEqualTo(pokemon.getName());

  }

  @Test
  void shouldReturnAllPokemons() {
    List<Pokemon> pokemons = List.of(getPokemon());

    when(pokemonRepository.findAll()).thenReturn(pokemons);

    List<PokemonGetDto> expected = pokemonService.findAllPokemons();

    verify(pokemonRepository).findAll();
    assertThat(pokemons.size()).isEqualTo(expected.size());

  }

  @Test
  void whenGivenId_shouldDeletePokemon_ifFound() {
    final Pokemon pokemon = getPokemon();
    when(pokemonRepository.findById(anyLong())).thenReturn(Optional.of(pokemon));

    final PokemonGetDto deletedPokemon = pokemonService.deletePokemonById(id);

    verify(pokemonRepository).findById(id);
    verify(pokemonRepository).delete(pokemon);
    assertThat(pokemon.getName()).isEqualTo(deletedPokemon.getName());
  }

  @Test
  void should_throw_exception_when_deletePokemon() {
    when(pokemonRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
    assertThrows(PokemonNotFoundException.class, ()-> pokemonService.deletePokemonById(id));
  }

  @Test
  void whenGivenId_shouldReturnPokemon_ifFound(){
    final Pokemon pokemon = getPokemon();
    when(pokemonRepository.findById(anyLong())).thenReturn(Optional.of(pokemon));

    final PokemonGetDto expected = pokemonService.findPokemonById(id);

    verify(pokemonRepository).findById(id);
    assertThat(pokemon.getName()).isEqualTo(expected.getName());

  }

  @Test
  void should_throw_exception_when_findPokemonById() {
    when(pokemonRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
    assertThrows(PokemonNotFoundException.class, ()-> pokemonService.findPokemonById(id));
  }

  @Test
  void whenGivenId_shouldUpdatePokemon_ifFound(){
    final Pokemon pokemon = getPokemon();
    final PokemonSaveDto pokemonSaveDto = getPokemonSaveDto();

    when(pokemonRepository.findById(anyLong())).thenReturn(Optional.of(pokemon));
    when(pokemonRepository.save(any(Pokemon.class))).thenReturn(pokemon);

    final PokemonGetDto updatedPokemon = pokemonService.updatePokemon(pokemonSaveDto,id);

    verify(pokemonRepository).save(pokemon);
    verify(pokemonRepository).findById(id);
    assertThat(updatedPokemon.getName()).isEqualTo(pokemon.getName());

  }

  @Test
  void should_throw_exception_when_updatePokemon() {
    final PokemonSaveDto pokemonSaveDto = getPokemonSaveDto();
    when(pokemonRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
    assertThrows(PokemonNotFoundException.class, ()-> pokemonService.updatePokemon(pokemonSaveDto,id));
  }

  @Test
  void whenGivenId_shouldPatchNamePokemon_ifFound(){
    final Pokemon pokemon = getPokemon();
    final PokemonPatchNameDto pokemonPatchNameDto = getPokemonPatchNameDto();

    when(pokemonRepository.findById(anyLong())).thenReturn(Optional.of(pokemon));
    when(pokemonRepository.save(any(Pokemon.class))).thenReturn(pokemon);

    final PokemonGetDto updatedPokemon = pokemonService.patchNamePokemon(pokemonPatchNameDto,id);

    verify(pokemonRepository).save(pokemon);
    verify(pokemonRepository).findById(id);
    assertThat(updatedPokemon.getName()).isEqualTo(pokemon.getName());

  }

  @Test
  void should_throw_exception_when_patchNamePokemon() {
    final PokemonPatchNameDto pokemonPatchNameDto = getPokemonPatchNameDto();

    when(pokemonRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
    assertThrows(PokemonNotFoundException.class, ()-> pokemonService.patchNamePokemon(pokemonPatchNameDto,id));
  }

  @Test
  void whenGivenId_shouldPatchFavoritePokemon_ifFound(){
    final Pokemon pokemon = getPokemon();
    final PokemonPatchFavoriteDto pokemonPatchFavoriteDto = getPokemonPatchFavoriteDto();

    when(pokemonRepository.findById(anyLong())).thenReturn(Optional.of(pokemon));
    when(pokemonRepository.save(any(Pokemon.class))).thenReturn(pokemon);

    final PokemonGetDto updatedPokemon = pokemonService.patchFavoritePokemon(pokemonPatchFavoriteDto,id);

    verify(pokemonRepository).save(pokemon);
    verify(pokemonRepository).findById(id);
    assertThat(updatedPokemon.isFavorite()).isEqualTo(pokemon.isFavorite());

  }

  @Test
  void should_throw_exception_when_patchFavoritePokemon() {
    final PokemonPatchFavoriteDto pokemonPatchFavoriteDto = getPokemonPatchFavoriteDto();
    when(pokemonRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
    assertThrows(PokemonNotFoundException.class, ()-> pokemonService.patchFavoritePokemon(pokemonPatchFavoriteDto,id));
  }

  private PokemonPatchFavoriteDto getPokemonPatchFavoriteDto(){
   return PokemonPatchFavoriteDto.builder().favorite(true).id(id).build();
  }

  private PokemonPatchNameDto getPokemonPatchNameDto(){
    return PokemonPatchNameDto.builder().name("pokemon").build();
  }

  private PokemonSaveDto getPokemonSaveDto() {
    return PokemonSaveDto.builder()
            .name("pokemon").build();
  }

  private Pokemon getPokemon() {
    return Pokemon.builder()
            .name("pokemon").build();
  }

}