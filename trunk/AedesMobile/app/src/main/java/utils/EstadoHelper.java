package utils;

import java.util.HashMap;

/**
 * Created by Alexandre on 26/05/2015.
 */
public final class EstadoHelper {
    private HashMap<String, String> estado;

    public EstadoHelper() {
        estado = new HashMap<>();
        estado.put("Acre", "AC");
        estado.put("Alagoas", "AL");
        estado.put("Amapá", "AP");
        estado.put("Amazonas", "AM");
        estado.put("Bahia", "BA");
        estado.put("Ceará", "CE");
        estado.put("Distrito Federal", "DF");
        estado.put("Espirito Santo", "ES");
        estado.put("Goiás", "GO");
        estado.put("Maranhão", "MA");
        estado.put("Mato Grosso", "MT");
        estado.put("Mato Grosso do Sul", "MS");
        estado.put("Minas Gerais", "MG");
        estado.put("Pará", "PA");
        estado.put("Paraíba", "PB");
        estado.put("Paraná", "PR");
        estado.put("Pernambuco", "PE");
        estado.put("Piauí", "PI");
        estado.put("Rio de Janeiro", "RJ");
        estado.put("Rio Grande do Norte", "RN");
        estado.put("Rio Grande do Sul", "RS");
        estado.put("Rondônia", "RO");
        estado.put("Roraima", "RR");
        estado.put("Santa Catarina", "SC");
        estado.put("São Paulo", "SP");
        estado.put("Sergipe", "SE");
        estado.put("Tocantins", "TO");
    }

    public String getEstadoAbreviatura(String nmEstado) {
        String abreviatura = estado.get(nmEstado);

        if(abreviatura == null){
            return "";
        }

        return abreviatura;
    }


}
