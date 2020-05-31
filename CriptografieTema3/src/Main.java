import java.util.Random;

public class Main {

    public static void main(String[] args) {
      /*  // write your code here

        char[] key = "Cheie".toCharArray();

        char[] text = "õÎ`J\u009B:gYÓ¿ªo] \u0084.ÓX\u0006³z¥6\u00AD;\u0099¼éNM÷å\u008Fû\u0098k.".toCharArray();

        StringBuilder str = new StringBuilder();



        int[] frecventa = new int[256];



        Random random = new Random();
        //System.out.println("I-ul: "+rc4.index_i);
        //System.out.println("J-ul: "+rc4.index_j);

        for (int i = 0; i < 65536  ; i++) { // 2^16


            int lungime = 5 + random.nextInt(12); //5 16
            //System.out.println("Lungime "+ lungime);
            char[] keyRandom = new char[lungime];
            //System.out.print("Cheia ");
            for (int k = 0; k < keyRandom.length; k++) {
                keyRandom[k] = (char) (random.nextInt(256));
                //System.out.print(keyRandom[k]);
            }
            //System.out.println("\n");

            RC4 rc4 = new RC4();
            rc4.prepare(keyRandom);
            char bit;
            for (int j = 0; j < 256; j ++) {
                bit=rc4.run();
                if (bit == 0x00)
                    frecventa[j]++;
                //    str.append((char)(rc4.getBit()^text[i]));
            }
        }

        for (int l = 0; l < 256; l++) {
            System.out.println("Frecventa " + (1+l) + " este: " + frecventa[l]);
        }
        //System.out.println("Bitii: " + str.toString() );

        RC4 rc4=new RC4();
        rc4.prepare(key);
        char bit;
        for(int i=0;i<text.length;i++){
            bit=rc4.run();
            System.out.print((char)(bit^text[i]));
           // str.append((char)(bit^text[i]));
        }
       // System.out.println("Bitii: " + str.toString() );


         */

      //LSFR

        int[] register={1,0,1,1,0,0,0,1,1};
        boolean[] booleans={true,true,false,true,true,false,true,false,false};

        LSFR lsfr=new LSFR();
        lsfr.setBooleans(booleans);
        lsfr.setRegister(register);
        System.out.print("Output-ul este: ");
        for(int i=0;i<20;i++){
            int output;
            output=lsfr.iterare();
            System.out.print(output);

        }

        //LSFR

    }
}
