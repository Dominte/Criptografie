public class LSFR {
    public int[] register;
    public boolean[] booleans;

    public int feedback(){
        int sum=0;
        for(int i=0;i<register.length;i++){
            if(booleans[i]){
                sum=sum+register[i];
            }
        }
        return sum%2;
    }

    public void permutare(){
        int save=feedback();
        for(int i=0;i<register.length-1;i++){
            register[i]=register[i+1];
        }
        register[register.length-1]=save;
    }

    public int iterare(){
        int output=register[0];

        permutare();

        return output;
    }


    public void setRegister(int[] register) {
        this.register = register;
    }

    public void setBooleans(boolean[] booleans) {
        this.booleans = booleans;
    }



}
