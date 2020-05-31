public class Chunk {
    public String text; //512 biti

    public String ObtainWord(int number) {
     String output = "";

        for (int i = 0; i < 32; i++) {
            output= text.substring(number*32,(number+1)*32);
        }

        return output;
    }
}
