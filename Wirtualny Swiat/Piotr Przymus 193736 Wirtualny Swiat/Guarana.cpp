#include "Guarana.h"

Guarana::Guarana(int polX, int polY, Swiat& swiat)
	: Roslina(polX, polY, swiat)
{
	wiek = 0;
	inicjatywa = 0;
	sila = 0;
	znak_gatunku = GUARANA;
	nazwa = "Guarana";
}

Guarana::Guarana(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak_gatunku, int wiek, string nazwa, Swiat& swiat)
	: Roslina(polozenieX, polozenieY, sila, inicjatywa, znak_gatunku, wiek, nazwa, swiat)
{
}

Guarana::~Guarana()
{
}

void Guarana::kolizja(Organizm* o)
{
	o->SetSila(o->GetSila() + 3);
	string px = to_string(polozeniex);
	string py = to_string(polozeniey);
	string p2x = to_string(o->GetPolozenieX());
	string p2y = to_string(o->GetPolozenieY());
	swiat.WypiszZdarzenie(o->GetNazwa() + "(" + p2x + "," + p2y + ") zjadl " + nazwa + "(" + px + "," + py + "), dostaje +3 sily");
	swiat.UsunOrganizm(polozeniex, polozeniey);
}

void Guarana::PostawDziecko(int x, int y)
{
	int szansa = rand() % RNG_GUARANA;
	if (szansa == 0)
	{
		swiat.WypiszZdarzenie(nazwa + "(" + to_string(polozeniex) + "," + to_string(polozeniey) + ") rozsiewa sie na (" + to_string(x) + "," + to_string(y) + ")");
		swiat.DodajOrganizm(new Guarana(x, y, swiat));
		swiat.gotoxy(40, 8);
		return;
	}
	swiat.WypiszZdarzenie(nazwa + "(" + to_string(polozeniex) + "," + to_string(polozeniey) + ") nie rozsiewa sie");
	swiat.gotoxy(40, 8);
}