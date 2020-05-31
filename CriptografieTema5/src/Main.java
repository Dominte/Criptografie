public class Main {

    public static void main(String[] args) {
        Heming heming = new Heming();
        SHA_1 sha_1 = new SHA_1();
        sha_1.setInput("abc");
        sha_1.rulare();
        System.out.println("Primii 32 de biti: " + heming.HexToBin(sha_1.Modificare));
        System.out.println("Rezultatul in hexa " + sha_1.SumaH.toUpperCase());
        String hash1 = sha_1.SumaH;
        sha_1.setInput("adc");
        sha_1.rulare();
        System.out.println("Primii 32 de biti: " + heming.HexToBin(sha_1.Modificare));
        System.out.println("Rezultatul in hexa " + sha_1.SumaH.toUpperCase());
        String hash2 = sha_1.SumaH;


        System.out.println("Distanta Heming: " + heming.CalculDistanta(hash1, hash2));

        BirthdayAttack birthdayAttack= new BirthdayAttack();

        birthdayAttack.attack(10);



    }
}
