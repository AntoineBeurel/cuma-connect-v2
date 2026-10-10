package fr.cuma.connect.materiel

import fr.cuma.connect.common.GlobalExceptionHandler
import fr.cuma.connect.common.NotFoundException
import org.junit.jupiter.api.Test
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal

@WebMvcTest(MaterielController::class)
@Import(GlobalExceptionHandler::class)
class MaterielControllerTest @Autowired constructor(private val mockMvc: MockMvc) {

    @MockitoBean
    lateinit var service: MaterielService

    @Test
    fun `liste le materiel`() {
        whenever(service.lister()).thenReturn(
            listOf(
                Materiel(
                    nom = "Tracteur", categorie = "Traction", uniteCompteur = UniteCompteur.HEURE,
                    tarifUnitaire = BigDecimal("40.00"), id = 1,
                ),
            ),
        )

        mockMvc.perform(get("/api/materiels"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].nom").value("Tracteur"))
    }

    @Test
    fun `renvoie 404 pour un materiel inconnu`() {
        whenever(service.trouver(99)).thenThrow(NotFoundException("Matériel 99 introuvable"))

        mockMvc.perform(get("/api/materiels/99"))
            .andExpect(status().isNotFound)
    }

    @Test
    fun `refuse un nom vide avec 400`() {
        mockMvc.perform(
            post("/api/materiels")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"nom":"","categorie":"Traction","uniteCompteur":"HEURE"}"""),
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.erreurs.nom").exists())
    }
}
