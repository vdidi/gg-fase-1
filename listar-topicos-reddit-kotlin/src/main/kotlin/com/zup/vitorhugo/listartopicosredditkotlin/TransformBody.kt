package com.zup.vitorhugo.listartopicosredditkotlin

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import javax.swing.text.Document

class TransformBody () {
    fun transform(resposta: String): MutableList<String> {
        var titulos = mutableListOf<String>()

        try {
            val documento: org.jsoup.nodes.Document? = Jsoup.parse(resposta)

            if (documento != null) {
                documento.select("h3").forEachIndexed { index, element ->
                    titulos.add(element.select("h3").text())
                }
            }
        }catch (e: Exception) {

        }

        return titulos
    }
}