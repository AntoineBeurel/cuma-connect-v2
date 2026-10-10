package fr.cuma.connect.materiel

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "materiel")
class Materiel(
    @Column(nullable = false)
    var nom: String,

    @Column(nullable = false)
    var categorie: String,

    var description: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "unite_compteur", nullable = false)
    var uniteCompteur: UniteCompteur,

    @Column(name = "compteur_actuel", nullable = false)
    var compteurActuel: BigDecimal = BigDecimal.ZERO,

    @Column(name = "tarif_unitaire")
    var tarifUnitaire: BigDecimal? = null,

    @Column(name = "photo_key")
    var photoKey: String? = null,

    @Column(nullable = false)
    var actif: Boolean = true,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
)
