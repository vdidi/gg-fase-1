package com.zup.vitorhugo.listartopicosredditjava;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

public class TransformBody {

    public List<String> transform(String resposta) {
        List<String> titulos = new ArrayList<String>();
        try {
            final Document documento = Jsoup.parse(resposta);

            for (Element tag : documento.select(
                    "h3"
            )){
                titulos.add(tag.select("h3").text());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return titulos;
    }

}
