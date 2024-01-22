package com.sundae.api.utils;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PasswordGenerateUtils {

    /**
     * Generates a secure random password with a specified composition of numbers, special characters,
     * and alphabets.
     *
     * @return A randomly generated secure password.
     */
    public static String generateSecureRandomPassword() {
        Stream<Character> pwdStream = Stream.concat(
                getRandomNumbers(2),
                Stream.concat(
                        getRandomSpecialChars(2),
                        Stream.concat(getRandomAlphabets(2, true), getRandomAlphabets(4, false))));
        List<Character> charList = pwdStream.collect(Collectors.toList());
        Collections.shuffle(charList);
        return charList.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    /**
     * Generates a stream of random special characters with the specified count.
     *
     * @param count The number of special characters to generate.
     * @return A stream of randomly generated special characters.
     */
    public static Stream<Character> getRandomSpecialChars(int count) {
        Random random = new SecureRandom();
        IntStream specialChars = random.ints(count, 33, 45);
        return specialChars.mapToObj(data -> (char) data);
    }

    /**
     * Generates a stream of random numbers with the specified count.
     *
     * @param count The number of numbers to generate.
     * @return A stream of randomly generated numbers.
     */
    public static Stream<Character> getRandomNumbers(int count) {
        Random random = new SecureRandom();
        return random.ints(count, 48, 58).mapToObj(data -> (char) data);
    }

    /**
     * Generates a stream of random alphabets (either uppercase or lowercase) with the specified count.
     *
     * @param count     The number of alphabets to generate.
     * @param uppercase If true, generates uppercase alphabets; otherwise, generates lowercase alphabets.
     * @return A stream of randomly generated alphabets.
     */
    public static Stream<Character> getRandomAlphabets(int count, boolean uppercase) {
        Random random = new SecureRandom();
        int start = uppercase ? 65 : 97;
        return random.ints(count, start, start + 26).mapToObj(data -> (char) data);
    }
}
