package hangman.util;

import java.util.HashMap;
import java.util.Map;

public class LetterFrequency
{
    public static Map<Character, Double> letterFrequency = new HashMap<>();
    public static double LARGEST_FREQ = .12702D;

    static
    {
        letterFrequency.put('E', .12702);
        letterFrequency.put('T', .09056);
        letterFrequency.put('A', .08167);
        letterFrequency.put('O', .07507);
        letterFrequency.put('I', .06966);
        letterFrequency.put('N', .06749);
        letterFrequency.put('S', .06327);
        letterFrequency.put('H', .06094);
        letterFrequency.put('R', .05987);
        letterFrequency.put('D', .04253);
        letterFrequency.put('L', .04025);
        letterFrequency.put('C', .02782);
        letterFrequency.put('U', .02758);
        letterFrequency.put('M', .02406);
        letterFrequency.put('W', .02360);
        letterFrequency.put('F', .02228);
        letterFrequency.put('G', .02015);
        letterFrequency.put('Y', .01974);
        letterFrequency.put('P', .01929);
        letterFrequency.put('B', .01492);
        letterFrequency.put('V', .00978);
        letterFrequency.put('K', .00772);
        letterFrequency.put('J', .00153);
        letterFrequency.put('X', .00150);
        letterFrequency.put('Q', .00095);
        letterFrequency.put('Z', .00074);


    }
}
