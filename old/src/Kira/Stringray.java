public class Stringray extends MiniJava{
    public static int Menü(){
        return read("Geben Sie ein, welche Option Sie auf ihren Text anwenden möchten: \n" +
                "0: Programm beenden. \n1: Häufigkeit der Buchstaben ausgeben. \n" +
                "2: Buchstaben ersetzen. \n3: Wortweise spiegeln.");
    }

    public static boolean isLetter(char test){
        if((test>='a' && test <='z') || (test>='A' && test<='Z')){
            return true;
        } else{
            return false;
        }
    }

    public static void main(String[] args){
        String eingabe = readString("Bitte geben Sie einen Text ein.");
        if(eingabe.length()==0){
            write("Leere Eingabe. Eingabe = Ausgabe für alle Teilaufgaben.");
            return;
        }

        int auswahl = Menü();
        while(auswahl!=0){
            //Teil 1
            if(auswahl==1){
                String ausgabe = "";
                int[] häufigkeit = new int[26];
                for (int i=0; i<eingabe.length(); i++){
                    char now = eingabe.charAt(i);
                    if(now>='A' && now<='Z'){
                        häufigkeit[now - 'A'] += 1;
                    } else if(now>='a' && now<='z'){
                        häufigkeit[now - 'a'] += 1;
                    }
                }
                for(int i=0; i<häufigkeit.length; i++){
                    if(häufigkeit[i]!=0){
                        ausgabe += (char)('A'+i) + ": " + häufigkeit[i] + "  ";
                    }
                }
                write(ausgabe);
            }

            //Teil 2
            if(auswahl==2){
                char alt;
                char neu;
                String austauschen;

                //Eingabe der beiden Buchstaben
                do {
                    austauschen = readString("Geben Sie einen Buchstaben ein, der ausgetauscht werden soll.");
                } while (austauschen.length()!=1 || !isLetter(austauschen.charAt(0)));
                alt = austauschen.charAt(0);
                do {
                    austauschen = readString("Geben Sie den Buchstaben ein, durch den ausgetauscht werden soll.");
                } while (austauschen.length()!=1 || !isLetter(austauschen.charAt(0)));
                neu = austauschen.charAt(0);

                //neu und alt sollen Kleinbuchstaben sein
                if(alt>'Z'){
                    alt -= ('a' - 'A');
                }
                if(neu>'Z'){
                    neu -= ('a' - 'A');
                }

                //Text anpassen
                String ausgabe = "";
                for (int i=0; i<eingabe.length(); i++){
                    if(eingabe.charAt(i)==alt){
                        ausgabe += (char)neu;
                    } else if(eingabe.charAt(i)==alt+('a'-'A')){
                        ausgabe += (char)(neu+('a'-'A'));
                    } else{
                        ausgabe += eingabe.charAt(i);
                    }
                }
                write(ausgabe);
            }

            //Teil 3
            if (auswahl == 3) {
                String ausgabe = "";
                String wort = "";
                for (int i=0; i<eingabe.length(); i++){
                    if(eingabe.charAt(i)==' '){
                        for (int j=0; j<wort.length(); j++){
                            ausgabe += wort.charAt(wort.length()-1-j);
                        }
                        ausgabe += ' ';
                        wort = "";
                    } else {
                        wort += eingabe.charAt(i);
                    }
                }
                for (int j=0; j<wort.length(); j++){
                    ausgabe += wort.charAt(wort.length()-1-j);
                }
                ausgabe += ' ';
                write(ausgabe);
            }

            auswahl=Menü();
        }
    }
}
