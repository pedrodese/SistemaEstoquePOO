package Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import Model.Cupons;
import Model.InfoProdutos;
import Model.Produto;
import Model.Estoque;
import View.EntradaSaida;



public class Controladora {
        Estoque estoque = new Estoque();
        InfoProdutos infoprodutos = new InfoProdutos();
        int opcaoSelecionada;
        int index;
        int total;
        int novoTotal;
        int totalAtual;

        public void mostraMenu(){

            do {
                if(infoprodutos.getProdutos().isEmpty()){
                    opcaoSelecionada = EntradaSaida.mostraMenuZero();
                }
                else{
                    opcaoSelecionada = EntradaSaida.mostraMenu();
                }

                switch (opcaoSelecionada){
                    case 0: // CRIAR PRODUTO
                        Produto produto = new Produto();
                        produto.setCodigo(EntradaSaida.getCodigoInput());
                        produto.setDescricao(EntradaSaida.getDescricaoInput());
                        produto.setPreco(EntradaSaida.getPrecoInput());
                        infoprodutos.setProdutos(produto);
                        break;

                    case 1: // MOSTRA PRODUTOS REGISTRADOS
                        if(infoprodutos.getProdutos().isEmpty()){
                            System.exit(0);
                        }
                        index = EntradaSaida.indexSelecionado(infoprodutos.getProdutos());
                        if(index != infoprodutos.getProdutos().size()){
                            EntradaSaida.mostraProdutosRegistrados(infoprodutos.getProdutos(), index);
                        }
                        break;

                    case 2: // REGISTRA PRODUTOS NO ESTOQUE
                        index = EntradaSaida.indexSelecionado(infoprodutos.getProdutos());
                        if(index != infoprodutos.getProdutos().size()){
                            infoprodutos.getProdutos().get(index).setTotal(EntradaSaida.setTotal());
                            estoque.setProdutos(infoprodutos.getProdutos().get(index));
                            EntradaSaida.mostraMensagemSucessoCadastroEstoque();
                        }
                        break;

                    case 3: // MOSTRA PRODUTOS ESTOQUE
                        if (estoque.getProdutos().isEmpty()){
                            EntradaSaida.mostraMensagemEstoqueVazio();
                        }
                        else{
                            index = EntradaSaida.indexSelecionado(estoque.getProdutos());
                            if(index != infoprodutos.getProdutos().size()){
                                EntradaSaida.mostraMenuEstoque(estoque.getProdutos(), index);
                            }
                        }

                        break;

                    case 4: // VENDER PRODUTO DO ESTOQUE
                        if (estoque.getProdutos().isEmpty()){
                            EntradaSaida.mostraMensagemEstoqueVazio();
                        }
                        else {
                            index = EntradaSaida.indexSelecionado(estoque.getProdutos());
                            if(index != infoprodutos.getProdutos().size()){
                                total = EntradaSaida.menuSelecionaQTDvendas(estoque.getProdutos().get(index).getTotal());
                                totalAtual = estoque.getProdutos().get(index).getTotal();
                                novoTotal = EntradaSaida.produtosVendidos(total, totalAtual);

                                if(total > totalAtual){
                                    EntradaSaida.erroMensagemVenda(totalAtual);
                                }
                                else if(total == totalAtual){
                                    Cupons cupom = new Cupons();
                                    cupom.setCodigo(estoque.getProdutos().get(index).getCodigo());
                                    cupom.setData("Data de Venda: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
                                    cupom.setTotalVendido(total);
                                    cupom.setPrecoTotal(total * estoque.getProdutos().get(index).getPreco());
                                    estoque.getProdutos().get(index).setCupons(cupom);
                                    estoque.setCuponsTotais(cupom);

                                    estoque.getProdutos().remove(index);
                                    EntradaSaida.mensagemVendaSucesso(novoTotal);
                                }
                                else{
                                    Cupons cupom = new Cupons();
                                    cupom.setCodigo(estoque.getProdutos().get(index).getCodigo());
                                    cupom.setData("Data da Venda: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
                                    cupom.setTotalVendido(total);
                                    cupom.setPrecoTotal(total * estoque.getProdutos().get(index).getPreco());
                                    estoque.getProdutos().get(index).setCupons(cupom);
                                    estoque.setCuponsTotais(cupom);

                                    estoque.getProdutos().get(index).setTotal(novoTotal);
                                    EntradaSaida.mensagemVendaSucesso(novoTotal);
                                }
                            }
                        }
                        break;
                    case 5: // MOSTRA TODAS AS VENDAS
                        EntradaSaida.mostraTodosCupons(estoque.getCuponsTotais());
                        break;

                    case 6: // MOSTRA VENDAS POR PRODUTO
                        if (estoque.getProdutos().isEmpty()){
                            EntradaSaida.mostraMensagemEstoqueVazio();
                        }
                        else{
                            index = EntradaSaida.indexSelecionado(estoque.getProdutos());
                            if(index != infoprodutos.getProdutos().size()){
                                EntradaSaida.mostraCuponsProdutos(estoque.getProdutos().get(index).getCupons());
                            }
                        }
                        break;

                    case 7: // CALCULA TODOS AS VENDAS
                        EntradaSaida.calculaCuponsTotais(estoque.getCuponsTotais());
                        break;

                    case 8:
                        EntradaSaida.exibeMensagemDespedida();
                        System.exit(0);
                }
            }while (true);
        }
    }
