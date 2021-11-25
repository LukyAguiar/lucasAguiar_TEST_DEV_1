package backend


class Company {

    /*Atributos*/
    String nome;
    String segmento;
    /*Relacionamento entre as Classe|Tabelas*/
    static hasMany=[stock:Stock]

    static constraints = {
        /*Caracteristicas para preencher as variaveis*/
        /*maxSize = No maximo 255 caracteres - 
          nullable = permite uma propriedade seja nula 
          blank = Valida se um valor string não está em branco
          */
        nome maxSize: 255, nullable: false, blank: false, unique: true
        segmento maxSize: 255, nullable: false, blank: false
    }

    /*Mapeamento da tabela pro Banco de dados*/
    static mapping = {
        autowire: true
    }
}
