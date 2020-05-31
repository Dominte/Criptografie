import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RSA {
    public BigInteger p1;
    public BigInteger p2;
    public BigInteger rezultatP1;
    public BigInteger rezultatP2;
    public BigInteger n;
    public BigInteger a;
    public BigInteger b;
    public List<BigInteger> q = new ArrayList<>();
    public BigInteger phi;
    public BigInteger publicExponent;
    public BigInteger d;
    public List<BigInteger> alfa = new ArrayList<>();
    public List<BigInteger> beta = new ArrayList<>();
    public BigInteger n1;
    public BigInteger n2;
    public BigInteger x1;
    public BigInteger x2;
    public BigInteger x;
    public BigInteger l;
    public BigInteger m1;
    public BigInteger m2;
    public BigInteger c;
    public BigInteger privateExponent;
    public BigInteger e;

    public final BigInteger one = new BigInteger("1");

    public void Prepare() {
        Random random = new Random();
        p1 = new BigInteger(1024, 100, random);
        p2 = new BigInteger(1024, 100, random);
        phi = (p1.subtract(one)).multiply(p2.subtract(one));
        n = p1.multiply(p2);
        publicExponent = new BigInteger(32, 100, random);
        privateExponent = publicExponent.modInverse(phi);
    }

    public String Criptare(BigInteger text) {
        Prepare();
        c = text.modPow(publicExponent, n);
        return c.toString();
    }

    public String DecriptareStandard(BigInteger text) {
        return (text.modPow(privateExponent, n)).toString();
    }

    public String DecriptareTCR(BigInteger text) {
        m1 = p1;
        m2 = p2;

        n1 = privateExponent.mod(m1.subtract(one));
        n2 = privateExponent.mod(m2.subtract(one));

        x1 = (text.mod(m1)).modPow(n1, m1);
        x2 = (text.mod(m2)).modPow(n2, m2);
        x = x1.add(m1.multiply((x2.subtract(x1)).multiply(m1.modPow(BigInteger.ZERO.subtract(BigInteger.valueOf(1)), m2))).mod(m2));

        return x.toString();
    }

    public String Wiener() {
        Random random = new Random();
        d = n;
        while ((((d.multiply(new BigInteger("3"))).pow(4)).compareTo(n)) > 0) {
            d = new BigInteger(32, 100, random);
        }
        e = d.modInverse(phi);

        a = e;
        b = n;
        System.out.println("Private Exponent " + d.toString());

        BigInteger dGenerat;


        int i = 0;

        do {
            q.add(a.divide(b));
            BigInteger r = a.mod(b);
            a = b;
            b = r;

            if (i > 1) {
                alfa.add((q.get(i).multiply(alfa.get(i - 1))).add(alfa.get(i - 2)));
                beta.add((q.get(i).multiply(beta.get(i - 1))).add(beta.get(i - 2)));
            } else {
                if (i == 0) {
                    beta.add(one);
                    alfa.add(q.get(i));
                } else if (i == 1) {
                    beta.add(q.get(i));
                    alfa.add((q.get(i).multiply(q.get(i - 1)).add(one)));
                }
            }

            l = alfa.get(i);
            dGenerat = beta.get(i);

            i++;

        } while (criteriu(l, dGenerat) == 0);

        return dGenerat.toString();
    }

    public int criteriu(BigInteger functiel, BigInteger functied) {

        if (functiel.compareTo(BigInteger.ZERO) > 0) {
            if ((e.multiply(functied).subtract(one)).mod(functiel).equals(BigInteger.ZERO)) {


                BigInteger fi = (e.multiply(functied).subtract(one)).divide(functiel);
                BigInteger b = fi.subtract(n).subtract(one);
                BigInteger delta = (b.pow(2)).subtract((n.multiply(BigInteger.valueOf(4))));
                BigInteger sqrtDelta = sqrt(delta);
                rezultatP1 = ((BigInteger.ZERO.subtract(b)).subtract(sqrtDelta)).divide(new BigInteger("2"));
                rezultatP2 = ((BigInteger.ZERO.subtract(b)).add(sqrtDelta)).divide(new BigInteger("2"));

                if ((rezultatP1.multiply(rezultatP2)).equals(n)) {
                    return 1;
                }
            }
        }

        return 0;
    }

    // https://stackoverflow.com/questions/4407839/how-can-i-find-the-square-root-of-a-java-biginteger sursa functiei
    public static BigInteger sqrt(BigInteger x) { // functia sqrt nu exista in java 8 in clasa BigInteger
        BigInteger div = BigInteger.ZERO.setBit(x.bitLength() / 2);
        BigInteger div2 = div;
        for (; ; ) {
            BigInteger y = div.add(x.divide(div)).shiftRight(1);
            if (y.equals(div) || y.equals(div2))
                return y;
            div2 = div;
            div = y;
        }
    }
}
