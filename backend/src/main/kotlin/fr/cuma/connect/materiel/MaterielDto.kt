package fr.cuma.connect.materiel

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.math.BigDecimal

/** Vue adhérent : ne contient jamais le tarif. */
data class MaterielDto(
    val id: Long,
    val nom: String,
    val categorie: String,
    val description: String?,
    val uniteCompteur: UniteCompteur,
    val compteurActuel: BigDecimal,
    val photoKey: String?,
    val actif: Boolean,
)

/** Vue admin : ajoute le tarif facultatif. */
data class MaterielAdminDto(
    val id: Long,
    val nom: String,
    val categorie: String,
    val description: String?,
    val uniteCompteur: UniteCompteur,
    val compteurActuel: BigDecimal,
    val tarifUnitaire: BigDecimal?,
    val photoKey: String?,
    val actif: Boolean,
)

data class MaterielRequest(
    @field:NotBlank @field:Size(max = 150)
    val nom: String,

    @field:NotBlank @field:Size(max = 100)
    val categorie: String,

    val description: String? = null,

    @field:NotNull
    val uniteCompteur: UniteCompteur,

    @field:DecimalMin("0")
    val compteurActuel: BigDecimal = BigDecimal.ZERO,

    @field:DecimalMin("0")
    val tarifUnitaire: BigDecimal? = null,

    val actif: Boolean = true,
)

fun Materiel.toDto() = MaterielDto(
    id = id!!,
    nom = nom,
    categorie = categorie,
    description = description,
    uniteCompteur = uniteCompteur,
    compteurActuel = compteurActuel,
    photoKey = photoKey,
    actif = actif,
)

fun Materiel.toAdminDto() = MaterielAdminDto(
    id = id!!,
    nom = nom,
    categorie = categorie,
    description = description,
    uniteCompteur = uniteCompteur,
    compteurActuel = compteurActuel,
    tarifUnitaire = tarifUnitaire,
    photoKey = photoKey,
    actif = actif,
)
