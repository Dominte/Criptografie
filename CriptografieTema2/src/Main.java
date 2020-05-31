
import java.math.BigInteger;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        /* Blum Blum Shub
        BigInteger two= BigInteger.valueOf(2L);
        int numar_biti_creare=1;
        Blum_Blum_Shub BBS=new Blum_Blum_Shub();

        //System.out.println(BBS.generatePQ(10));
        while(true) {
            BBS.setP(BBS.generatePQ(10));
            BBS.setQ(BBS.generatePQ(10));
            BBS.setM(); // P*Q
            BBS.setSeed(); //random value
            if(BBS.getP().equals(BBS.getQ())==false)
                break;
        }
        //BBS.setSeedManual("132312312312312312312312312312312324432");
        System.out.println("Seed-ul este " + BBS.getSeed());
        System.out.println("P: "+BBS.getP());
        System.out.println("Q: "+BBS.getQ());
        System.out.println("M: "+BBS.getM());
        Random random=new Random();
        BBS.setX(); // seed*seed % M

        StringBuilder str = new StringBuilder();


        System.out.println("La pasul 0" + ' '+BBS.getX());
        for(int i=1;i<=numar_biti_creare;i++){
            BBS.setXManual(BBS.Iterare()); // X1=X0*X0%M
            System.out.println("La pasul "+ i +' '+ BBS.getX());
            str.append(BBS.getX().mod(two));
        }
        System.out.println("Primii "+ numar_biti_creare +" biti: " +str.toString()) ;

        Blum Blum Shub */

        // Jacobi
   /*      Jacobi jacobi=new Jacobi();
        BigInteger a= new BigInteger("1001");
        BigInteger n= new BigInteger("9907");  da -1*/
        long a = System.currentTimeMillis();

        for (int j = 0; j < 100; j++) { // 100 nr pe 1024 biti 40 secunde~~~

            BigInteger bit;
            int ok = 1;
            double numar_biti_creare = 1024;
            double nr0 = 0;
            double nr1 = 0;
            Blum_Blum_Shub BBS = new Blum_Blum_Shub();
            Jacobi jacobi = new Jacobi();

            while (true) {
                BBS.setP(BBS.generatePQ(1024));
                BBS.setQ(BBS.generatePQ(1024));
                BBS.setM(); // P*Q
                BBS.setSeed(); //random value
                if (BBS.getP().equals(BBS.getQ()) == false)
                    break;
            }

            //BBS.setSeedManual("953243420197123589784446454493008639234132540180320245089516317843256633471434502996135660712456910751961138191773053742349456633570797451490789829831484522048777358398103531329142229269143020478673569996464537504156386319320623921935141072852068112320093201964979925462325206404406296165460424134521725109067327879050107375323475698570390891080120600699917323759268131850222690547415098059081207669954501559311966428275947660343361974667399241481494697291867544542087728266495672839501133594157939087371490854055691378400167361035386202743299199716245639257368500478973441249365996368889339820472902952961192808439");
            System.out.println("Seed-ul este " + BBS.getSeed());
            System.out.println("P: " + BBS.getP());
            System.out.println("Q: " + BBS.getQ());
            System.out.println("M: " + BBS.getM());
            Random random = new Random();
            BBS.setX(); // seed*seed % M

            StringBuilder str = new StringBuilder();


            //System.out.println("La pasul 0" + ' ' + BBS.getSeed());
            for (int i = 1; i <= numar_biti_creare; i++) {
                BBS.IterateSeed(); // seed++
                //System.out.println("La pasul " + i + ' ' + BBS.getSeed());

                bit = jacobi.calculSimbolJacobi(BBS.getSeed(), BBS.getM());
                if (bit.equals(new BigInteger("1"))) {
                    str.append(1);
                    nr1++;
                } else if (bit.equals(new BigInteger("0")))
                    ok = 0;
                else if (bit.equals(new BigInteger("-1"))) {
                    str.append(0);
                    nr0++;
                }

                // singura modificare
            }
            if (ok == 1)
                System.out.println("Primii " + numar_biti_creare + " biti: " + str.toString());
            else
                System.out.println("Eroare");

            System.out.println("Avem 0 de " + (nr0/numar_biti_creare)*100+"%") ;
            System.out.println("Avem 1 de " + (nr1/numar_biti_creare)*100 +"%");

        /*BigInteger a= new BigInteger("1001");
        BigInteger n= new BigInteger("9907");
        bit=jacobi.calculSimbolJacobi(a,n);
        System.out.println("ULTIMUL BIT " +bit);
        System.out.println(jacobi.calculSimbolJacobi(a,n)); */
        }
        long b = System.currentTimeMillis() - a;
        System.out.println("Timpul pentru Jacobi " + b);
    }
}
