#pragma once
#include "Zwierze.h"

class Antylopa : public Zwierze
{
public:
	Antylopa(int polX, int polY, Swiat& swiat);
	Antylopa(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak_gatunku, int wiek, string nazwa, Swiat& swiat);
	void akcja() override;
	void kolizja(Organizm* o) override;
	~Antylopa();
	void PostawDziecko(int x, int y) override;
};