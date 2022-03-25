public class Samochod extends Pojazd implements Ruch{

    Samochod(String marka, int vmax){
        this.marka = marka;
        this.vmax = vmax;
    }


    @Override
    void wyswietlInformacje() {
        System.out.println("Pojazd marki " + marka + ". Predkość maksymalna: " + (vmax) + "km/h");
    }

    @Override
    public void jedzProsto() {
        System.out.println("[Samochód] Porusza się do przodu.");
    }
}
