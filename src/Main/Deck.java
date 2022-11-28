package Main;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

public class Deck {
    private static List<String> deck = new ArrayList<>(52);

    public void createCards(){
        deck.add("2_of_clubs.png");
        deck.add("3_of_clubs.png");
        deck.add("4_of_clubs.png");
        deck.add("5_of_clubs.png");
        deck.add("6_of_clubs.png");
        deck.add("7_of_clubs.png");
        deck.add("8_of_clubs.png");
        deck.add("9_of_clubs.png");
        deck.add("10_of_clubs.png");
        deck.add("2_of_diamonds.png");
        deck.add("3_of_diamonds.png");
        deck.add("4_of_diamonds.png");
        deck.add("5_of_diamonds.png");
        deck.add("6_of_diamonds.png");
        deck.add("7_of_diamonds.png");
        deck.add("8_of_diamonds.png");
        deck.add("9_of_diamonds.png");
        deck.add("10_of_diamonds.png");
        deck.add("2_of_hearts.png");
        deck.add("3_of_hearts.png");
        deck.add("4_of_hearts.png");
        deck.add("5_of_hearts.png");
        deck.add("6_of_hearts.png");
        deck.add("7_of_hearts.png");
        deck.add("8_of_hearts.png");
        deck.add("9_of_hearts.png");
        deck.add("10_of_hearts.png");
        deck.add("2_of_spades.png");
        deck.add("3_of_spades.png");
        deck.add("4_of_spades.png");
        deck.add("5_of_spades.png");
        deck.add("6_of_spades.png");
        deck.add("7_of_spades.png");
        deck.add("8_of_spades.png");
        deck.add("9_of_spades.png");
        deck.add("10_of_spades.png");
        deck.add("ace_of_clubs.png");
        deck.add("ace_of_spades.png");
        deck.add("ace_of_diamonds.png");
        deck.add("ace_of_spades.png");
        deck.add("jack_of_clubs.png");
        deck.add("jack_of_hearts.png");
        deck.add("jack_of_diamonds.png");
        deck.add("jack_of_spades.png");
        deck.add("king_of_clubs.png");
        deck.add("king_of_diamonds.png");
        deck.add("king_of_hearts.png");
        deck.add("king_of_spades.png");
        deck.add("queen_of_clubs.png");
        deck.add("queen_of_diamonds.png");
        deck.add("queen_of_hearts.png");
        deck.add("queen_of_spades.png");

        Collections.shuffle(deck);
    }
    public static String getCard(int num){
        return deck.get(num);//returns the picture String name
    }
    public static void clearDeck(){
        deck.clear();
    }
}
