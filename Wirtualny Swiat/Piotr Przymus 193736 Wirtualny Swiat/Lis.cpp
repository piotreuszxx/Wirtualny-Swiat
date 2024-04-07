#include "Lis.h"

Lis::Lis(int polX, int polY, Swiat& swiat)
	: Zwierze(polX, polY, swiat)
{
	wiek = 0;
	inicjatywa = 7;
	sila = 3;
	znak_gatunku = LIS;
	nazwa = "Lis";
}

Lis::Lis(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak_gatunku, int wiek, string nazwa, Swiat& swiat)
	: Zwierze(polozenieX, polozenieY, sila, inicjatywa, znak_gatunku, wiek, nazwa, swiat)
{
}

void Lis::akcja()
{
	//funkcja losuj¹ca w któr¹ stronê organizm ma siê poruszyæ, jeœli na tym miejscu stoi inny organizm to wywo³uje jego funkcje kolizja
	int x = polozeniex;
	int y = polozeniey;
	bool byly[4];
	for(int i=0;i<4;i++)
		byly[i]=false;
	int kierunek;
	while (true)
	{
		kierunek = rand() % 4;
		if (kierunek == 0 && !byly[kierunek])
		{
			byly[kierunek] = true;
			if (y > 0 && (swiat.GetOrganizm(x, y - 1) == nullptr || swiat.GetOrganizm(x, y - 1)->GetNazwa() == "Lis" || swiat.GetOrganizm(x, y - 1)->GetSila() <= sila))
			{
				y--;
				break;
			}
		}
		else if (kierunek == 1 && !byly[kierunek])
		{
			byly[kierunek] = true;
			if (y < swiat.GetRozmiarY() - 1 && (swiat.GetOrganizm(x, y + 1) == nullptr || swiat.GetOrganizm(x, y + 1)->GetNazwa() == "Lis" || swiat.GetOrganizm(x, y + 1)->GetSila() <= sila))
			{
				y++;
				break;
			}
		}
		else if (kierunek == 2 && !byly[kierunek])
		{
			byly[kierunek] = true;
			if (x > 0 && (swiat.GetOrganizm(x - 1, y) == nullptr || swiat.GetOrganizm(x - 1, y)->GetNazwa() == "Lis" ||  swiat.GetOrganizm(x - 1, y)->GetSila() <= sila))
			{
				x--;
				break;
			}
		}
		else if (kierunek == 3 && !byly[kierunek])
		{
			byly[kierunek] = true;
			if (x < swiat.GetRozmiarX() - 1 && (swiat.GetOrganizm(x + 1, y) == nullptr || swiat.GetOrganizm(x + 1, y)->GetNazwa() == "Lis" || swiat.GetOrganizm(x + 1, y)->GetSila() <= sila))
			{
				x++;
				break;
			}
		}
		if (byly[0] && byly[1] && byly[2] && byly[3])
		{
			swiat.WypiszZdarzenie("Lis (" + to_string(polozeniex) + "," + to_string(polozeniey) + ") nie porusza sie");
			return;
		}
	}
	if (swiat.GetOrganizm(x, y) != nullptr)
	{
		swiat.GetOrganizm(x, y)->kolizja(this); //this wjechal w organizm na polu x,y
		if (wiek != -1 && swiat.GetOrganizm(x, y) == nullptr)
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
		swiat.WypiszZdarzenie(nazwa + "(" + to_string(polozeniex) + "," + to_string(polozeniey) + ") przemiescil sie na (" + to_string(x) + "," + to_string(y) + ")");
		polozeniex = x;
		polozeniey = y;
	}
}

Lis::~Lis()
{
}

void Lis::PostawDziecko(int x, int y)
{
	swiat.DodajOrganizm(new Lis(x, y, swiat));
	swiat.gotoxy(40, 8);
}