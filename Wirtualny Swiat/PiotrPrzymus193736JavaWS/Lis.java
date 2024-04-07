package WSJAVA;
import java.util.Random;

public class Lis extends Zwierze{
    public Lis(int polX, int polY, Swiat swiat) {
        super(polX, polY, swiat);
        this.sila = 3;
        this.inicjatywa = 7;
        this.znak_gatunku = Stale.LIS;
        this.wiek = 0;
        this.nazwa = "Lis";
    }
    public Lis(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak_gatunku, int wiek, String nazwa, Swiat swiat) {
        super(polozenieX, polozenieY, sila, inicjatywa, znak_gatunku, wiek, nazwa, swiat);
    }

    @Override
    public void akcja()
    {
        //funkcja losująca w którą stronę organizm ma się poruszyć, jeśli na tym miejscu stoi inny organizm to wywołuje jego funkcje kolizja
        int x = polozeniex;
        int y = polozeniey;
        boolean[] byly = new boolean[4];
        for(int i=0;i<4;i++)
            byly[i]=false;
        int kierunek;
        Random rand = new Random();
        while (true)
        {
            kierunek = rand.nextInt(4);
            if (kierunek == 0 && !byly[kierunek])
            {
                byly[kierunek] = true;
                if (y > 0 && (swiat.GetOrganizm(x, y - 1) == null || swiat.GetOrganizm(x, y - 1).GetNazwa().equals("Lis") || swiat.GetOrganizm(x, y - 1).GetSila() <= sila))
                {
                    y--;
                    break;
                }
            }
            else if (kierunek == 1 && !byly[kierunek])
            {
                byly[kierunek] = true;
                if (y < swiat.GetRozmiarY() - 1 && (swiat.GetOrganizm(x, y + 1) == null || swiat.GetOrganizm(x, y + 1).GetNazwa().equals("Lis") || swiat.GetOrganizm(x, y + 1).GetSila() <= sila))
                {
                    y++;
                    break;
                }
            }
            else if (kierunek == 2 && !byly[kierunek])
            {
                byly[kierunek] = true;
                if (x > 0 && (swiat.GetOrganizm(x - 1, y) == null || swiat.GetOrganizm(x - 1, y).GetNazwa().equals("Lis") ||  swiat.GetOrganizm(x - 1, y).GetSila() <= sila))
                {
                    x--;
                    break;
                }
            }
            else if (kierunek == 3 && !byly[kierunek])
            {
                byly[kierunek] = true;
                if (x < swiat.GetRozmiarX() - 1 && (swiat.GetOrganizm(x + 1, y) == null || swiat.GetOrganizm(x + 1, y).GetNazwa().equals("Lis") || swiat.GetOrganizm(x + 1, y).GetSila() <= sila))
                {
                    x++;
                    break;
                }
            }
            if (byly[0] && byly[1] && byly[2] && byly[3])
            {
                //System.out.println("Lis (" + polozeniex + "," + polozeniey + ") nie porusza sie");
                swiat.WypiszZdarzenie("Lis (" + polozeniex + "," + polozeniey + ") nie porusza sie");
                return;
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
    public void PostawDziecko(int x, int y)
    {
        swiat.DodajOrganizm(new Lis(x, y, swiat));
        // swiat.gotoxy(40,8);
    }
}
