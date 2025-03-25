package com.montaury.pokebagarre.metier;

import com.montaury.pokebagarre.erreurs.ErreurBagarre;
import com.montaury.pokebagarre.erreurs.ErreurMemePokemon;
import com.montaury.pokebagarre.erreurs.ErreurPokemonNonRenseigne;
import com.montaury.pokebagarre.erreurs.ErreurRecuperationPokemon;
import com.montaury.pokebagarre.webapi.PokeBuildApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class BagarreTest {
    private static PokeBuildApi mockApi;
    private static Bagarre bagarre;

    @BeforeAll
    public static void setUp() {
        mockApi = Mockito.mock(PokeBuildApi.class);
        bagarre = new Bagarre(mockApi);
    }

    @Test
    public void devrait_lancer_erreur_si_premier_pokemon_vide() {
        Throwable thrown = assertThrows(ErreurPokemonNonRenseigne.class, () -> bagarre.demarrer("", "Pikachu"));
        assertThat(thrown).isInstanceOf(ErreurPokemonNonRenseigne.class).hasMessage("Le premier pokemon n'est pas renseigne");
    }

    @Test
    public void devrait_lancer_erreur_si_second_pokemon_vide() {
        Throwable thrown = assertThrows(ErreurPokemonNonRenseigne.class, () -> bagarre.demarrer("Pikachu", ""));
        assertThat(thrown).isInstanceOf(ErreurPokemonNonRenseigne.class).hasMessage("Le second pokemon n'est pas renseigne");
    }

    @Test
    public void devrait_lancer_erreur_si_les_deux_pokemons_sont_identiques() {
        Throwable thrown = assertThrows(ErreurMemePokemon.class, () -> bagarre.demarrer("Pikachu", "Pikachu"));
        assertThat(thrown).isInstanceOf(ErreurMemePokemon.class);
    }

    @Test
    public void devrait_lever_erreur_si_mauvaise_recup_pokemon() {
        Mockito.when(mockApi.recupererParNom("pikatvhouuuuu")).thenReturn(CompletableFuture.failedFuture(new ErreurRecuperationPokemon("pikatvhouuuuu")));

        Throwable thrown = catchThrowable(() -> mockApi.recupererParNom("pikatvhouuuuu").get());
        assertThat(thrown).hasCauseInstanceOf(ErreurRecuperationPokemon.class).withFailMessage("Impossible de recuperer les details sur 'pikatvhouuuuu'");
    }
}