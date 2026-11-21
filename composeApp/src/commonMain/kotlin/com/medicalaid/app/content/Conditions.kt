package com.medicalaid.app.content

object Conditions {
    val pregnancy: List<Condition> = PregnancyConditions.all + ChronicConditions.pregnancy
    val general: List<Condition> = ChronicConditions.general + cardioRenalConditions + heartConditions + substanceConditions
    val all: List<Condition> = pregnancy + general

    private val index = all.associateBy { it.id }

    fun byId(id: String): Condition? = index[id]

    fun of(population: Population) = if (population == Population.Pregnancy) pregnancy else general
}
