package br.com.brigaderiafina.brigaderiafina.adapters;

import java.util.ArrayList;

import br.com.brigaderiafina.brigaderiafina.R;

public class MenuInfo {

    public String  mMenuInfo;

    public MenuInfo(){

    }

    public MenuInfo(String menuInfo){
        this.mMenuInfo  = menuInfo;

    }

    public static ArrayList<MenuInfo> getMenuInfo(int menuItemId){

        ArrayList<MenuInfo> menuInfo = new ArrayList<MenuInfo>();

        if (menuItemId == R.id.action_order) {


            menuInfo.add(new MenuInfo("A Brigaderia Fina é um atelier de produção! Não possuímos loja física e nossos produtos são feitos apenas por encomenda."));

            menuInfo.add(new MenuInfo("Quantidade mínima para encomendas:"));
            menuInfo.add(new MenuInfo("     - Brigadeiros –20 unidades por sabor"));
            menuInfo.add(new MenuInfo("     - Tacinhas –20 unidades por sabor"));
            menuInfo.add(new MenuInfo("     - Cake Pops – 6 unidades por sabor"));
            menuInfo.add(new MenuInfo("     - Cupcakes –20 unidades por sabor"));
            menuInfo.add(new MenuInfo("     - Mini cupcakes – 12 unidades por sabor"));
            menuInfo.add(new MenuInfo("     - Outros – 20 unidades por sabor"));
            menuInfo.add(new MenuInfo("De segunda a sexta-feira os pedidos devem ser retirados no local de produção. Nos" +
                    " finais de semana a taxa de entrega para Porto Alegre varia de R$20 a R$50" +
                    " dependendo da localização"));

            menuInfo.add(new MenuInfo("Os pedidos devem ser feitos com pelo menos 15 dias de antecedência! Consulte a" +
                    " disponibilidade!"));
            menuInfo.add(new MenuInfo("Os doces são fornecidos em forminhas marrons impermeáveis, transparentes ou" +
                    " modelo 4 pétalas (cores a escolher)! O valor permanece o mesmo do catálogo!"));

            menuInfo.add(new MenuInfo("A confirmação do pedido/ reserva de data só é realizada após o pagamento de 50% do" +
                    " valor total do pedido (por depósito ou transferência bancária). O valor restante é pago" +
                    " impreterivelmente na entrega."));
            menuInfo.add(new MenuInfo("Em caso de cancelamento até 10 dias antes da entrega será feita a devolução de 70%" +
                    " do valor já pago. A devolução será efetivada através de depósito bancário em até 5" +
                    " dias úteis após o pedido de cancelamento. Em prazo menor que 10 dias, não será feita" +
                    " devolução de qualquer valor."));

            menuInfo.add(new MenuInfo("Ao confirmar o pedido o cliente está ciente das informações contidas neste catálogo."));


        }

        if (menuItemId == R.id.action_contact) {

            menuInfo.add(new MenuInfo("brigaderiafina@gmail.com"));
        }

        if (menuItemId == R.id.action_suggestion) {

            menuInfo.add(new MenuInfo("4 ou 5 doces por pessoa para eventos que tenham sobremesa/ bolo"));
            menuInfo.add(new MenuInfo("6 ou 7 doces por pessoa para coquetéis, coffee breaks e eventos sem sobremesa/ bolo"));
        }

        return menuInfo;

    }


}
