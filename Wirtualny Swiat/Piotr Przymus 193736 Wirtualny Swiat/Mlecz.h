#pragma once
#include "Roslina.h"

class Mlecz : public Roslina
{
public:
	Mlecz(int polX, int polY, Swiat& swiat);
	Mlecz(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak_gatunku, int wiek, string nazwa, Swiat& swiat);
	~Mlecz();
	void akcja() override;
	void PostawDziecko(int x, int y) override;
};