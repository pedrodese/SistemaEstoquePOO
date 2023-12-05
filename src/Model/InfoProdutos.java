package Model;

import java.util.ArrayList;

public class InfoProdutos{
    private ArrayList<Produto> produtos = new ArrayList<>();

    public ArrayList<Produto> getProdutos(){
        return produtos;
    }

    public void setProdutos(Produto produto) {
        this.produtos.add(produto);
    }
}
