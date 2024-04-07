#pragma once
#include "Zwierze.h"

class Zolw : public Zwierze
{
public:
	// Zolw();
	Zolw(int polX, int polY, Swiat& swiat);
	Zolw(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak_gatunku, int wiek, string nazwa, Swiat& swiat);
	void akcja() override;
	void kolizja(Organizm* o) override;
	~Zolw();
	void PostawDziecko(int x, int y) override;
};