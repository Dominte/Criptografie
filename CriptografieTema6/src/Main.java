import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        RSA rsa = new RSA();
        Random random = new Random();
        BigInteger text = new BigInteger("123");
        String criptat = rsa.Criptare(text);
        long timeCriptare=System.nanoTime();
        String decriptat = rsa.DecriptareStandard(new BigInteger(criptat));
        long timeDecriptare=System.nanoTime()-timeCriptare;
        String decriptatTCR = rsa.DecriptareTCR(new BigInteger(criptat));
        long timeDecriptareTCR=System.nanoTime()-timeDecriptare-timeCriptare;
        String attack= rsa.Wiener();

        System.out.println("Criptat: "+criptat);
        System.out.println("Rezultat decriptat STD: " +decriptat);
        System.out.println("Timp decriptare STD: "+timeDecriptare);
        System.out.println("Rezultat decriptat TCR: "+decriptatTCR);
        System.out.println("Timp decriptare TCR: "+timeDecriptareTCR);
        System.out.println("Private Exponent Wiener Attack result: "+attack);
    }


}
