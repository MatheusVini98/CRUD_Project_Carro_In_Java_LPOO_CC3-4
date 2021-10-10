package Modelo;

/**
 *
 * @author MatheusVinícius
 */

import DAL.CarroDAO;
import java.util.ArrayList;
import java.util.List;

public class Controle
{
    public String mensagem;
    
    public void cadastrarCarros(List<String> dadosCarro)
    {
        this.mensagem = "";
        Validacao validacao = new Validacao();
        validacao.validarDadosCarro(dadosCarro);
        if (validacao.getMensagem().equals(""))
        {
            Carro carro = new Carro();
            carro.setId(validacao.getId());
            carro.setFabricante(dadosCarro.get(1));
            carro.setModelo(dadosCarro.get(2));
            carro.setCor(dadosCarro.get(3));
            carro.setAno_fabricacao(Integer.parseInt(dadosCarro.get(4)));
            carro.setValor(Float.parseFloat(dadosCarro.get(5)));
            CarroDAO carroDAO = new CarroDAO();
            carroDAO.cadastrarCarro(carro);
            this.mensagem = carroDAO.getMensagem();
        }
        else
        {
            this.mensagem = validacao.getMensagem();
        }
    }
    
    public void editarCarros(List<String> dadosCarro)
    {
        this.mensagem = "";
        Validacao validacao = new Validacao();
        validacao.validarDadosCarro(dadosCarro);
        if (validacao.getMensagem().equals(""))
        {
            Carro carro = new Carro();
            carro.setId(validacao.getId());
            carro.setFabricante(dadosCarro.get(1));
            carro.setModelo(dadosCarro.get(2));
            carro.setCor(dadosCarro.get(3));
            carro.setAno_fabricacao(Integer.parseInt(dadosCarro.get(4)));
            carro.setValor(Float.parseFloat(dadosCarro.get(5)));
            CarroDAO carroDAO = new CarroDAO();
            carroDAO.editarCarro(carro);
            this.mensagem = carroDAO.getMensagem();
        }
        else
        {
            this.mensagem = validacao.getMensagem();
        }
    
        
    }
     
    public void excluirCarro(String numeroId)
    {
        this.mensagem = "";
        Validacao validacao = new Validacao();
        validacao.validarIdCarro(numeroId);
        Carro carro = new Carro();
        if(validacao.getMensagem().equals(""))
        {
            CarroDAO carroDAO = new CarroDAO();
            carro.setId(validacao.getId());
            carroDAO.excluirCarro(carro);
            this.mensagem = carroDAO.getMensagem();
        }
        else
        {
            this.mensagem = validacao.getMensagem();
        }
    }
            
    public List<Carro> pesquisarCarroPorFabricante(String nomeFabricante)
    {
        this.mensagem ="";
        Validacao validacao = new Validacao();
        validacao.validarFabricanteCarro(nomeFabricante);
        Carro carro = new Carro();
        List<Carro> listaCarros = new ArrayList<>();
        if (validacao.getMensagem().equals(""))
        {
            carro.setFabricante(nomeFabricante);
            CarroDAO carroDAO = new CarroDAO();
            listaCarros = carroDAO.pesquisarCarroPorFabricante(carro);
            this.mensagem = carroDAO.getMensagem();
        }
        else
        {
            this.mensagem = validacao.getMensagem();
        }
        return listaCarros;
    }
            
    public List<Carro> pesquisarCarroPorModelo(String nomeModelo)
    {
        this.mensagem ="";
        Validacao validacao = new Validacao();
        validacao.validarModeloCarro(nomeModelo);
        Carro carro = new Carro();
        List<Carro> listaCarros = new ArrayList<>();
        if (validacao.getMensagem().equals(""))
        {
            carro.setModelo(nomeModelo);
            CarroDAO carroDAO = new CarroDAO();
            listaCarros = carroDAO.pesquisarCarroPorModelo(carro);
            this.mensagem = carroDAO.getMensagem();
        }
        else
        {
            this.mensagem = validacao.getMensagem();
        }
        return listaCarros;
    }

    public String getMensagem()
    {
        return mensagem;
    }

    public void setMensagem(String mensagem)
    {        
        this.mensagem = mensagem;
    }

    
}
