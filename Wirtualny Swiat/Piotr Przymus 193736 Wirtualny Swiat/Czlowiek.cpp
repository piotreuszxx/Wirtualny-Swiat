#include "Czlowiek.h"

Czlowiek::Czlowiek(int polX, int polY, Swiat& swiat)
	: Zwierze(polX, polY, swiat)
{
	wiek = 0;
	inicjatywa = 4;
	sila = 5;
	znak_gatunku = CZLOWIEK;
	nazwa = "Czlowiek";
	licznik = 0;
}

Czlowiek::Czlowiek(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak, int wiek, string nazwa, int licznik, Swiat& swiat)
	: Zwierze(polozenieX, polozenieY, sila, inicjatywa, znak, wiek, nazwa, swiat), licznik(licznik)
{
}

Czlowiek::~Czlowiek()
{
}

void Czlowiek::akcja()
{
	int x = polozeniex;
	int y = polozeniey;
	string k = swiat.GetKierunekCzlowieka();
	if (k == "zostaje")
	{
		// nic
	}
	else if (k == "lewo" && x > 0)
	{
		x--;
	}
	else if (k == "prawo" && x < swiat.GetRozmiarX() - 1)
	{
		x++;
	}
	else if (k == "gora" && y > 0)
	{
		y--;
	}
	else if (k == "dol" && y < swiat.GetRozmiarY() - 1)
	{
		y++;
	}

	if (swiat.GetOrganizm(x, y) != nullptr)
	{
		swiat.GetOrganizm(x, y)->kolizja(this); //this wjechal w organizm na polu x,y
		if (wiek != -1 && swiat.GetOrganizm(x, y) == nullptr)
		{
			polozeniex = x; // jesli this nie zostal zjedzony ORAZ zjadl to zmien polozenie
			polozeniey = y;
		}
		if (licznik > 5)
			sila--;
		if (licznik)
			licznik--;
		if (wiek == -1)
		{
			swiat.UsunOrganizm(polozeniex, polozeniey); // jesli this zostal zjedzony to go usun
		}

	}
	else
	{
		swiat.WypiszZdarzenie(nazwa + "(" + to_string(polozeniex) + "," + to_string(polozeniey) + ") przemiescil sie na (" + to_string(x) + "," + to_string(y) + ")");
		polozeniex = x;
		polozeniey = y;
		if (licznik > 5)
			sila--;
		if (licznik)
			licznik--;
	}
}

void Czlowiek::ZwiekszSile()
{
	if(licznik == 0)
	{
		licznik = 10;
		sila += 5;
	}
}

void Czlowiek::PostawDziecko(int x, int y)
{
	//
}

int Czlowiek::GetLicznik()
{
	return licznik;
}