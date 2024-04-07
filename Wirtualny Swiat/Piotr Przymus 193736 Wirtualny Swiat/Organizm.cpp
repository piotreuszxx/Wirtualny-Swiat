#include "Organizm.h"

Organizm::Organizm(int polX, int polY, Swiat& swiat)
	: polozeniex(polX), polozeniey(polY), swiat(swiat)
{
	wiek = 0;
}

Organizm::Organizm(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak, int wiek, string nazwa, Swiat& swiat)
	: polozeniex(polozenieX), polozeniey(polozenieY), sila(sila), inicjatywa(inicjatywa), znak_gatunku(znak), wiek(wiek), nazwa(nazwa), swiat(swiat)
{}

Organizm::~Organizm()
{
}

void Organizm::SetSila(int s)
{
	sila = s;
}

void Organizm::SetPolozenieX(int x)
{
	polozeniex = x;
}

void Organizm::SetPolozenieY(int y)
{
	polozeniey = y;
}

void Organizm::SetWiek(int w)
{
	wiek = w;
}

int Organizm::GetPolozenieX()
{
	return polozeniex;
}

int Organizm::GetPolozenieY()
{
	return polozeniey;
}

int Organizm::GetSila()
{
	return sila;
}

int Organizm::GetInicjatywa()
{
	return inicjatywa;
}

char Organizm::GetZnak_Gatunku()
{
	return znak_gatunku;
}

int Organizm::GetWiek()
{
	return wiek;
}

string Organizm::GetNazwa()
{
	return nazwa;
}

void Organizm::Postarzej()
{
	wiek++;
}

void Organizm::rysowanie()
{
	swiat.Dopisz(polozeniex, polozeniey, znak_gatunku);
}