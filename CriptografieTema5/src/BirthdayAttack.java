import java.util.HashMap;

public class BirthdayAttack {

    HashMap<String, String> wordsSha1 = new HashMap<String, String>();

    public void CleanHashMap() {
        this.wordsSha1.clear();
    }

    public String generateRandomWords(int lungime) {
        StringBuilder sb = new StringBuilder();

        char character;
        for (int i = 0; i < lungime; i++) {
            int random = (int) ((Math.random() * 1000) % 26);
            character = (char) (random + 97);
            sb.append(character);
        }

        return sb.toString();
    }

    public void attack(int number) {
        SHA_1 sha_1 = new SHA_1();
        CleanHashMap();
        int i = 0;
        while (true) {
            String word = generateRandomWords(number);
            sha_1.setInput(word);

            if ((i % 1000) == 0) {
                System.out.println("Pas-ul " + i);
            }
            i++;
            sha_1.rulare();
            if (wordsSha1.containsKey(sha_1.Modificare)) {
                System.out.println("Coliziune gasita pentru cuvintele: ");
                System.out.println("Primul cuvant: " + word);
                System.out.println("Al doilea cuvant: " + wordsSha1.get(sha_1.Modificare));
                System.out.println("Primii 32 de biti din functia de Hash :" + sha_1.Modificare);
                break;
            } else
                wordsSha1.put(sha_1.Modificare, word);
        }


    }
}
