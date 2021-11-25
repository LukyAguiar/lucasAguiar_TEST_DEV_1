package backend;

public class CompanyDTO {
    String nome;
    String segmento;
    double desvioPadrao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CompanyDTO(String nome, String segmento, double desvioPadrao) {
        this.nome = nome;
        this.segmento = segmento;
        this.desvioPadrao = desvioPadrao;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public double getDesvioPadrao() {
        return desvioPadrao;
    }

    public void setDesvioPadrao(double desvioPadrao) {
        this.desvioPadrao = desvioPadrao;
    }
}