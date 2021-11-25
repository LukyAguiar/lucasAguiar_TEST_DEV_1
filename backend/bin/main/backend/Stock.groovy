package backend

class Stock {

    /*Atributos*/
    Double price;
    Date priceDate;
    /*Relacionamento entre as Classe|Tabelas*/
    static belongsTo = [comp:Company]

    static constraints = {

        price nullable: false, blank: false

        priceDate nullable: false, blank: false

    }
}
