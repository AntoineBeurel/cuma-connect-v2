package fr.cuma.connect.materiel

import fr.cuma.connect.TestcontainersConfig
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase
import org.springframework.context.annotation.Import
import java.math.BigDecimal
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestcontainersConfig::class)
class MaterielRepositoryTest @Autowired constructor(
    private val repository: MaterielRepository,
) {

    @Test
    fun `enregistre un materiel sans tarif`() {
        val saved = repository.saveAndFlush(
            Materiel(nom = "Tracteur", categorie = "Traction", uniteCompteur = UniteCompteur.HEURE),
        )

        assertNotNull(saved.id)
        assertNull(repository.findById(saved.id!!).get().tarifUnitaire)
    }

    @Test
    fun `conserve le tarif et le compteur`() {
        val saved = repository.saveAndFlush(
            Materiel(
                nom = "Moissonneuse",
                categorie = "Récolte",
                uniteCompteur = UniteCompteur.HECTARE,
                compteurActuel = BigDecimal("120.50"),
                tarifUnitaire = BigDecimal("35.00"),
            ),
        )

        val relu = repository.findById(saved.id!!).get()
        assertEquals(0, BigDecimal("120.50").compareTo(relu.compteurActuel))
        assertEquals(0, BigDecimal("35.00").compareTo(relu.tarifUnitaire))
    }

    @Test
    fun `recherche par categorie sans tenir compte de la casse`() {
        repository.saveAndFlush(Materiel(nom = "Semoir", categorie = "Semis", uniteCompteur = UniteCompteur.HECTARE))

        assertEquals(1, repository.findByCategorieIgnoreCase("semis").size)
    }
}
