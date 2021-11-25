package backend

import grails.gorm.transactions.Transactional
import java.sql.Timestamp
import java.util.*

@Transactional
class StockService {


    def getStocks(String empresa, int horas) {

        int stockCount = 0;

        long start = System.currentTimeMillis();

        final Timestamp time = new Timestamp(System.currentTimeMillis() - java.time.Duration.ofHours(horas).toMillis())
        
        /*Executando uma QUery Select buscando nome e o preço por data em horas.*/
        def stocks = Stock.executeQuery("SELECT s FROM Stock s, Company c " +
                "WHERE s.comp = c.id " +
                "AND c.nome LIKE :empresa " +
                "AND s.priceDate > :horas", [empresa:empresa, horas:time])

        println "\n"
        println "Quotes: "

        stocks.each { stock ->

            println "${stock.price}"
            stockCount++
        }

        println "\n"

        long end = System.currentTimeMillis()

        float elapsedTime = (end - start) / 1000F;
        println "Time elapsed: " + elapsedTime + "\n"
        println "Quotes retrieved: ${stockCount} \n"


    }

    /*Abaixo Funções relacionadas ao Front */

    public static double desvioPadrao(ArrayList<Double> numArray)
    {
        double sum = 0.0, standardDeviation = 0.0;
        int length = numArray.size();

        for(String num : numArray) {
            sum += Double.valueOf(num);
        }

        double mean = sum/length;

        for(String num: numArray) {
            standardDeviation += Math.pow(Double.valueOf(num) - mean, 2);
        }

        return Math.sqrt(standardDeviation/length);
    }

    /*Método Get para o botão principal para buscar as instâncias com dados falsificados*/
    def getCompanies() {

        ArrayList<CompanyDTO> companies = new ArrayList<CompanyDTO>();

        def companiesQuery = Company.executeQuery("SELECT c FROM Company c")

        companiesQuery.each { company ->

            ArrayList<Double> stockPrices = new ArrayList<>();

            def stocks = Stock.executeQuery("SELECT s FROM Stock s, Company c " +
                    "WHERE s.comp = c.id " +
                    "AND c.nome LIKE :empresa" , [empresa:"${company.nome}"])

            stocks.each { stock ->

                stockPrices.add("${stock.price}")

            }

            double desvioPadrao = desvioPadrao(stockPrices)

            println "${desvioPadrao}"

            CompanyDTO companyDTO = new CompanyDTO("${company.nome}","${company.segmento}", desvioPadrao)

            companies.add(companyDTO)

        }

        def builder = new groovy.json.JsonBuilder(companies)
        return builder.toString()

    }

}
