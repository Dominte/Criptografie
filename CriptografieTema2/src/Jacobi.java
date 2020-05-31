import java.math.BigInteger;

public class Jacobi extends Blum_Blum_Shub{


    public static BigInteger calculSimbolJacobi(BigInteger a, BigInteger n) {

        BigInteger b = a.mod(n); // a mod n
        BigInteger c = n;
        BigInteger two = BigInteger.valueOf(2L);

        if(n.mod(two).equals(new BigInteger("0")))
        {
            System.out.println("N trebuie sa fie impar");
            return new BigInteger("0");
        }
        //System.out.println("N-ul este "+ n);
        //System.out.println("A-ul este "+ a);
        //System.out.println("B-ul este "+ b);

        int s = 1;

        BigInteger four = BigInteger.valueOf(4L);
        BigInteger eight = BigInteger.valueOf(8L);
        BigInteger three = BigInteger.valueOf(3L);
        BigInteger five = BigInteger.valueOf(5L);
        BigInteger one = BigInteger.valueOf(1L);

        while (b.compareTo(new BigInteger("2")) >= 0) {  // while b>=2
            while (b.mod(four).equals(new BigInteger("0"))) {
                b = b.divide(four);

            }

            if (b.mod(two).equals(new BigInteger("0"))) { //if 2|b then
                if (c.mod(eight).equals(three) || c.mod(eight).equals(five)) {
                    s = s * -1;

                }

                b = b.divide(two);
            }

            if (b.equals(new BigInteger("1")))
                break;

            if (b.mod(four).equals(new BigInteger("1")) || c.mod(four).equals(new BigInteger("1"))) {
                BigInteger copy = new BigInteger(String.valueOf(b));
                b = c.mod(b);
                c = copy;


            }

            if (b.mod(BigInteger.valueOf(4L)).equals(BigInteger.valueOf(3L)) && c.mod(BigInteger.valueOf(4L)).equals(BigInteger.valueOf(3L))){
                s = s * -1;
                BigInteger copy = new BigInteger(String.valueOf(b));
                b = c.mod(b);
                c = copy;
            }


        }
        //System.out.println("Bit-ul este "+ b.multiply(BigInteger.valueOf(s)));
        return b.multiply(BigInteger.valueOf(s));
    }


}
