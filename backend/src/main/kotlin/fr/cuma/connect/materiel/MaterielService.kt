package fr.cuma.connect.materiel

import fr.cuma.connect.common.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MaterielService(private val repository: MaterielRepository) {

    @Transactional(readOnly = true)
    fun lister(): List<Materiel> = repository.findAll()

    @Transactional(readOnly = true)
    fun trouver(id: Long): Materiel =
        repository.findById(id).orElseThrow { NotFoundException("Matériel $id introuvable") }

    fun creer(req: MaterielRequest): Materiel = repository.save(
        Materiel(
            nom = req.nom,
            categorie = req.categorie,
            description = req.description,
            uniteCompteur = req.uniteCompteur,
            compteurActuel = req.compteurActuel,
            tarifUnitaire = req.tarifUnitaire,
            actif = req.actif,
        ),
    )

    fun modifier(id: Long, req: MaterielRequest): Materiel = trouver(id).apply {
        nom = req.nom
        categorie = req.categorie
        description = req.description
        uniteCompteur = req.uniteCompteur
        compteurActuel = req.compteurActuel
        tarifUnitaire = req.tarifUnitaire
        actif = req.actif
    }

    fun supprimer(id: Long) {
        repository.delete(trouver(id))
    }
}
