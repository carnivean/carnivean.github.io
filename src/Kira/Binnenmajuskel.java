public class Binnenmajuskel extends MiniJava{
    public static void main (String[] args){
        String eingabe = "";
        String ausgabe = "";
        String neu = readString();
        boolean korrekt;
        while (neu.length()!=0){
            korrekt = true;
            for (int i=0; i<neu.length(); i++){
                if(!Character.isLetter(neu.charAt(i))){
                    korrekt = false;
                }
            }
            if(korrekt){
                eingabe += " " + neu;
            }
            neu = readString();
        }
        if(eingabe.length() == 0){
            return;
        }
        eingabe = eingabe.toLowerCase();

        //Startcase
        ausgabe += Character.toUpperCase(eingabe.charAt(1));
        for (int i=2; i<eingabe.length(); i++){
            if(eingabe.charAt(i)!=' '){
                ausgabe += eingabe.charAt(i);
            }
        }
        write("Startcase: " + ausgabe);

        //Uppercase
        ausgabe = ausgabe.toUpperCase();
        write("UPPERCASE: " + ausgabe);
        ausgabe = "";

        //snake_case
        for (int i=1; i<eingabe.length(); i++){
            if(eingabe.charAt(i)==' '){
                ausgabe += '_';
            } else {
                ausgabe += eingabe.charAt(i);
            }
        }
        write("snake_case: " + ausgabe);
        ausgabe = "";

        //PascalCase
        for (int i=0; i<eingabe.length(); i++){
            if(eingabe.charAt(i)==' '){
                i++;
                ausgabe += Character.toUpperCase(eingabe.charAt(i));
            } else {
                ausgabe += eingabe.charAt(i);
            }
        }
        write("PascalCase: " + ausgabe);
    }
}
