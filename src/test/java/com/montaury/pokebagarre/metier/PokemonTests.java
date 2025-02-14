/*
* Test pour pokebagarre
* 1-Attaque pokemon 1 supérieure a attaque pokemon 2
* 2-Attaque pokemon 1 inférieure a attaque pokemon 2
* 3-Defence pokemon 1 supérieure a defence pokemon 2
* 4-Defence pokemon 1 inférieure a defence pokemon 2
* 5-Même stats
 */




package com.montaury.pokebagarre.metier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokemonTests {
    @Test
    void force_sup(){
        // GIVEN
        Pokemon p1 = new Pokemon("1", "", new Stats(10, 5));
        Pokemon p2 = new Pokemon("2", "", new Stats(9, 5));

        // WHEN
        boolean resultat = p1.estVainqueurContre(p2);

        // THEN
        assertEquals(resultat, true);
    }

    @Test
    void force_inf(){
        // GIVEN
        Pokemon p1 = new Pokemon("1", "", new Stats(9, 5));
        Pokemon p2 = new Pokemon("2", "", new Stats(10, 5));

        // WHEN
        boolean resultat = p1.estVainqueurContre(p2);

        // THEN
        assertEquals(resultat, false);
    }

    @Test
    void def_sup(){
        // GIVEN
        Pokemon p1 = new Pokemon("1", "", new Stats(10, 6));
        Pokemon p2 = new Pokemon("2", "", new Stats(10, 5));

        // WHEN
        boolean resultat = p1.estVainqueurContre(p2);

        // THEN
        assertEquals(resultat, true);
    }

    @Test
    void def_inf(){
        // GIVEN
        Pokemon p1 = new Pokemon("1", "", new Stats(10, 5));
        Pokemon p2 = new Pokemon("2", "", new Stats(10, 6));

        // WHEN
        boolean resultat = p1.estVainqueurContre(p2);

        // THEN
        assertEquals(resultat, false);
    }

    @Test
    void stat_meme(){
        // GIVEN
        Pokemon p1 = new Pokemon("1", "", new Stats(10, 6));
        Pokemon p2 = new Pokemon("2", "", new Stats(10, 6));

        // WHEN
        boolean resultat1 = p1.estVainqueurContre(p2);
        boolean resultat2 = p2.estVainqueurContre(p1);

        // THEN
        assertEquals(resultat1, true);
        assertEquals(resultat2, true);
    }
}