#include "Wilk.h"

Wilk::Wilk(int polX, int polY, Swiat& swiat)
	: Zwierze(polX, polY, swiat)
{
	wiek = 0;
	inicjatywa = 5;
	sila = 9;
	znak_gatunku = WILK;
	nazwa = "Wilk";
}

Wilk::Wilk(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak, int wiek, string nazwa, Swiat& swiat)
	: Zwierze(polozenieX, polozenieY, sila, inicjatywa, znak, wiek, nazwa, swiat)
{
}

Wilk::~Wilk()
{

}

void Wilk::PostawDziecko(int x, int y)
{
	swiat.DodajOrganizm(new Wilk(x, y, swiat));
	swiat.gotoxy(40, 8);
}