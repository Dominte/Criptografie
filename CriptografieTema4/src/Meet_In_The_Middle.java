import java.lang.reflect.Array;
import java.math.BigInteger;
import java.sql.SQLOutput;


public class Meet_In_The_Middle {
    String[] text_criptare = new String[256];
    String[] text_decriptare = new String[256];
    public int index_text_criptate;
    public int index_text_decriptate;
    public int iterator = 1;




    public  String BinToHex(String input) {
        String string = new BigInteger(input, 2).toString(16);
        while (string.length() < input.length() / 4)
            string = '0' + string;

        return string;
    }

    public String GenerateRandomKey(int number) {
        number = number % 256;

        String byteHex1 = Integer.toBinaryString(number);
        StringBuilder sb = new StringBuilder();

        while (byteHex1.length() < 8)
            byteHex1 = '0' + byteHex1;

        for (int i = 0; i < 8; i++) {
            sb.append(byteHex1);
        }
        String output=BinToHex(sb.toString());
        return output;
    }



    public void FindKeys(String plaintext1, String cryptotext1, String plaintext2, String cryptotext2) {
        DES des = new DES();
        iterator=0;
        String key1 = "1111111111111111";
        String cryptotextIntermediar=des.Criptare(plaintext1,key1);

        String Key_criptare;
        String Key_decriptare;

        for (int j = 0; j < 256; j++) {
            Key_criptare = GenerateRandomKey(j);
            text_criptare[j] = des.Criptare(plaintext1, Key_criptare);
            text_decriptare[j] = des.Decriptare(cryptotext1, Key_criptare);
            if(text_criptare[j].contentEquals(cryptotextIntermediar))
              //  System.out.println("Criptare la j " + j);
            if(text_decriptare[j].contentEquals(cryptotextIntermediar))
                System.out.println("Decriptare la j " +j);
        }


        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                if (text_criptare[i].contentEquals(text_decriptare[j])) {
                    //System.out.println(text_criptare[i] + " " + text_decriptare[j]);
                    Key_criptare = GenerateRandomKey(i);
                    Key_decriptare = GenerateRandomKey(j);

                    if (des.Criptare(plaintext2, Key_criptare).toUpperCase().contentEquals(des.Decriptare(cryptotext2, Key_decriptare).toUpperCase())) {

                        System.out.println("Perechea " + iterator + " : key1= " + Key_criptare + " key2= " + Key_decriptare);
                        iterator++;
                    }
                }

            }
        }
    }
}
