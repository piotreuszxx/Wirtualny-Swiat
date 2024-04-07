#include "Trawa.h"

Trawa::Trawa(int polX, int polY, Swiat& swiat)
	: Roslina(polX, polY, swiat)
{
	wiek = 0;
	inicjatywa = 0;
	sila = 0;
	znak_gatunku = TRAWA;
	nazwa = "Trawa";
}

Trawa::Trawa(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak, int wiek, string nazwa, Swiat& swiat)
	: Roslina(polozenieX, polozenieY, sila, inicjatywa, znak, wiek, nazwa, swiat)
{
}

Trawa::~Trawa()
{
}

void Trawa::PostawDziecko(int x, int y)
{
	int szansa = rand() % RNG_TRAWA;
	if (szansa == 0)
	{
		swiat.WypiszZdarzenie(nazwa + "(" + to_string(polozeniex) + "," + to_string(polozeniey) + ") rozsiewa sie na (" + to_string(x) + "," + to_string(y) + ")");
		swiat.DodajOrganizm(new Trawa(x, y, swiat));
		swiat.gotoxy(40, 8);
		return;
	}
	swiat.WypiszZdarzenie(nazwa + "(" + to_string(polozeniex) + "," + to_string(polozeniey) + ") nie rozsiewa sie");
	swiat.gotoxy(40, 8);
}