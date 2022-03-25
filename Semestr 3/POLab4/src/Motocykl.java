public class Motocykl extends Pojazd implements Ruch{

    Motocykl(String marka, int vmax){
        this.marka = marka;
        this.vmax = vmax;
    }


    @Override
    void wyswietlInformacje() {
        System.out.println("Motocykl marki " + marka + ". Predkość maksymalna: " + (vmax) + "km/h");
    }

    @Override
    public void jedzProsto() {
        System.out.println("[Motocykl] Porusza się do przodu.");
    }
}