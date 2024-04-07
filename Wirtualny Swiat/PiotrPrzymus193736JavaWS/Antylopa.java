package WSJAVA;
import java.util.Random;

public class Antylopa extends Zwierze{
    public Antylopa(int polX, int polY, Swiat swiat) {
        super(polX, polY, swiat);
        this.sila = 4;
        this.inicjatywa = 4;
        this.znak_gatunku = Stale.ANTYLOPA;
        this.wiek = 0;
        this.nazwa = "Antylopa";
    }
    public Antylopa(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak_gatunku, int wiek, String nazwa, Swiat swiat) {
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
                if (y > 1)
                {
                    y-=2;
                    break;
                }
            }
            else if (kierunek == 1)
            {
                if (y < swiat.GetRozmiarY() - 2)
                {
                    y+=2;
                    break;
                }
            }
            else if (kierunek == 2)
            {
                if (x > 1)
                {
                    x-=2;
                    break;
                }
            }
            else
            {
                if (x < swiat.GetRozmiarX() - 2)
                {
                    x+=2;
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
            Random rand = new Random();
            int szansa = rand.nextInt(2);
            if (szansa == 1)
            {
                int kierunek;
                int x = polozeniex;
                int y = polozeniey;
                boolean[] byly = new boolean[4];
                for (int i = 0; i < 4; i++)
                {
                    byly[i] = false;
                }
                while (true)
                {
                    kierunek = rand.nextInt(4);
                    if (kierunek == 0 && !byly[kierunek])
                    {
                        byly[kierunek] = true;
                        if (y > 0 && swiat.GetOrganizm(x, y - 1) == null)
                        {
                            y--;
                            break;
                        }
                    }
                    else if (kierunek == 1 && !byly[kierunek])
                    {
                        byly[kierunek] = true;
                        if (y < swiat.GetRozmiarY() - 1 && swiat.GetOrganizm(x, y + 1) == null)
                        {
                            y++;
                            break;
                        }
                    }
                    else if (kierunek == 2 && !byly[kierunek])
                    {
                        byly[kierunek] = true;
                        if (x > 0 && swiat.GetOrganizm(x - 1, y) == null)
                        {
                            x--;
                            break;
                        }
                    }
                    else if (kierunek == 3 && !byly[kierunek])
                    {
                        byly[kierunek] = true;
                        if (x < swiat.GetRozmiarX() - 1 && swiat.GetOrganizm(x + 1, y) == null)
                        {
                            x++;
                            break;
                        }
                    }
                    if (byly[0] && byly[1] && byly[2] && byly[3])
                    {
                        //System.out.println(o.GetNazwa() + "(" + p2x + "," + p2y + ") zabija " + nazwa + "(" + px + "," + py + ")");
                        swiat.WypiszZdarzenie(o.GetNazwa() + "(" + p2x + "," + p2y + ") zabija " + nazwa + "(" + px + "," + py + ")");
                        swiat.ZabijOrganizm(polozeniex, polozeniey);
                        break;
                    }
                }
                if(wiek != -1)
                {
                    //System.out.println(o.GetNazwa() + "(" + p2x + "," + p2y + ") probowal zjesc " + nazwa + "(" + px + "," + py + "), ale ta uciekla na (" + x + "," + y + ")");
                    swiat.WypiszZdarzenie(o.GetNazwa() + "(" + p2x + "," + p2y + ") probowal zjesc " + nazwa + "(" + px + "," + py + "), ale ta uciekla na (" + x + "," + y + ")");
                    polozeniex = x;
                    polozeniey = y;
                }
                else
                {
                    swiat.UsunOrganizm(polozeniex, polozeniey);
                }
            }
            else
            {
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
    }

    @Override
    public void PostawDziecko(int x, int y)
    {
        swiat.DodajOrganizm(new Antylopa(x, y, swiat));
        //swiat.gotoxy(40, 8);
    }
}
