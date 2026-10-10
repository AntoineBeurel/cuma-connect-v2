package fr.cuma.connect.materiel

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

// Tant que l'authentification n'existe pas (semaine 3), tout est servi en vue admin.
@RestController
@RequestMapping("/api/materiels")
class MaterielController(private val service: MaterielService) {

    @GetMapping
    fun lister(): List<MaterielAdminDto> = service.lister().map { it.toAdminDto() }

    @GetMapping("/{id}")
    fun trouver(@PathVariable id: Long): MaterielAdminDto = service.trouver(id).toAdminDto()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun creer(@Valid @RequestBody req: MaterielRequest): MaterielAdminDto =
        service.creer(req).toAdminDto()

    @PutMapping("/{id}")
    fun modifier(@PathVariable id: Long, @Valid @RequestBody req: MaterielRequest): MaterielAdminDto =
        service.modifier(id, req).toAdminDto()

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun supprimer(@PathVariable id: Long) = service.supprimer(id)
}
