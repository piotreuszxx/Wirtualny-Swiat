#pragma once
#include "Roslina.h"

class Guarana : public Roslina
{
public:
	Guarana(int polX, int polY, Swiat& swiat);
	Guarana(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak_gatunku, int wiek, string nazwa, Swiat& swiat);
	~Guarana();
	void kolizja(Organizm* o) override;
	void PostawDziecko(int x, int y) override;
};