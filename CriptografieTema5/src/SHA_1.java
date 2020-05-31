
import java.math.BigInteger;
import java.util.ArrayList;

public class SHA_1 {
    public String input;
    public String lungime;
    public String SumaH;
    public String Modificare;

    public Chunk[] chunks;
    public String h0 = "01100111010001010010001100000001";
    public String h1 = "11101111110011011010101110001001";
    public String h2 = "10011000101110101101110011111110";
    public String h3 = "00010000001100100101010001110110";
    public String h4 = "11000011110100101110000111110000";

    public String A = h0;
    public String B = h1;
    public String C = h2;
    public String D = h3;
    public String E = h4;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
        this.h0 = "01100111010001010010001100000001";
        this.h1 = "11101111110011011010101110001001";
        this.h2 = "10011000101110101101110011111110";
        this.h3 = "00010000001100100101010001110110";
        this.h4 = "11000011110100101110000111110000";
        this.A = h0;
        this.B = h1;
        this.C = h2;
        this.D = h3;
        this.E = h4;

    }

    public String AdunarePeBiti(String a, String b) {

        BigInteger bigInteger1 = new BigInteger(a, 2);
        BigInteger bigInteger2 = new BigInteger(b, 2);
        bigInteger1 = bigInteger1.add(bigInteger2);
        String result = bigInteger1.toString(2);
        if (result.length() > 32) result = result.substring(result.length() - 32);
        while (result.length() < a.length()) result = '0' + result;

        return result;
    }

    public String BinToHex(String input) {
        String string = new BigInteger(input, 2).toString(16);
        while (string.length() < input.length() / 4)
            string = '0' + string;

        return string;
    }

    public void TextToBin() {
        StringBuilder sb = new StringBuilder();
        String character = "";

        for (int i = 0; i < input.length(); i++) {
            character = Integer.toBinaryString(input.charAt(i));

            while (character.length() < 8)
                character = '0' + character;

            sb.append(character);
        }
        setInput(sb.toString());
        lungime = Integer.toBinaryString(input.length());
        while (lungime.length() < 64)
            lungime = '0' + lungime;
    }

    public void padInput() {
        input = input + '1';
        while ((input.length() % 512) != 448) {
            input = input + '0';
        }

        input = input + lungime;
    }

    String And(String a, String b) {

        StringBuilder sb = new StringBuilder();

        if (a.length() != b.length())
            System.out.println("Eroare AND");

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == '1' && b.charAt(i) == '1')
                sb.append('1');
            else
                sb.append('0');
        }

        return sb.toString();
    }

    String Or(String a, String b) {

        StringBuilder sb = new StringBuilder();

        if (a.length() != b.length())
            System.out.println("Eroare OR");

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == '0' && b.charAt(i) == '0')
                sb.append('0');
            else
                sb.append('1');
        }

        return sb.toString();
    }

    String Xor(String a, String b) {

        StringBuilder sb = new StringBuilder();

        if (a.length() != b.length())
            System.out.println("Eroare XOR");
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == b.charAt(i))
                sb.append('0');
            else
                sb.append('1');
        }

        return sb.toString();
    }

    public void BreakInChunks() {

        StringBuilder sb = new StringBuilder();

        chunks = new Chunk[input.length() / 512];
        for (int i = 0; i < chunks.length; i++) {
            Chunk chunk = new Chunk();
            chunk.text = input.substring(i * 512, (i + 1) * 512);
            chunks[i] = chunk; //impart inputul in chunk-uri
        }


        for (Chunk chunk : chunks) { // per chunk

            for (int j = 16; j < 80; j++) { // am 16 cuvinte, vreau pana la 80

                String string1 = chunk.ObtainWord(j - 3);
                String string2 = chunk.ObtainWord(j - 8);
                String string3 = chunk.ObtainWord(j - 14);
                String string4 = chunk.ObtainWord(j - 16);

                String xor1 = Xor(string1, string2);
                String xor2 = Xor(xor1, string3);
                String xor3 = Xor(xor2, string4);

                xor3 = xor3.substring(1) + xor3.substring(0, 1);

                chunk.text = chunk.text + xor3; // adaugam cuvantul
            }

        }

    }

    public void LoopThroughChunks() {
        for (int i = 0; i < chunks.length; i++) {
            for (int j = 0; j < 80; j++) {
                String f;
                String k;
                if (j < 20) {
                    String BandC = And(B, C);
                    String negare = "11111111111111111111111111111111";
                    String notB = And(Xor(B, negare), D);
                    f = Or(BandC, notB);
                    k = "01011010100000100111100110011001";
                } else if (j < 40) {
                    String BxorC = Xor(B, C);
                    f = Xor(BxorC, D);
                    k = "01101110110110011110101110100001";
                } else if (j < 60) {
                    String BandC = And(B, C);
                    String BandD = And(B, D);
                    String CandD = And(C, D);
                    String BandCorBandD = Or(BandC, BandD);
                    f = Or(BandCorBandD, CandD);
                    k = "10001111000110111011110011011100";
                } else {
                    String BxorC = Xor(B, C);
                    f = Xor(BxorC, D);
                    k = "11001010011000101100000111010110";
                }

                String word = chunks[i].ObtainWord(j);

                String copyA = A.substring(5) + A.substring(0, 5);

                String tempA = AdunarePeBiti(copyA, f);
                String tempB = AdunarePeBiti(tempA, E);
                String tempC = AdunarePeBiti(tempB, k);
                String temp = AdunarePeBiti(tempC, word);

                E = D;
                D = C;
                C = B.substring(30) + B.substring(0, 30);
                B = A;
                A = temp;
                //System.out.println(BinToHex(A) + " " + BinToHex(B) + " " + BinToHex(C) + " " + BinToHex(D) + " "+ BinToHex(E));
            }

            h0 = AdunarePeBiti(h0, A);
            h1 = AdunarePeBiti(h1, B);
            h2 = AdunarePeBiti(h2, C);
            h3 = AdunarePeBiti(h3, D);
            h4 = AdunarePeBiti(h4, E);
        }

        Modificare = BinToHex(h0);
        SumaH = BinToHex(h0) + BinToHex(h1) + BinToHex(h2) + BinToHex(h3) + BinToHex(h4);
    }


    public void rulare() {
        TextToBin();
        padInput();
        BreakInChunks();
        LoopThroughChunks();
    }


}
