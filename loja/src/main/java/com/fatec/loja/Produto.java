package com.fatec.loja;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produto {
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    private String modelo;
    private String marca;
    private String descritivo;
    private double valor;
    private double promo;
    private boolean destaque = false;
    private Date anoFabricacao;
    private int kmRodados;
    private String placa;

    @ElementCollection
    private List<String> keywords = new ArrayList<>();

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getDescritivo() {
        return descritivo;
    }
    public void setDescritivo(String descritivo) {
        this.descritivo = descritivo;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public double getPromo() {
        return promo;
    }
    public void setPromo(double promo) {
        this.promo = promo;
    }
    public int getKmRodados() {
        return kmRodados;
    }
    public void setKmRodados(int kmRodados) {
        this.kmRodados = kmRodados;
    }
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public boolean isDestaque() {
        return destaque;
    }
    public void setDestaque(boolean destaque) {
        this.destaque = destaque;
    }

    public List<String> getKeywords() {
        return keywords;
    }
    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
    public Date getAnoFabricacao() {
        return anoFabricacao;
    }
    public void setAnoFabricacao(Date anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

}
