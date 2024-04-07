package WSJAVA;
import java.util.Random;

public class Mlecz extends Roslina{
    public Mlecz(int polX, int polY, Swiat swiat) {
        super(polX, polY, swiat);
        this.sila = 0;
        this.inicjatywa = 0;
        this.znak_gatunku = Stale.MLECZ;
        this.wiek = 0;
        this.nazwa = "Mlecz";
    }
    public Mlecz(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak_gatunku, int wiek, String nazwa, Swiat swiat) {
        super(polozenieX, polozenieY, sila, inicjatywa, znak_gatunku, wiek, nazwa, swiat);
    }

    @Override
    public void akcja()
    {
        for(int i = 0; i<3; i++)
            super.akcja();
    }

    @Override
    public void PostawDziecko(int x, int y)
    {
        Random rand = new Random();
        int szansa = rand.nextInt(Stale.RNG_MLECZ);
        if (szansa == 0)
        {
            //System.out.println(nazwa + "(" + polozeniex + "," + polozeniey + ") rozsiewa sie na (" + x + "," + y + ")");
            swiat.WypiszZdarzenie(nazwa + "(" + polozeniex + "," + polozeniey + ") rozsiewa sie na (" + x + "," + y + ")");
            swiat.DodajOrganizm(new Mlecz(x, y, swiat));
            //swiat.gotoxy(40, 8);
            return;
        }
        //.out.println(nazwa + "(" + polozeniex + "," + polozeniey + ") nie rozsiewa sie");
        swiat.WypiszZdarzenie(nazwa + "(" + polozeniex + "," + polozeniey + ") nie rozsiewa sie");
        //swiat.gotoxy(40, 8);
    }
}
