package WSJAVA;
import java.util.Random;

public class Zolw extends Zwierze{
    public Zolw(int polX, int polY, Swiat swiat) {
        super(polX, polY, swiat);
        this.sila = 2;
        this.inicjatywa = 1;
        this.znak_gatunku = Stale.ZOLW;
        this.wiek = 0;
        this.nazwa = "Zolw";
    }
    public Zolw(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak_gatunku, int wiek, String nazwa, Swiat swiat) {
        super(polozenieX, polozenieY, sila, inicjatywa, znak_gatunku, wiek, nazwa, swiat);
    }

    @Override
    public void akcja()
    {
        //funkcja losująca w którą stronę organizm ma się poruszyć, jeśli na tym miejscu stoi inny organizm to wywołuje jego funkcje kolizja
        Random rand = new Random();
        int szansa = rand.nextInt(4);
        if (szansa != 0)
        {
            //System.out.println(nazwa + "(" + polozeniex + "," + polozeniey + ") nie rusza sie w tej turze");
            swiat.WypiszZdarzenie(nazwa + "(" + polozeniex + "," + polozeniey + ") nie rusza sie w tej turze");
            return;
        }
        else
        {
            int x = polozeniex;
            int y = polozeniey;
            int kierunek;
            while (true)
            {
                kierunek = rand.nextInt(4);
                if (kierunek == 0)
                {
                    if (y > 0)
                    {
                        y--;
                        break;
                    }
                }
                else if (kierunek == 1)
                {
                    if (y < swiat.GetRozmiarY() - 1)
                    {
                        y++;
                        break;
                    }
                }
                else if (kierunek == 2)
                {
                    if (x > 0)
                    {
                        x--;
                        break;
                    }
                }
                else
                {
                    if (x < swiat.GetRozmiarX() - 1)
                    {
                        x++;
                        break;
                    }
                }
            }
            if (swiat.GetOrganizm(x, y) != null)
            {
                swiat.GetOrganizm(x, y).kolizja(this); //this wjechal w organizm na polu x,y
                if (wiek != -1 && swiat.GetOrganizm(x, y) == null)
                {
                    //System.out.println(nazwa + "(" + polozeniex + "," + polozeniey + ") przemiescil sie na (" + x + "," + y + ")");
                    swiat.WypiszZdarzenie(nazwa + "(" + polozeniex + "," + polozeniey + ") przemiescil sie na (" + x + "," + y + ")");
                    polozeniex = x; // jesli this nie zostal zjedzony ORAZ zjadl to zmien polozenie
                    polozeniey = y;
                }
                if (wiek == -1)
                {
                    swiat.UsunOrganizm(polozeniex, polozeniey); // jesli this zostal zjedzony to go usun
                }
            }
            else
            {
                //System.out.println(nazwa + "(" + polozeniex + "," + polozeniey + ") przemiescil sie na (" + x + "," + y + ")");
                swiat.WypiszZdarzenie(nazwa + "(" + polozeniex + "," + polozeniey + ") przemiescil sie na (" + x + "," + y + ")");
                polozeniex = x;
                polozeniey = y;
            }
        }
    }

    @Override
    public void kolizja(Organizm o) // o wjechal w this
    {
        if (o.GetNazwa().equals(nazwa))
        {
            if (wiek > 0)
                Rozmnazanie(o);
            else
            {
                String px = String.valueOf(polozeniex);
                String py = String.valueOf(polozeniey);
                String p2x = String.valueOf(o.GetPolozenieX());
                String p2y = String.valueOf(o.GetPolozenieY());
                //System.out.println(o.GetNazwa() + "(" + p2x + "," + p2y + ") probowal rozmnozyc sie z " + nazwa + "(" + px + "," + py + "), ale drugi jest za mlody");
                swiat.WypiszZdarzenie(o.GetNazwa() + "(" + p2x + "," + p2y + ") probowal rozmnozyc sie z " + nazwa + "(" + px + "," + py + "), ale drugi jest za mlody");

                return;
            }
        }
        else
        {
            String px = String.valueOf(polozeniex);
            String py = String.valueOf(polozeniey);
            String p2x = String.valueOf(o.GetPolozenieX());
            String p2y = String.valueOf(o.GetPolozenieY());
            if (o.GetSila() >= sila)
            {
                if (o.GetSila() < 5)
                {
                    //System.out.println(o.GetNazwa() + "(" + p2x + "," + p2y + ") zaatakowal " + nazwa + "(" + px + "," + py + "), ale on odpiera jego atak, bo ma sile < 5");
                    swiat.WypiszZdarzenie(o.GetNazwa() + "(" + p2x + "," + p2y + ") zaatakowal " + nazwa + "(" + px + "," + py + "), ale on odpiera jego atak, bo ma sile < 5");
                    return;
                }
                //System.out.println(o.GetNazwa() + "(" + p2x + "," + p2y + ") zabija " + nazwa + "(" + px + "," + py + ")");
                swiat.WypiszZdarzenie(o.GetNazwa() + "(" + p2x + "," + p2y + ") zabija " + nazwa + "(" + px + "," + py + ")");
                swiat.ZabijOrganizm(polozeniex, polozeniey); // zabicie this
                swiat.UsunOrganizm(polozeniex, polozeniey); //usuniecie this z planszy
            }
            else
            {
                //System.out.println(o.GetNazwa() + "(" + p2x + "," + p2y + ") ginie od " + nazwa + "(" + px + "," + py + ")");
                swiat.WypiszZdarzenie(o.GetNazwa() + "(" + p2x + "," + p2y + ") ginie od " + nazwa + "(" + px + "," + py + ")");
                swiat.ZabijOrganizm(o.GetPolozenieX(), o.GetPolozenieY()); // zabicie atakujacego
            }
        }
    }

    @Override
    public void PostawDziecko(int x, int y)
    {
        swiat.DodajOrganizm(new Zolw(x, y, swiat));
        // swiat.gotoxy(40, 8);
    }
}
