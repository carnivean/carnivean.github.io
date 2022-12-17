public class XOR extends MiniJava{
    public static boolean validInput (char test) {
        if(test>='A' && test<='Z'){
            return true;
        } else if (test>='a' && test<='z'){
            return true;
        } else if (test == ' '){
            return true;
        } else if (test == '.'){
            return true;
        } else if(test>='0' && test <= '9'){
            return true;
        } else {
            return false;
        }
    }

    public static String IntArrZuText (int[]encoded){
        String output = "";
        for (int i=0; i<encoded.length; i++){
            if(encoded[i]>=0 && encoded[i]<=25){
                output += (char)('a'+encoded[i]);
            } else if(encoded[i]>=26 && encoded[i]<=51){
                output += (char)('A'+(encoded[i]-26));
            } else if(encoded[i]>=52 && encoded[i]<=61){
                output += (char)('0'+(encoded[i]-52));
            } else if(encoded[i]==62){
                output += ' ';
            } else if(encoded[i]==63){
                output += '.';
            }
        }
        return output;
    }

    public static void main (String [] args){
        int key;
        int vector;

        //Schlüssel und Initialisierungsvektor einlesen
        do {
            key = read("Bitte geben Sie einen Schlüssel ein.");
        } while (key<0 || key>63);
        do{
            vector = read("Bitte geben Sie einen Initialisierungsvektor ein.");
        } while (vector<0 || vector>63);

        //Text einlesen
        String input = readString("Bitte geben Sie einen Text ein, der verschlüsselt werden soll.");
        for (int i=0; i<input.length(); i++){
            if(!validInput(input.charAt(i))){
                write("Die Eingabe ist ungültig");
                return;
            }
        }
        if(input.length()==0){
            return;
        }

        //Kodierung
        int[] encoded = new int[input.length()];
        for(int i=0; i<input.length(); i++){
            if(input.charAt(i)>='a' && input.charAt(i)<='z'){
                encoded[i] = (int)(input.charAt(i)-'a');
            } else if(input.charAt(i)>='A' && input.charAt(i)<='Z'){
                encoded[i] = (int)(input.charAt(i)-'A'+26);
            } else if(input.charAt(i)>='0' && input.charAt(i)<='9'){
                encoded[i] = (int)(input.charAt(i)-'0'+52);
            } else if(input.charAt(i)==' '){
                encoded[i] = 62;
            } else if (input.charAt(i)=='.'){
                encoded[i] = 63;
            }
        }

        //Text verschlüsseln
        encoded[0] = (encoded[0]^vector)^key;
        for(int i=1; i<encoded.length; i++){
            encoded[i] = (encoded[i-1]^encoded[i])^key;
        }

        //Dekodierung
        String decoded = IntArrZuText(encoded);

        //Ausgabe
        write("Der verschlüsselte Text: " + decoded);

        //Entschlüsseln
        int[] entschlüsselt = new int[encoded.length];
        for (int i=1; i<encoded.length; i++){
            entschlüsselt[entschlüsselt.length-i] = (encoded[entschlüsselt.length-i]^key)^encoded[entschlüsselt.length-i-1];
        }
        entschlüsselt[0] = (encoded[0]^key)^vector;

        String output = IntArrZuText(entschlüsselt);
        write ("Der Originaltext lautet: " + output);
    }
}
