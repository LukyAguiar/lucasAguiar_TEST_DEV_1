package backend

import java.sql.Timestamp
import java.time.LocalDate
import java.time.chrono.ChronoLocalDate
import java.time.chrono.Chronology
import java.time.chrono.IsoChronology
import java.time.temporal.ChronoUnit

class BootStrap {

    def init = { servletContext ->

        int max = 10
        int min = 1
        int range = max - min + 1

        def riot = new Company(nome: 'Riot', segmento: 'Games').save(failOnError: true)

        def ivory = new Company(nome: 'Ivory', segmento: 'Consultoria').save(failOnError: true)

        def ford = new Company(nome: 'Ford', segmento: 'Automovéis').save(failOnError: true)

        Timestamp tsAtual = new Timestamp(System.currentTimeMillis())

        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(tsAtual.getTime())

        cl.set(Calendar.DATE, tsAtual.getDay() - 31)

        Timestamp tsInicial = new Timestamp(System.currentTimeMillis())
        tsInicial.setTime(cl.getTimeInMillis())

        tsInicial.setHours(10)
        tsInicial.setMinutes(0)

        Random rn = new Random()

        /*For para calcular o tempo de Preço em Data*/
        for(int i = 30; i > 0; i--){

            while (tsInicial.getHours() < 18) {

                int rand = rn.nextInt(range) + min
                int rand1 = rn.nextInt(range) + min
                int rand2 = rn.nextInt(range) + min

                Stock stock = new Stock(price: rand, priceDate: tsInicial)
                stock.comp = riot
                stock.save(failOnError: true)

                Stock stock1 = new Stock(price: rand1, priceDate: tsInicial)
                stock1.comp = ivory;
                stock1.save(failOnError: true)

                Stock stock2 = new Stock(price: rand2, priceDate: tsInicial)
                stock2.comp = ford
                stock2.save(failOnError: true)

                tsInicial.setMinutes(tsInicial.getMinutes() + 1)

            }

            cl.setTimeInMillis(tsAtual.getTime())
            cl.set(Calendar.DATE, tsAtual.getDay() - i)

            tsInicial.setTime(cl.getTimeInMillis())

            tsInicial.setHours(10)
            tsInicial.setMinutes(0)

        }


    }
    def destroy = {
    }
}
