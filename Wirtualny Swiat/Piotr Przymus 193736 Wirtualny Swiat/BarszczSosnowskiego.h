#pragma once
#include "Roslina.h"

class BarszczSosnowskiego : public Roslina
{
public:
	BarszczSosnowskiego(int polX, int polY, Swiat& swiat);
	BarszczSosnowskiego(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak_gatunku, int wiek, string nazwa, Swiat& swiat);
	~BarszczSosnowskiego();
	void akcja() override;
	void kolizja(Organizm* o) override;
	void PostawDziecko(int x, int y) override;
};