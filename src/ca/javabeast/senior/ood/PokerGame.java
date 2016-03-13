/*
 * Copyright 2016 alpenliebe <alpseinstein@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ca.javabeast.senior.ood;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alpenliebe <alpseinstein@gmail.com>
 */
public abstract class PokerGame<T extends Card> {

    protected Deck<T> deck;

    public abstract void initialGame();
}

enum Suit {

    Club(0), Diamond(1), Heart(2), Spade(3);
    private int value;

    private Suit(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static Suit getSuitFromValue(int value) {
        switch (value) {
            case 0:
                return Club;
            case 1:
                return Diamond;
            case 2:
                return Heart;
            case 3:
                return Spade;
            default:
                return null;
        }
    }
}

abstract class Card {

    private boolean available = true;

    /* number or face that's on card - a number 2 through 10, 
     or 11 for Jack, 12 for Queen, 13 for King, or 1 for Ace */
    protected int faceValue;
    protected Suit suit;

    public Card(int c, Suit s) {
        faceValue = c;
        suit = s;
    }

    public abstract int value();

    public Suit suit() {
        return suit;
    }

    public boolean isAvailable() {
        return available;
    }

    public void markAvailable() {
        available = true;
    }

    public void markUnavailable() {
        available = false;
    }
}

class Deck<T extends Card> {

    private List<T> cards;

    public Deck(List<T> cards) {
        cards = cards;
    }

    public void shuffle() {
        for (int l = 0, r = cards.size() - 1; r > 0; r--) {
            l = (int) (Math.random() * (r + 1));
            swap(l, r);
        }
    }

    public int remainingCards() {
        return cards.size();
    }

    public T dealCard() {
        return cards.size() > 0 ? cards.remove(cards.size() - 1) : null;
    }

    private void swap(int l, int r) {
        T temp = cards.get(l);
        cards.set(l, cards.get(r));
        cards.set(r, temp);
    }
}

class Hand<T extends Card> {

    protected List<T> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public int score() {
        int score = 0;
        for (T card : cards) {
            score += card.value();
        }
        return score;
    }

    public void addCard(T card) {
        cards.add(card);
    }

}

//BlackJack
class BlackJackCard extends Card {

    public BlackJackCard(int c, Suit s) {
        super(c, s);
    }

    @Override
    public int value() {
        if (isAce()) {
            return 11;
        } else if (isFaceCard()) {
            return 10;
        } else {
            return faceValue;
        }
    }

    public boolean isAce() {
        return faceValue == 1;
    }

    private boolean isFaceCard() {
        return faceValue > 10 && faceValue < 14;
    }
}

class BlackJackHand extends Hand<BlackJackCard> {

    @Override
    public int score() {
        int an = 0;
        int score = 0;
        for (BlackJackCard card : cards) {
            if (card.isAce()) {
                score += 1;
                an++;
            } else {
                score += card.value();
            }

        }

        for (int i = 0; i < an; i++) {
            if (score > 21 || score + 10 > 21) {
                break;
            } else {
                score += 10;
            }
        }
        return score;
    }

    public boolean busted() {
        return score() > 21;
    }

    public boolean is21() {
        return score() == 21;
    }
}

class BlackJactPokerGame extends PokerGame {

    @Override
    public void initialGame() {
        List<BlackJackCard> cards = new ArrayList<>();
        for (int i = 0; i < 52; i++) {
            cards.add(new BlackJackCard((i + 1)%13==0?13:(i + 1)%13, Suit.getSuitFromValue(i%4)));
        }
        deck = new Deck<>(cards);
        deck.shuffle();
    }
}
