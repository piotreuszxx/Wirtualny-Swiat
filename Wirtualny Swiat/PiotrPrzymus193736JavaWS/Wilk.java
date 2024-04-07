package WSJAVA;

public class Wilk extends Zwierze {
    public Wilk(int polX, int polY, Swiat swiat) {
        super(polX, polY, swiat);
        this.sila = 9;
        this.inicjatywa = 5;
        this.znak_gatunku = Stale.WILK;
        this.wiek = 0;
        this.nazwa = "Wilk";
    }
    public Wilk(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak_gatunku, int wiek, String nazwa, Swiat swiat) {
        super(polozenieX, polozenieY, sila, inicjatywa, znak_gatunku, wiek, nazwa, swiat);
    }

    @Override
    public void PostawDziecko(int x, int y)
    {
        swiat.DodajOrganizm(new Wilk(x, y, swiat));
        //swiat.gotoxy(40, 8);
    }
}
