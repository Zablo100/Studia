public class Autobus extends Pojazd implements Ruch{

    Autobus(String marka, int vmax){
        this.marka = marka;
        this.vmax = vmax;
    }


    @Override
    void wyswietlInformacje() {
        System.out.println("Autobus marki " + marka + ". Predkość maksymalna: " + (vmax) + "km/h");
    }

    @Override
    public void jedzProsto() {
        System.out.println("[Autobus] Porusza się do przodu.");
    }
}