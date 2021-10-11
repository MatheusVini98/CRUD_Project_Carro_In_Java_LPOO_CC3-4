package Modelo;

import java.util.List;

public class Validacao
{

    private String mensagem;
    private Integer id;

    public void validarDadosCarro(List<String> dadosCarro)
    {
        this.mensagem = "";
        this.validarIdCarro(dadosCarro.get(0));                                 //ID
        this.validarFabricanteCarro(dadosCarro.get(1));                         //Fabricante
        this.validarModeloCarro(dadosCarro.get(2));                             //Modelo    
        this.validarCorCarro(dadosCarro.get(3));                                //Cor
        
        if (dadosCarro.get(4).length() < 3 || dadosCarro.get(4).length() > 4)   //Ano
        {
            this.mensagem += "Ano inválido \n";
        }
        if (dadosCarro.get(5).length() < 2 || dadosCarro.get(5).length() > 10)  //Valor
        {
            this.mensagem += "Valor Inválido \n";
        }
    }

    public void validarIdCarro(String numeroId)
    {
        this.mensagem = "";
        try
        {
            this.id = Integer.parseInt(numeroId); //ID
        }
        catch (Exception e)
        {
            this.mensagem += "ID inválido \n";
        }
    }

    public void validarFabricanteCarro(String fabricante)
    {
        if (fabricante.length() < 3 || fabricante.length() > 40)                //fabricante ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
        {
            this.mensagem += "O Nome da Fabricante deve ter de 3 a 40 caracteres \n";
        }
        else
            this.mensagem = "";
    }

    public void validarModeloCarro(String modelo)
    {
        if (modelo.length() < 3 || modelo.length() > 20)                        //modelo
        {
            this.mensagem += "O Nome do Modelo deve ter de 3 a 20 caracteres \n";
        }
        else
            this.mensagem = "";
    }

    public void validarCorCarro(String cor)
    {
        if (cor.length() < 3 || cor.length() > 15)                              //cor
        
        {
            this.mensagem += "O Nome da Cor deve ter de 3 a 15 caracteres \n";
        }
        else
            this.mensagem = "";
    }

    public String getMensagem()
    {
        return mensagem;
    }

    public Integer getId()
    {
        return id;
    }

}
