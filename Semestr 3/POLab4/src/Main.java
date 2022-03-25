public class Main {
    public static void main(String[] args){
        Samochod bmw = new Samochod("BMW", 240);
        Motocykl yamaha = new Motocykl("Yamaha", 280);
        Autobus solaris = new Autobus("Solaris", 110);

        Pojazd[] pojazdy = {bmw, yamaha, solaris};

        for(Pojazd p : pojazdy){
            p.wyswietlInformacje();
        }
        System.out.println(" ");

        Ruch[] jazda = {bmw, yamaha, solaris};

        for(Ruch r : jazda){
            r.jedzProsto();
        }


    }
}
