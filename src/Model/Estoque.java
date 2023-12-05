package Model;

import java.util.ArrayList;

public class Estoque{
    private ArrayList<Produto> produtos = new ArrayList<>();
    private ArrayList<Cupons> cuponsTotais = new ArrayList<>();

    public ArrayList<Cupons> getCuponsTotais(){
        return cuponsTotais;
    }

    public void setCuponsTotais(Cupons cuponsTotais){
        this.cuponsTotais.add(cuponsTotais);
    }

    public ArrayList<Produto> getProdutos(){
        return produtos;
    }

    public void setProdutos(Produto produtos) {
        this.produtos.add(produtos);
    }

}
