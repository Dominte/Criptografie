

import java.math.BigInteger;

public class DES {

    int roundNumb;

    public int getRoundNumb() {
        return roundNumb;
    }

    public void IterateRoundNumb() {
        this.roundNumb++;
    }

    public void setRoundNumb(int roundNumb) {
        this.roundNumb = roundNumb;
    }

    int[] IP = {58, 50, 42, 34, 26, 18, 10, 2,
            60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6,
            64, 56, 48, 40, 32, 24, 16, 8,
            57, 49, 41, 33, 25, 17, 9, 1,
            59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5,
            63, 55, 47, 39, 31, 23, 15, 7};

    int[] IP_invers = {40, 8, 48, 16, 56, 24, 64, 32,
            39, 7, 47, 15, 55, 23, 63, 31,
            38, 6, 46, 14, 54, 22, 62, 30,
            37, 5, 45, 13, 53, 21, 61, 29,
            36, 4, 44, 12, 52, 20, 60, 28,
            35, 3, 43, 11, 51, 19, 59, 27,
            34, 2, 42, 10, 50, 18, 58, 26,
            33, 1, 41, 9, 49, 17, 57, 25};

    int[] PC1 = {57, 49, 41, 33, 25,
            17, 9, 1, 58, 50, 42, 34, 26,
            18, 10, 2, 59, 51, 43, 35, 27,
            19, 11, 3, 60, 52, 44, 36, 63,
            55, 47, 39, 31, 23, 15, 7, 62,
            54, 46, 38, 30, 22, 14, 6, 61,
            53, 45, 37, 29, 21, 13, 5, 28,
            20, 12, 4};

    int[] PC2 = {14, 17, 11, 24, 1, 5, 3,
            28, 15, 6, 21, 10, 23, 19, 12,
            4, 26, 8, 16, 7, 27, 20, 13, 2,
            41, 52, 31, 37, 47, 55, 30, 40,
            51, 45, 33, 48, 44, 49, 39, 56,
            34, 53, 46, 42, 50, 36, 29, 32};

    int[] EP = {32, 1, 2, 3, 4, 5,
            4, 5, 6, 7, 8, 9,
            8, 9, 10, 11, 12, 13,
            12, 13, 14, 15, 16, 17,
            16, 17, 18, 19, 20, 21,
            20, 21, 22, 23, 24, 25,
            24, 25, 26, 27, 28, 29,
            28, 29, 30, 31, 32, 1};

    int[] P = {16, 7, 20, 21, 29, 12, 28,
            17, 1, 15, 23, 26, 5, 18,
            31, 10, 2, 8, 24, 14, 32,
            27, 3, 9, 19, 13, 30, 6,
            22, 11, 4, 25};

    int[][][] sbox = {
            {{14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
                    {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
                    {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
                    {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}},
            {{15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
                    {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
                    {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
                    {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}},
            {{10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
                    {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
                    {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
                    {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}},
            {{7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
                    {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
                    {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
                    {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}},
            {{2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
                    {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
                    {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
                    {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}},
            {{12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
                    {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
                    {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
                    {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}},
            {{4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
                    {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
                    {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
                    {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}},
            {{13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
                    {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
                    {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
                    {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}}
    };


    public  String HexToBin(String input) {
        String string = new BigInteger(input, 16).toString(2);
        while (string.length() < input.length() * 4)
            string = '0' + string;

        return string;
    }


    public  String BinToHex(String input) {
        String string = new BigInteger(input, 2).toString(16);
        while (string.length() < input.length() / 4)
            string = '0' + string;

        return string;
    }

    //input hex
    public String LeftShift(String input) {
        StringBuilder sb = new StringBuilder();
        String inputCopy = HexToBin(input);

        for (int i = 0; i < inputCopy.length() - 1; i++)
            sb.append(inputCopy.charAt(i + 1));
        sb.append(inputCopy.charAt(0));

        String output = sb.toString();

        output = BinToHex(output);

        return output;
    }

    //input hex
    public String Permutare(int[] PC, String input) {
        StringBuilder sb = new StringBuilder();
        input = HexToBin(input);

        for (int i = 0; i < PC.length; i++)
            sb.append(input.charAt(PC[i] - 1));

        return BinToHex(sb.toString());
    }

    public String[] getKeys(String key) {
        String[] keys = new String[16];

        key = Permutare(PC1, key);
        for (int i = 0; i < 16; i++) {
            if (i == 0 || i == 1 || i == 8 || i == 15) { // o singura permutare
                key = LeftShift(key.substring(0, 7)) + LeftShift(key.substring(7, 14));
            } else {   // doua permutari
                key = LeftShift(LeftShift(key.substring(0, 7))) + LeftShift(LeftShift(key.substring(7, 14)));
            }
            keys[i] = Permutare(PC2, key);
        }
        return keys;
    }

    //input hexa
    String Xor(String a, String b) {
        String copyA = HexToBin(a);
        String copyB = HexToBin(b);
        StringBuilder sb = new StringBuilder();
        int length;
        int begin;

        if (copyA.length() > copyB.length()) {
            length = copyA.length();
            begin = copyA.length() - copyB.length();
        } else {
            length = copyB.length();
            begin = copyB.length() - copyA.length();
        }

        for (int i = begin; i < length; i++) {
            if (copyA.length() > copyB.length())
                sb.append(copyA.charAt(i) ^ copyB.charAt(i - begin));
            else
                sb.append(copyA.charAt(i - begin) ^ copyB.charAt(i));
        }
        String output = sb.toString();

        while (output.length() < copyB.length())
            output = "0" + output;
        while (output.length() < copyA.length())
            output = "0" + output;

        output = BinToHex(output);
        return output;
    }

    //input hexa
    String sBoxFunction(String input) {
        StringBuilder sb = new StringBuilder();
        input = HexToBin(input);
        for (int i = 0; i < 48; i += 6) {
            String substring = input.substring(i, i + 6);

            int n = i / 6;

            int lin = Integer.parseInt(
                    substring.charAt(0) + "" + substring.charAt(5), 2); // primul si ultim bit

            int col = Integer.parseInt(
                    substring.substring(1, 5), 2); // bitii din mijloc 2-5

            String c = Integer.toHexString(sbox[n][lin][col]);

            sb.append(c);
        }
        return sb.toString();
    }


    String round(String input, String key) {

        String left = input.substring(0, 8);
        String copy = input.substring(8, 16);
        String right = copy;

        copy = Permutare(EP, copy);
        copy = Xor(copy, key);
        copy = sBoxFunction(copy);
        copy = Permutare(P, copy);
        left = Xor(left, copy);

        //System.out.println("Runda " + getRoundNumb() + " Right: " + HexToBin(right.toUpperCase()) + " Left: " + HexToBin(left.toUpperCase()));

        return right + left;
    }

    //input hexa
    public String Criptare(String input, String key) {
        int i;

        String[] keys = getKeys(key);

        input = Permutare(IP, input); // permutarea initiala

        setRoundNumb(0);
        IterateRoundNumb();
        for (i = 0; i < 16; i++) {
            //System.out.println("Cheia: " + HexToBin(keys[i]));
            input = round(input, keys[i]);
            IterateRoundNumb();
        }

        input = input.substring(8, 16) + input.substring(0, 8);


        input = Permutare(IP_invers, input);

        return input.toUpperCase();
    }

    String Decriptare(String input, String key) {
        ///System.out.println("Decriptare \n");
        String[] keys = getKeys(key);
        input = Permutare(IP, input);
        setRoundNumb(0);

        IterateRoundNumb();
        for (int i = 0; i <16; i++) {
            input = round(input, keys[16-i-1]);
            IterateRoundNumb();
        }

        input = input.substring(8, 16)
                + input.substring(0, 8);
        input = Permutare(IP_invers, input);


        return input.toUpperCase();
    }


}
