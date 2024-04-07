#pragma once
#include "Organizm.h"

class Roslina : public Organizm
{
public:
	Roslina(int polX, int polY, Swiat& swiat);
	Roslina(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak_gatunku, int wiek, string nazwa, Swiat& swiat);
	virtual void akcja() override;
	virtual void kolizja(Organizm* o) override;

	virtual void Rozmnazanie(Organizm* o) override;
	virtual void PostawDziecko(int x, int y) = 0;
	bool CzyOtoczona();
	virtual ~Roslina();
};