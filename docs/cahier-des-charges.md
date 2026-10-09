# Cahier des charges : CUMA Connect v2

## Acteurs
- **Adhérent** : consulte le planning, réserve, annule et clôture ses réservations, gère ses notes.
- **Admin** : tout ce que fait l'adhérent, plus la gestion du matériel (dont le tarif), des blocages, des factures et des statistiques.

## User stories
1. En tant qu'adhérent, je vois le planning de chaque machine, avec le nom de la personne qui a réservé.
2. En tant qu'adhérent, je réserve une machine sur un créneau libre ; un conflit est refusé (409).
3. En tant qu'adhérent, je clôture ma réservation en saisissant le compteur de fin ; la machine redevient disponible le reste de la journée.
4. En tant qu'adhérent, j'annule ma réservation.
5. En tant qu'adhérent, j'ajoute, modifie et supprime mes notes sur un jour (générales ou liées à un matériel).
6. En tant qu'admin, je bloque un matériel pour un adhérent ; il disparaît complètement pour lui (404).
7. En tant qu'admin, je gère le matériel et son tarif facultatif, je consulte les factures et les statistiques d'usage.

## Règles du compteur
- `compteur_debut` est pré-rempli avec le dernier compteur de la machine.
- `compteur_fin` doit être supérieur ou égal à `compteur_debut` (sinon 400).
- Tant que `compteur_fin` n'est pas saisi, la machine est occupée.
- La quantité utilisée vaut `compteur_fin - compteur_debut`.
- La clôture met à jour le compteur actuel de la machine dans la même transaction.
- Plusieurs réservations le même jour sont conservées et affichées l'une sous l'autre.

## Règles de visibilité
- Les adhérents ne voient jamais de tarif, de montant ni de facture.
- Annuler et clôturer : propriétaire de la réservation ou admin.
