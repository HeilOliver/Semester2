Lösungsidee:

Der MapController beinhaltet die komplette Map und alle darauf befindlichen Spieler.
Der Spieler kann sich aber selbst bewegen. Der Mapcontroller ist nur für die Komunikation zwischen
Spielern und für allgemeine funktionen wie laden speichern zuständig.
Ein Feld hat vier Nachbarn. Wenn ein Feld Betretbar sein soll muss es das Interface IAccessible besitzen.
Falls auf dem Feld etwas getan werden soll muss das Interface IExecuteable implementiert sein.

Items die der Spieler aufnehmen kann müssen von der Klasse Item vererben. Falls sie konsumiert
werden sollen müssen sie auch noch das Interface IConsumable besitzen.
