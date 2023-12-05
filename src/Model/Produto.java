package Model;

import java.util.ArrayList;

public class Produto {
    private String codigo;
    private String descricao;
    private double preco;
    private int total;
    private final ArrayList<Cupons> cupons = new ArrayList<>();

    public ArrayList<Cupons> getCupons() {
        return cupons;
    }

    public void setCupons(Cupons cupons) {
        this.cupons.add(cupons);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
