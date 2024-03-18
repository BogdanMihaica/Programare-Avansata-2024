Clasa Attraction : 
Este o clasa abstracte pe baza careia sunt extinse clasele Church, Statue si Concert.
Aceasta detine o variabila name de tip String si un Map<LocalDate, TimeInterval> visitTimetable pentru a stoca intervalul de timp pentru o anumita data
In acesasta clasa am suprascris metoda compareTo pentru a compara doua atractii bazate pe ora de deschidere (primul element din perechea TimeInterval) in functie de ziua curenta

Clasa Pair a fost creata folosind generics (stocheaza o pereche formata dintr-un obiect al unei clase si inca un obiect al unei clase).
Visitable si Payable sunt doua interfete pe baza carora sunt implementaet clasele Concert Statue si Church 
In clasa visitable avem metoda default getOpeningHour(LocalDate A, Attraction B) care returneaza ora de deschidere din visitTimetable pentru atractia B

In clasa Trip am implementat metoda displayAttraction care sorteaza sirul de atractii din componenta acesteia folosind functia suprascrisa pentru clasa Attraction si verifica folosind 2 metode :isVisitable si isPayable . Daca clasa iterata este Visitable dar nu este Payable atunci este afisata pe ecran.

In clasa TravelPlan am initializat un HashMap care primeste chei Attraction care au valori LocalDate. Aici am suprascris metoda toString astfel:
Am creat un StringBuilder pentru a lipi String-uri obtinute in interiorul for-ului care itereaza prin HashMap-ul precizat mai devreme si l-am returnat la final folosind functia toString() a StringBuilder-ului.
