package net.cloudcentrik.common.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {

    private static final String NUMERIC_STRING = "0123456789";
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String[] SUITS = {"Clubs", "Diamonds", "Hearts", "Spades"};
    private static final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    private static final String[] PLAYERS = {"Kalle", "Ismail", "Danial", "Bertek", "David", "Ölof"};

    private static final String[] VAT_RATE_PERCENT = {"0", "6", "12", "25"};

    public static String randomId(int length) {

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }

        return buffer.toString();
    }


    public static String randomAlphaNumeric(int count) {

        StringBuilder builder = new StringBuilder();

        while (count-- != 0) {

            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());

            builder.append(ALPHA_NUMERIC_STRING.charAt(character));

        }

        return builder.toString();

    }

    public static String randomNumeric(int count) {

        StringBuilder builder = new StringBuilder();

        while (count-- != 0) {

            int character = (int) (Math.random() * NUMERIC_STRING.length());

            builder.append(NUMERIC_STRING.charAt(character));

        }

        return builder.toString();

    }

    public static String generateRandomFloat(float min, float max) {
        if (min >= max)
            throw new IllegalArgumentException("max must be greater than min");
        Random rand = new Random();
        float result = ThreadLocalRandom.current().nextFloat() * (max - min) + min;
        if (result >= max) // correct for rounding
            result = Float.intBitsToFloat(Float.floatToIntBits(max) - 1);
        return Float.toString(result);
    }

    public static float randomFloat(float min, float max) {

        Random rand = new Random();

        float result = rand.nextFloat() * (max - min) + min;

        return result;

    }

    public static float randomInt(int min, int max) {

        int result = ThreadLocalRandom.current().nextInt(min,max);

        return result;

    }

    public static String randomName(String language) {
        int i = (int) (Math.random() * RANKS.length);
        int j = (int) (Math.random() * SUITS.length);
        int k = (int) (Math.random() * PLAYERS.length);

        if(language.equals("sv")){
            return PLAYERS[k] + " - " + RANKS[i] + " av " + SUITS[j];
        }else if(language.equals("en")){
            return PLAYERS[k] + " - " + RANKS[i] + " of " + SUITS[j];
        }
        return "";
    }

    public static String randomDescription(String language) {
        int i = (int) (Math.random() * RANKS.length);
        int j = (int) (Math.random() * SUITS.length);
        int k = (int) (Math.random() * PLAYERS.length);

        if(language.equals("sv")){
            return PLAYERS[k] + " placera " + RANKS[i] + " av " + SUITS[j]+" efter "+randomName("sv");
        }else if(language.equals("en")){
            return PLAYERS[k] + " place " + RANKS[i] + " of " + SUITS[j]+" after "+randomName("en");
        }
        return "";

    }

    public static String randomVatRate() {
        int i = (int) (Math.random() * VAT_RATE_PERCENT.length);
        return VAT_RATE_PERCENT[i];
    }
}
