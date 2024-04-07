package WSJAVA;
import java.util.Random;

public class WilczeJagody extends Roslina{
    public WilczeJagody(int polX, int polY, Swiat swiat) {
        super(polX, polY, swiat);
        this.sila = 99;
        this.inicjatywa = 0;
        this.znak_gatunku = Stale.WILCZE_JAGODY;
        this.wiek = 0;
        this.nazwa = "WilczeJagody";
    }
    public WilczeJagody(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak_gatunku, int wiek, String nazwa, Swiat swiat) {
        super(polozenieX, polozenieY, sila, inicjatywa, znak_gatunku, wiek, nazwa, swiat);
    }

    @Override
    public void kolizja(Organizm o) {
        String px = String.valueOf(polozeniex);
        String py = String.valueOf(polozeniey);
        String p2x = String.valueOf(o.GetPolozenieX());
        String p2y = String.valueOf(o.GetPolozenieY());

        if (o.GetSila() >= sila) {
            //System.out.println(o.GetNazwa() + "(" + p2x + "," + p2y + ") zjadl " + nazwa + "(" + px + "," + py + ") i razem z nim ginie");
            swiat.WypiszZdarzenie(o.GetNazwa() + "(" + p2x + "," + p2y + ") zjadl " + nazwa + "(" + px + "," + py + ") i razem z nim ginie");
            swiat.ZabijOrganizm(o.GetPolozenieX(), o.GetPolozenieY());
            swiat.UsunOrganizm(polozeniex, polozeniey);

        } else {
            //System.out.println(o.GetNazwa() + "(" + p2x + "," + p2y + ") nadgryzl " + nazwa + "(" + px + "," + py + ") i od nich ginie");
            swiat.WypiszZdarzenie(o.GetNazwa() + "(" + p2x + "," + p2y + ") nadgryzl " + nazwa + "(" + px + "," + py + ") i od nich ginie");
            swiat.ZabijOrganizm(o.GetPolozenieX(), o.GetPolozenieY());
        }
    }

    @Override
    public void PostawDziecko(int x, int y)
    {
        Random rand = new Random();
        int szansa = rand.nextInt(Stale.RNG_WILCZE_JAGODY);
        if (szansa == 0)
        {
            //System.out.println(nazwa + "(" + polozeniex + "," + polozeniey + ") rozsiewaja sie na (" + x + "," + y + ")");
            swiat.WypiszZdarzenie(nazwa + "(" + polozeniex + "," + polozeniey + ") rozsiewaja sie na (" + x + "," + y + ")");
            swiat.DodajOrganizm(new WilczeJagody(x, y, swiat));
            //swiat.gotoxy(40, 8);
            return;
        }
        //System.out.println(nazwa + "(" + polozeniex + "," + polozeniey + ") nie rozsiewaja sie");
        swiat.WypiszZdarzenie(nazwa + "(" + polozeniex + "," + polozeniey + ") nie rozsiewaja sie");
        //swiat.gotoxy(40, 8);
    }
}
