public class RC4 {
    //public char[] Key;
    public int[] S = new int[256];
    public int index_i;
    public int index_j;
    public char bit;


    public void prepare(char[] Key) {
        //prep
        int lungime = Key.length;
        bit = 0;
        int j = 0;


        for (int i = 0; i < 256; i++) { // s0=id
            S[i] = i;
        }

        for (int i = 0; i < 256; i++) {
            j = (j + S[i] + Key[i % lungime]) % 256;

            int copy = S[i];
            S[i] = S[j];
            S[j] = copy; // swap s[i] s[j]
        }
        setIndex_i(0);
        setIndex_j(0);
        //return S;
    }

    public char run() {

        index_i = (index_i + 1) % 256;
        index_j = (index_j + S[index_i]) % 256;


        int copy = S[index_i]; // swap
        S[index_i] = S[index_j];
        S[index_j] = copy;

        //kept internally

        return (char) (S[(S[index_i] + S[index_j]) % 256]);
        //setBit(S[(S[index_i] + S[index_j] ) % 256]);
    }

    public void setBit(char bit) {
        this.bit = bit;
    }

    public char getBit() {
        return bit;
    }

    public int getIndex_i() {
        return index_i;
    }

    public void setIndex_i(int index_i) {
        this.index_i = index_i;
    }

    public int getIndex_j() {
        return index_j;
    }

    public void setIndex_j(int index_j) {
        this.index_j = index_j;
    }


}
