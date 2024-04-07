package WSJAVA;
import java.util.Random;

abstract public class Zwierze extends Organizm {

    public Zwierze(int polX, int polY, Swiat swiat) {
        super(polX, polY, swiat);
    }

    public Zwierze(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak_gatunku, int wiek, String nazwa, Swiat swiat) {
        super(polozenieX, polozenieY, sila, inicjatywa, znak_gatunku, wiek, nazwa, swiat);
    }

    @Override
    public void akcja()
    {
        //funkcja losująca w którą stronę organizm ma się poruszyć, jeśli na tym miejscu stoi inny organizm to wywołuje jego funkcje kolizja
        int x = polozeniex;
        int y = polozeniey;
        int kierunek;
        Random rand = new Random();
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
                //System .out.println(o.GetNazwa() + "(" + p2x + "," + p2y + ") probowal rozmnozyc sie z " + nazwa + "(" + px + "," + py + "), ale drugi jest za mlody");
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

    //abstract public void PostawDziecko(int x, int y);

    @Override
    public void Rozmnazanie(Organizm o)
    {
        if (swiat.CzySaOtoczone(this, o))
        {
            String px = String.valueOf(polozeniex);
            String py = String.valueOf(polozeniey);
            String p2x = String.valueOf(o.GetPolozenieX());
            String p2y = String.valueOf(o.GetPolozenieY());
            //System.out.println(o.GetNazwa() + "(" + p2x + "," + p2y + ") nie moze sie rozmnazac z " + nazwa + "(" + px + "," + py + ")");
            swiat.WypiszZdarzenie(o.GetNazwa() + "(" + p2x + "," + p2y + ") nie moze sie rozmnazac z " + nazwa + "(" + px + "," + py + ")");

            return;
        }

        int x1 = polozeniex;
        int y1 = polozeniey;
        int x2 = o.GetPolozenieX();
        int y2 = o.GetPolozenieY();
        int x, y, kierunek;
        Random rand = new Random();
        while (true)
        {
            kierunek = rand.nextInt(8);
            if (kierunek == 0)
            {
                if (y1 > 0 && swiat.GetOrganizm(x1, y1 - 1) == null)
                {
                    y1--;
                    x = x1;
                    y = y1;
                    break;
                }
            }
            else if (kierunek == 1)
            {
                if (y1 < swiat.GetRozmiarY() - 1 && swiat.GetOrganizm(x1, y1 + 1) == null)
                {
                    y1++;
                    x = x1;
                    y = y1;
                    break;
                }
            }
            else if (kierunek == 2)
            {
                if (x1 > 0 && swiat.GetOrganizm(x1 - 1, y1) == null)
                {
                    x1--;
                    x = x1;
                    y = y1;
                    break;
                }
            }
            else if (kierunek == 3)
            {
                if (x1 < swiat.GetRozmiarX() - 1 && swiat.GetOrganizm(x1 + 1, y1) == null)
                {
                    x1++;
                    x = x1;
                    y = y1;
                    break;
                }
            }
            else if (kierunek == 4)
            {
                if (y2 > 0 && swiat.GetOrganizm(x2, y2 - 1) == null)
                {
                    y2--;
                    x = x2;
                    y = y2;
                    break;
                }
            }
            else if (kierunek == 5)
            {
                if (y2 < swiat.GetRozmiarY() - 1 && swiat.GetOrganizm(x2, y2 + 1) == null)
                {
                    y2++;
                    x = x2;
                    y = y2;
                    break;
                }
            }
            else if (kierunek == 6)
            {
                if (x2 > 0 && swiat.GetOrganizm(x2 - 1, y2) == null)
                {
                    x2--;
                    x = x2;
                    y = y2;
                    break;
                }
            }
            else
            {
                if (x2 < swiat.GetRozmiarX() - 1 && swiat.GetOrganizm(x2 + 1, y2) == null)
                {
                    x2++;
                    x = x2;
                    y = y2;
                    break;
                }
            }
        }
        //System.out.println(o.GetNazwa() + "(" + o.GetPolozenieX() + "," + o.GetPolozenieY() + ") rozmnaza sie z " + nazwa + "(" + polozeniex + "," + polozeniey + ") na pole: " + x + "," + y);
        swiat.WypiszZdarzenie(o.GetNazwa() + "(" + o.GetPolozenieX() + "," + o.GetPolozenieY() + ") rozmnaza sie z " + nazwa + "(" + polozeniex + "," + polozeniey + ") na pole: " + x + "," + y);
        o.PostawDziecko(x, y);
    }
}
