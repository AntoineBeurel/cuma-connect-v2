package fr.cuma.connect.materiel

import org.springframework.data.jpa.repository.JpaRepository

interface MaterielRepository : JpaRepository<Materiel, Long> {
    fun findByCategorieIgnoreCase(categorie: String): List<Materiel>
}
