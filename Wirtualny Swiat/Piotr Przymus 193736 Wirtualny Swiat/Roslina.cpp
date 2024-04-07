#include "Roslina.h"

Roslina::Roslina(int polX, int polY, Swiat& swiat)
	: Organizm(polX, polY, swiat)
{}

Roslina::Roslina(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak, int wiek, string nazwa, Swiat& swiat)
	: Organizm(polozenieX, polozenieY, sila, inicjatywa, znak, wiek, nazwa, swiat)
{}

void Roslina::akcja()
{
	if (CzyOtoczona())
	{
		swiat.WypiszZdarzenie(nazwa + "(" + to_string(polozeniex) + "," + to_string(polozeniey) + ") nie ma gdzie sie rozsiac");
		return;
	}
	Rozmnazanie(nullptr);
}

void Roslina::Rozmnazanie(Organizm* o)
{
	int x = polozeniex;
	int y = polozeniey;
	int kierunek;
	while (true)
	{
		kierunek = rand() % 4;
		if (kierunek == 0)
		{
			if (y > 0 && swiat.GetOrganizm(x, y - 1) == nullptr)
			{
				y--;
				break;
			}
		}
		else if (kierunek == 1 && swiat.GetOrganizm(x, y + 1) == nullptr)
		{
			if (y < swiat.GetRozmiarY() - 1 && swiat.GetOrganizm(x, y + 1) == nullptr)
			{
				y++;
				break;
			}
		}
		else if (kierunek == 2 && swiat.GetOrganizm(x - 1, y) == nullptr)
		{
			if (x > 0)
			{
				x--;
				break;
			}
		}
		else if (kierunek == 3)
		{
			if (x < swiat.GetRozmiarX() - 1 && swiat.GetOrganizm(x + 1, y) == nullptr)
			{
				x++;
				break;
			}
		}
	}
	this->PostawDziecko(x, y);
}

void Roslina::kolizja(Organizm* o)
{
	string px = to_string(polozeniex);
	string py = to_string(polozeniey);
	string p2x = to_string(o->GetPolozenieX());
	string p2y = to_string(o->GetPolozenieY());
	swiat.WypiszZdarzenie(o->GetNazwa() + "(" + p2x + "," + p2y + ") zdeptal " + nazwa + "(" + px + "," + py + ")");
	swiat.UsunOrganizm(polozeniex, polozeniey);
}

bool Roslina::CzyOtoczona()
{
	if (polozeniex > 0 && swiat.GetOrganizm(polozeniex - 1, polozeniey) == nullptr)
	{
		return false;
	}
	if (polozeniex < swiat.GetRozmiarX() - 1 && swiat.GetOrganizm(polozeniex + 1, polozeniey) == nullptr)
	{
		return false;
	}
	if (polozeniey > 0 && swiat.GetOrganizm(polozeniex, polozeniey - 1) == nullptr)
	{
		return false;
	}
	if (polozeniey < swiat.GetRozmiarY() - 1 && swiat.GetOrganizm(polozeniex, polozeniey + 1) == nullptr)
	{
		return false;
	}
	return true;
}

Roslina::~Roslina()
{}