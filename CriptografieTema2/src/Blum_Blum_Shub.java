import java.util.Date;
import java.util.Random;
import java.security.SecureRandom;
import java.math.BigInteger;

public class Blum_Blum_Shub extends Random {

    BigInteger p = new BigInteger("0");
    BigInteger q = new BigInteger("0");
    BigInteger M = new BigInteger("0");
    BigInteger seed = new BigInteger("0");
    BigInteger X = new BigInteger("0");

    public BigInteger getX() {
        return X;
    }

    public void setX() {
        X=seed.multiply(seed).mod(M);
    }

    //static pt big numbers indicat
    public static BigInteger generatePQ(int biti)
    {
        Random random=new Random();
        BigInteger bigInteger= new BigInteger("0");
        BigInteger three = BigInteger.valueOf(3L);
        BigInteger four = BigInteger.valueOf(4L);
        int ok=1;
        while(ok==1){
            bigInteger= new BigInteger(biti, 100, random); //100 % prim
            if(bigInteger.mod(four).equals(three))
                ok=0;
        }
        return bigInteger;
    }

    public Blum_Blum_Shub() {

    }

    public Blum_Blum_Shub(BigInteger p, BigInteger q, BigInteger seed) {
        this.p = p;
        this.q = q;
        this.M = p.multiply(q);
        this.seed = seed;
    }
    public BigInteger Iterare(){
        return X.multiply(X).mod(this.M);
    }
    public void setXManual(BigInteger X){
        this.X=X.mod(this.M);
    }

    public BigInteger getSeed() {
        return seed;
    }

    public void setSeed() {
        Date date = new Date();
        BigInteger timeMilli= new BigInteger(String.valueOf(date.getTime()));
        this.seed = timeMilli.mod(getM());
    }
    public void setSeedManual(String seed){
        this.seed=new BigInteger(seed).mod(this.M);
    }

    public BigInteger getP() {
        return p;
    }

    public void setP(BigInteger p) {
        this.p = p;
    }

    public void IterateSeed(){
        this.seed=this.seed.add(new BigInteger("1"));
    }

    public BigInteger getQ() {
        return q;
    }

    public void setQ(BigInteger q) {
        this.q = q;
    }

    public BigInteger getM() {
        return M;
    }

    public void setM() {
        M = this.p.multiply(this.q);
    }
}
