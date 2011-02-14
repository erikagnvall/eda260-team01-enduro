Denna readme är avsedd för teknisk dokumentation och är endast intressant för den som skall vidarutveckla systemet.

Bygga systemet:
Bygga systemet är automatiserat genom ett ANT-script som finns i filen build.xml vilken
ligger i samma mapp som denna readme.
För att bygga systemet körs detta script och då kommer även acceptanstester genomföras.

Filstrukturen innehåller följande mappar:

src - Innehåller källkoden för programmet

test - Innehåller de testfiler som körs för att testa koden

spike - Innehåller diverse spikes som genomförts under utvecklingen

acceptanceTest - Innehåller textfiler som används för att acceptanstesta systemet, här läggs 
				loggar för acceptanstesterna

docs -  Innehåller den dokumentation som skall läsas av end user, bland annat manualer

technicalDoc - Här finns mer detaljerad dokumentation av den tekniska biten,
			  intressant för den som skall utveckla systemet.
			   Här finns även mer detaljerad information om mappstrukturen
			   
UISpec4J - Innehåller .jar-filer för GUI-test

Vidare ligger de filer som systemet producerar i samma mapp som denna readme

