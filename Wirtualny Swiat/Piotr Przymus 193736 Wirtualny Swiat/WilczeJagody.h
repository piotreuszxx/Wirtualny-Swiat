#pragma once
#include "Roslina.h"

class WilczeJagody : public Roslina
{
public:
	WilczeJagody(int polX, int polY, Swiat& swiat);
	WilczeJagody(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak_gatunku, int wiek, string nazwa, Swiat& swiat);
	~WilczeJagody();
	void kolizja(Organizm* o) override;
	void PostawDziecko(int x, int y) override;
};