package com.pokemon.pokedex.expose;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemon.pokedex.business.PokemonService;
import com.pokemon.pokedex.model.dto.PokemonDto;
import com.pokemon.pokedex.model.dto.PokemonPatchFavoriteDto;
import com.pokemon.pokedex.model.dto.PokemonPatchNameDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PokemonController.class)
class PokemonControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PokemonService pokemonService;

  @Autowired
  private ObjectMapper objectMapper;


  @Test
  void createPokemon_whenPostMethod() throws Exception {
    final PokemonDto pokemonDto =getPokemonDto();
    when(pokemonService.savePokemon(any(PokemonDto.class))).thenReturn(pokemonDto);

    mockMvc.perform(post("/v1/pokemons")
            .content(objectMapper.writeValueAsString(pokemonDto))
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name",is(pokemonDto.getName())));
  }

  @Test
  void findAllPokemons_whenGetMethod() throws Exception {
    final List<PokemonDto> pokemons = new ArrayList<>();
    pokemons.add(getPokemonDto());
    when(pokemonService.findAllPokemons(anyString(),anyBoolean())).thenReturn(pokemons);

    mockMvc.perform(get("/v1/pokemons?orderByColumn=CP&ascending=false")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$",hasSize(1)))
            .andExpect(jsonPath("$[0].name",is("pokemon")));
  }

  @Test
  void findPokemonById_whenGetMethod() throws Exception {
    final PokemonDto pokemon =getPokemonDto();
    when(pokemonService.findPokemonById(anyLong())).thenReturn(pokemon);

    mockMvc.perform(get("/v1/pokemons/1")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name",is(pokemon.getName())));
  }

  @Test
  void updatePokemon_whenPutMethod() throws Exception {
    final PokemonDto pokemonDto =getPokemonDto();
    when(pokemonService.updatePokemon(any(PokemonDto.class),anyLong())).thenReturn(pokemonDto);

    mockMvc.perform(put("/v1/pokemons/1")
            .content(objectMapper.writeValueAsString(pokemonDto))
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name",is(pokemonDto.getName())));
  }

  @Test
  void patchNamePokemon_whenPatchMethod() throws Exception {
    final PokemonPatchNameDto pokemonPatchNameDto =getPokemonPatchNameDto();
    final PokemonDto pokemonDto =getPokemonDto();
    when(pokemonService.patchNamePokemon(any(PokemonPatchNameDto.class),anyLong())).thenReturn(pokemonDto);

    mockMvc.perform(patch("/v1/pokemons/update-name/1")
            .content(objectMapper.writeValueAsString(pokemonPatchNameDto))
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name",is(pokemonDto.getName())));
  }

  @Test
  void patchFavoritePokemon_whenPatchMethod() throws Exception {
    final PokemonPatchFavoriteDto pokemonPatchFavoriteDto =getPokemonPatchFavoriteDto();
    final PokemonDto pokemonDto =getPokemonDto();
    when(pokemonService.patchFavoritePokemon(any(PokemonPatchFavoriteDto.class),anyLong()))
            .thenReturn(pokemonDto);

    mockMvc.perform(patch("/v1/pokemons/make-favorite/1")
            .content(objectMapper.writeValueAsString(pokemonPatchFavoriteDto))
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name",is(pokemonDto.getName())));
  }

  @Test
  void deletePokemonById_whenDeleteMethod() throws Exception {
    final PokemonDto pokemon =getPokemonDto();
    when(pokemonService.deletePokemonById(anyLong())).thenReturn(pokemon);

    mockMvc.perform(delete("/v1/pokemons/1")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name",is(pokemon.getName())));
  }


  private PokemonPatchFavoriteDto getPokemonPatchFavoriteDto(){
    return PokemonPatchFavoriteDto.builder().favorite(true).id(1L).build();
  }

  private PokemonPatchNameDto getPokemonPatchNameDto(){
    return PokemonPatchNameDto.builder().name("pokemon").build();
  }



  private PokemonDto getPokemonDto() {
    return PokemonDto.builder()
            .name("pokemon").build();
  }
}