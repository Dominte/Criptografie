import java.math.BigInteger;

public class Heming {

    public  String HexToBin(String input) {
        String string = new BigInteger(input, 16).toString(2);
        while (string.length() < input.length() * 4)
            string = '0' + string;

        return string;
    }


    public String TextToBin(String a) {
        StringBuilder sb = new StringBuilder();
        String character = "";

        for (int i = 0; i < a.length(); i++) {
            character = Integer.toBinaryString(a.charAt(i));

            while (character.length() < 8)
                character = '0' + character;

            sb.append(character);
        }

        return sb.toString();

    }

    public int CalculDistanta(String a, String b) {
        int d = 0;
        String copyA= HexToBin(a);
        String copyB= HexToBin(b);
        for (int i = 0; i < copyA.length(); i++) {
            if(copyA.charAt(i)!=copyB.charAt(i))
                d++;
        }

        return d;
    }

}
