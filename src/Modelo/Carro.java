package Modelo;

/**
 *
 * @author MatheusVinícius
 */
public class Carro
{
    private int id;
    private String fabricante;
    private String modelo;
    private String cor;
    private int anoFabricao;
    private float valor;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFabricante()
    {
        return fabricante;
    }

    public void setFabricante(String fabricante)
    {
        this.fabricante = fabricante;
    }

    public String getModelo()
    {
        return modelo;
    }

    public void setModelo(String modelo)
    {
        this.modelo = modelo;
    }

    public String getCor()
    {
        return cor;
    }

    public void setCor(String cor)
    {
        this.cor = cor;
    }

    public int getAnoFabricao()
    {
        return anoFabricao;
    }

    public void setAnoFabricao(int anoFabricao)
    {
        this.anoFabricao = anoFabricao;
    }

    public float getValor()
    {
        return valor;
    }

    public void setValor(float valor)
    {
        this.valor = valor;
    }
    
    
    
}
