package cz.fei.upce.cv05.evidence.chatek;

import java.util.Scanner;

public class EvidenceChatekApp {

    // Konstanty pro definovani jednotlivych operaci (pouze pro cisty kod)
    static final int KONEC_PROGRAMU = 0;
    static final int VYPIS_CHATEK = 1;
    static final int VYPIS_KONKRETNI_CHATKU = 2;
    static final int PRIDANI_NAVSTEVNIKU = 3;
    static final int ODEBRANI_NAVSTEVNIKU = 4;
    static final int CELKOVA_OBSAZENOST = 5;
    static final int VYPIS_PRAZDNE_CHATKY = 6;

    static final int VELIKOST_KEMPU = 5;
    static final int MAX_VELIKOST_CHATKY = 10;
    
    static final int CHYBA_V_CISLE_CHATKY = -1;
    
    static Scanner scanner = new Scanner(System.in);

    static int[] chatky = new int[VELIKOST_KEMPU];

        
    public static void main(String[] args) {
        
        // Definovani pole podle velikosti kempu (poctu chatek)
        int operace;

        do {
            System.out.println("""
                    MENU:
                                        
                    1 - vypsani vsech chatek
                    2 - vypsani konkretni chatky
                    3 - Pridani navstevniku
                    4 - Odebrani navstevniku
                    5 - Celkova obsazenost kempu
                    6 - Vypis prazdne chatky
                    0 - Konec programu
                    """);

            // Ziskani operace od uzivatele
            System.out.print("Zadej volbu: ");
            operace = scanner.nextInt();

            switch (operace) {
                case VYPIS_CHATEK -> vypisChatky();
                case VYPIS_KONKRETNI_CHATKU -> vypisJedneChatky();                 
                case PRIDANI_NAVSTEVNIKU ->  pridejNavstevnika();
                case ODEBRANI_NAVSTEVNIKU -> odebraniNavstevnika();
                case CELKOVA_OBSAZENOST -> celkovaObsazenost();
                case VYPIS_PRAZDNE_CHATKY -> vypisPrazdnychChatek();
                case KONEC_PROGRAMU -> {
                    System.out.println("Konec programu");
                }
                default -> {
                    System.err.println("Neplatna volba");
                }
            }
        } while (operace != 0);
    }

    private static void pridejNavstevnika() {
        int indexChatky = zadaniCislaChatky();
        if (kontrolaCislaChatky(indexChatky) == false) {
            System.err.println("Tato chatka neexistuje");
            return;  
        }
        
        System.out.print("Zadej pocet navstevniku: ");
        int pocetNavstevniku = scanner.nextInt();
        
        if (pridejNavstevniky(indexChatky, pocetNavstevniku)) 
            return;
        chatky[indexChatky] = pocetNavstevniku + chatky[indexChatky];
    }

    private static boolean pridejNavstevniky(int chatka, int pocetNavstevniku) {
        if (chatky[chatka] + pocetNavstevniku > MAX_VELIKOST_CHATKY) {
            System.err.println("Prekrocen maximalni pocet navstevniku chatky");
            return false;
        }
        chatky[chatka] += pocetNavstevniku;
        return true;
    }

    private static boolean kontrolaObsazenostiChatky(int pocetNavstevniku) {
        // Zaporne cislo nebo prilis velky nevalidni vstup
        if (pocetNavstevniku <= 0 || pocetNavstevniku > MAX_VELIKOST_CHATKY) {
            System.err.println("Neplatna hodnota pro pocet navstevniku");
            return false;
        }
        return true;
    }

    private static void vypisJedneChatky() {
        int indexChatky = zadaniCislaChatky();
        
        if (kontrolaCislaChatky(indexChatky) == false) {
            System.err.println("Tato chatka neexistuje");
            return;  
        } 
        System.out.println("Chatka [" + (indexChatky + 1) + "] = " + chatky[indexChatky]);
  
    }

    /**
     * Projdi cele pole od 0, VELIKOST) a vypis kazdy index
     */
    private static void vypisChatky() {
        for (int i = 0; i < chatky.length; i++) {
            System.out.println("Chatka [" + (i + 1) + "] = " + chatky[i]);
        }
    }

    private static void odebraniNavstevnika() {
        int cisloChatky = zadaniCislaChatky();
        if (cisloChatky == CHYBA_V_CISLE_CHATKY)
            return;
    }

    private static int zadaniCislaChatky() {
        System.out.print("Zadej cislo chatky: ");
        int indexChatky = scanner.nextInt() - 1;
        return indexChatky;
    }

    private static void celkovaObsazenost() {
        
    }

    private static void vypisPrazdnychChatek() {
        
    }
    
    private static boolean kontrolaCislaChatky(int cisloChatky) {
        return cisloChatky < 0 || cisloChatky >= chatky.length;
    }
}
