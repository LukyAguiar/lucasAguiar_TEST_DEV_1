package backend

class StockController {

    def index() {

        stockService.getStocks("Ivory", 50)

    }

    def stockService

      def list() {
        render stockService.getCompanies();
    }

    def getStocks(String empresa, int horas){

        try{

            stockService.getStocks(empresa, horas)

        }catch(e){

            flash.message = "Não é possivel acessar esses dados!"
        }

    }




}
