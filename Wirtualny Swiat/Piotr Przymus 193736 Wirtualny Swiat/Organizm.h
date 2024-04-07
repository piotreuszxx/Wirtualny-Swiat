#pragma once
#include "Swiat.h"

class Organizm
{
protected:
	int polozeniex;
	int polozeniey;
	int sila;
	int inicjatywa;
	char znak_gatunku;
	int wiek;
	string nazwa;
	Swiat& swiat;
public:
	//Organizm();
	Organizm(int polX, int polY, Swiat& swiat);
	Organizm(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak_gatunku, int wiek, string nazwa, Swiat& swiat);
	virtual void akcja() = 0;
	virtual void kolizja(Organizm* o) = 0;
	virtual void Rozmnazanie(Organizm* o) = 0;
	virtual void PostawDziecko(int x, int y) = 0;
	void rysowanie();

	void SetSila(int s);
	void SetPolozenieX(int x);
	void SetPolozenieY(int y);
	void SetWiek(int w);

	int GetPolozenieX();
	int GetPolozenieY();
	int GetSila();
	int GetInicjatywa();
	char GetZnak_Gatunku();
	int GetWiek();
	string GetNazwa();

	void Postarzej();

	virtual ~Organizm();
};
