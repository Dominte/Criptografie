import java.awt.datatransfer.MimeTypeParseException;

public class Main {

    public static void main(String[] args) {
        DES des = new DES();
        String string = "100001";
        //System.out.println(DES.HexToBin(string));
        //System.out.println(DES.HexToBin(des.LeftShift(des.LeftShift(string))));

        //System.out.println(des.Xor("00101", "1111"));
        System.out.println();

        String key1 = "CCCCCCCCCCCCCCCC";
        String key2 = "FAFAFAFAFAFAFAFA";

        String text1 = "0123456789ABCDEF";
        String cryptotext1 = des.Criptare(des.Criptare(text1,key1),key2);
        String text2 = "A6D32C2394234FED";
        String cryptotext2 = des.Criptare(des.Criptare(text2,key1),key2);

        DES cipher = new DES();

        text1 = cipher.Criptare(text1, key1);
        System.out.println("Text1 criptat cu key1: " + text1.toUpperCase() + " exemplul de pe site");

        text1 = cipher.Criptare(text1, key2);
        System.out.println("Text1 criptat cu key2: " + text1.toUpperCase());

        text2 = cipher.Criptare(text2, key1);
        System.out.println("Text2 criptat cu key1: " + text2.toUpperCase());

        text2 = cipher.Criptare(text2, key2);
        System.out.println("Text2 criptat cu key2: " + text2.toUpperCase());

        Meet_In_The_Middle MITM = new Meet_In_The_Middle();

        key1 = MITM.GenerateRandomKey((int) (Math.random()*1000%256));
        key2 = MITM.GenerateRandomKey((int) (Math.random()*1000%256));

        key1=MITM.GenerateRandomKey(7);
        key2=MITM.GenerateRandomKey(255);

        text1 = "0123456789ABCDEF";
        cryptotext1 = des.Criptare(des.Criptare(text1,key1),key2);
        text2 = "A6F32C2396934FED";
        cryptotext2 = des.Criptare(des.Criptare(text2,key1),key2);


        System.out.println(MITM.GenerateRandomKey(1));
        System.out.println("Text 1 " +text1);
        System.out.println("Cryptotext 1 " +cryptotext1);
        System.out.println("Text 2 " +text2);
        System.out.println("Cryptotext 2 " +cryptotext2);
        MITM.FindKeys(text1,cryptotext1,text2,cryptotext2);

        //System.out.println(des.Decriptare(des.Criptare(text1,key1),key1));

        System.out.println("Key1 cu care am critpat: " +key1);
        System.out.println("Key2 cu care am decriptat: " +key2);
    }
}
