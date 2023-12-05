
package View;

import Model.Cupons;
import Model.Produto;
import javax.swing.*;
import java.util.ArrayList;

public class EntradaSaida {

    public static int mostraMenuZero() {
        String[] opcoes = {"Registrar um novo Produto", "Sair"};

        JComboBox menu = new JComboBox(opcoes);
        JOptionPane.showConfirmDialog(null, menu, "Selecione a opção desejada", JOptionPane.DEFAULT_OPTION);
        return menu.getSelectedIndex();
    }

    public static int mostraMenu() {
        String[] opcoes = {"Registrar um novo produto", "Mostrar produtos registrados", "Registrar produto no estoque"
                , "Mostrar produtos em estoque", "Vender produto", "Mostrar todas as vendas", "Mostrar vendas por produto",
                "Calcular os valores das vendas", "Sair"};

        JComboBox menu = new JComboBox(opcoes);
        JOptionPane.showConfirmDialog(null, menu, "", JOptionPane.DEFAULT_OPTION);
        return menu.getSelectedIndex();
    }


    public static void mostraMensagemEstoqueVazio(){
        JOptionPane.showMessageDialog(null, "Você não possui produtos em estoque, registre os produtos!",
                "Alerta", JOptionPane.ERROR_MESSAGE);
    }

    public static String getCodigoInput() {
        return JOptionPane.showInputDialog(null, "Qual o código do produto que deseja cadastrar?",
                "Registro de Produto", JOptionPane.PLAIN_MESSAGE);
    }

    public static String getDescricaoInput() {
        return JOptionPane.showInputDialog(null, "Descrição do Produto: ",
                "Registro de Produto", JOptionPane.PLAIN_MESSAGE);
    }

    public static double getPrecoInput() {
        double contador;
        do{
            contador = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o preço do produto: ","Registro de Produto", JOptionPane.PLAIN_MESSAGE));
            if(contador <= 0.0){
                JOptionPane.showMessageDialog(null, "Digite um valor válido!", "Valor Inválido!", JOptionPane.ERROR_MESSAGE);
            }
        }while (contador <= 0.0);
        return contador;
    }

    public static void mostraProdutosRegistrados(ArrayList<Produto> produtos, int index) {
        String desc = "Código: " + produtos.get(index).getCodigo() + "\n" +
                "Descrição: " + produtos.get(index).getDescricao() + "\n" +
                "Preço: R$ " + produtos.get(index).getPreco();

        JOptionPane.showMessageDialog(null, desc, "Descrição do Produto",
                JOptionPane.PLAIN_MESSAGE);
    }

    public static int indexSelecionado(ArrayList<Produto> produtos) {
        String[] itens = new String[produtos.size()+1];

        for (int x = 0; x < produtos.size()+1; x++) {
            try{
                itens[x] = "Código: " + produtos.get(x).getCodigo();
            }
            catch (IndexOutOfBoundsException e){
                itens[x] = "Return";
            }

        }

        JComboBox menu = new JComboBox<>(itens);

        JOptionPane.showMessageDialog(null, menu, "Lista de Códigos dos Produtos", JOptionPane.PLAIN_MESSAGE);

        return menu.getSelectedIndex();
    }

    public static int setTotal(){
        int contador;
        do{
            contador = Integer.parseInt(JOptionPane.showInputDialog(null, "Quantos itens você deseja cadastrar?","Registrar Produto", JOptionPane.PLAIN_MESSAGE));
            if(contador <=0){
                JOptionPane.showMessageDialog(null, "Digite um valor válido!", "Valor Inválido", JOptionPane.ERROR_MESSAGE);
            }
        }while (contador <= 0);
        return contador;
    }

    public static void mostraMensagemSucessoCadastroEstoque(){
        JOptionPane.showMessageDialog(null,"O produto foi cadastrado com sucesso!","Registrar Produto no Estoque", JOptionPane.PLAIN_MESSAGE);
    }

    public static void mostraMenuEstoque(ArrayList<Produto> produtos, int index) {
        String desc = "Código: " + produtos.get(index).getCodigo() + "\n" +
                "Descrição: " + produtos.get(index).getDescricao() + "\n" +
                "Preço: R$" + produtos.get(index).getPreco() + "\n" +
                "Total de Produtos no Estoque: " + produtos.get(index).getTotal();

        JOptionPane.showMessageDialog(null, desc, "Descrição Total do Produto no Estoque",
                JOptionPane.PLAIN_MESSAGE);
    }

    public static int menuSelecionaQTDvendas(int valorTotal){
        int total;
        do{
            total = Integer.parseInt(JOptionPane.showInputDialog(null, "Qual o total de produtos que deseja vender?\n Quantidade atual no estoque: " + valorTotal
                    , "Venda de Produto", JOptionPane.PLAIN_MESSAGE));
            if (total<=0){
                JOptionPane.showMessageDialog(null, "Você deve digitar um numero válido!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }while (total<=0);
        return total;
    }

    public static void mostraTodosCupons(ArrayList<Cupons> cuponsTotais){
        String mensagem = "";

        for(int x=0; x<cuponsTotais.size(); x++){
            mensagem += "Código: " + cuponsTotais.get(x).getCodigo() + "\n" +
                    "Data da Venda: " + cuponsTotais.get(x).getData() + "\n" +
                    "Total Vendido: " + cuponsTotais.get(x).getTotalVendido() + "\n" +
                    "Preço Total: R$" + cuponsTotais.get(x).getPrecoTotal() + "\n\n";

        }

        JOptionPane.showMessageDialog(null, mensagem,"Vendas Totais", JOptionPane.PLAIN_MESSAGE);
    }

    public static void mostraCuponsProdutos(ArrayList<Cupons> cupons){
        String mensagem = "";

        for (Cupons cupon : cupons){
            mensagem += "Código: " + cupon.getCodigo() + "\n" +
                        "Data da Venda: " + cupon.getData() + "\n" +
                        "Total Vendido: " + cupon.getTotalVendido() + "\n" +
                        "Preço Total: R$" + cupon.getPrecoTotal() + "\n\n";
        }

        JOptionPane.showMessageDialog(null, mensagem,"Vendas Totais", JOptionPane.PLAIN_MESSAGE);
    }

    public static void calculaCuponsTotais(ArrayList<Cupons> cuponsTotais){
        double soma = 0.0;

        for (Cupons cuponsTotai : cuponsTotais) {
            soma += cuponsTotai.getPrecoTotal();

        }

        JOptionPane.showMessageDialog(null, ("Valor Total: " + soma),"Vendas Totais", JOptionPane.PLAIN_MESSAGE);
    }

    public static int produtosVendidos(int total, int valorAtual){
        return valorAtual - total;
    }

    public static void erroMensagemVenda(int total){
        JOptionPane.showMessageDialog(null, "Você não pode realizar a operação, pois o valor do estoque é menor do que deseja!\n\n\tValor Atual " + total, "ERRO", JOptionPane.ERROR_MESSAGE);
    }

    public static void mensagemVendaSucesso(int valorAtual){
        JOptionPane.showMessageDialog(null, "Venda concluida ! "+ " A quantidade em estoque atual do produto é: " + valorAtual, "Venda Concluida com Sucesso!", JOptionPane.PLAIN_MESSAGE);
    }

    public static void exibeMensagemDespedida(){
        JOptionPane.showMessageDialog(null,"Obrigado por utilizar o StockPro! Volte sempre :)");
    }
}
