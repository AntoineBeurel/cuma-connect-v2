# CUMA Connect v2

Gestion de matériel agricole partagé pour une CUMA : planning de réservations sans conflit,
clôture au compteur, notes sur les jours et droits d'accès par matériel.

**Stack :** Spring Boot 3 / Kotlin, PostgreSQL, React / TypeScript, Docker, GitHub Actions, AWS.

> Projet en cours de construction. Feuille de route : [docs/feuille-de-route-cuma-connect-v2.pdf](docs/feuille-de-route-cuma-connect-v2.pdf)

## Structure

| Dossier | Contenu |
|---|---|
| `backend/` | API Spring Boot / Kotlin |
| `frontend/` | Application React / TypeScript |
| `lambda/` | Fonction de rappel |
| `analytics/` | Notebook pandas, dashboard |
| `infra/` | Docker Compose, déploiement |
| `docs/` | Cahier des charges, schémas, ADR |

## Avancement

- [ ] v0.1 : backend complet, conteneurisé
