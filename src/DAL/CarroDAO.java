package DAL;

import Modelo.Carro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO
{

    private String mensagem;
    Conexao conexao = new Conexao();

    public void cadastrarCarro(Carro carro)
    {
        this.mensagem = "";
        try
        {
            Connection con = conexao.conectar();
            if (conexao.getMensagem().equals(""))
            {
                String comSql = "Insert into Carros (fabricante, modelo, cor, ano_fabricacao, valor)"
                        + "values(?,?,?,?,?)";
                PreparedStatement stmt = con.prepareStatement(comSql);
                stmt.setString(1, carro.getFabricante());
                stmt.setString(2, carro.getModelo());
                stmt.setString(3, carro.getCor());
                stmt.setInt(4, carro.getAno_fabricacao());
                stmt.setFloat(5, carro.getValor());
                stmt.execute();
                conexao.desconectar();
                this.mensagem = "Carro cadastrado com sucesso!";
            }
            else
            {
                this.mensagem = conexao.getMensagem();
            }
        }
        catch (Exception e)
        {
//            this.mensagem = e.getMessage();
            this.mensagem = "Erro de gravação no BD";
        }
    }

    public void editarCarro(Carro carro)
    {

        this.mensagem = "";
        try
        {
            Connection con = conexao.conectar();
            if (conexao.getMensagem().equals(""))
            {
                String comSql = "Update Carro "
                        + "set fabricante = ?,"
                        + "modelo = ?,"
                        + "cor = ? "
                        + "ano_fabricacao = ? "
                        + "valor = ? "
                        + "where id = ?";
                PreparedStatement stmt = con.prepareStatement(comSql);
                stmt.setString(1, carro.getFabricante());
                stmt.setString(2, carro.getModelo());
                stmt.setString(3, carro.getCor());
                stmt.setInt(4, carro.getAno_fabricacao());
                stmt.setFloat(5, carro.getValor());
                stmt.setInt(6, carro.getId());
                stmt.execute();
                conexao.desconectar();
                this.mensagem = "Carro editado com sucesso!";
            }
            else
            {
                this.mensagem = conexao.getMensagem();
            }
        }
        catch (Exception e)
        {
//            this.mensagem = e.getMessage();
            this.mensagem = "Erro de gravação no BD";
        }
    }

    public void excluirCarro(Carro carro)
    {
        this.mensagem = "";
        try
        {
            Connection con = conexao.conectar();
            if (conexao.getMensagem().equals(""))
            {
                carro = (Carro) this.pesquisarCarroPorFabricante(carro);
                if(carro.getFabricante() == null || carro.getFabricante().equals(""))
                {
                    this.mensagem = "Não existe carro para excluir";
                }
                else
                {
                    String comSql = "Delete from Carros where id = ?";
                    PreparedStatement stmt = con.prepareStatement(comSql);
                    stmt.setInt(1, carro.getId());
                    stmt.execute();
                    conexao.desconectar();
                    this.mensagem = "Carro excluido com sucesso!";                    
                }
               
            }
            else
            {
                this.mensagem = conexao.getMensagem();
            }
        }
        catch (Exception e)
        {
//            this.mensagem = e.getMessage();
            this.mensagem = "Erro de gravação no BD";
        }
    }

    public List<Carro> pesquisarCarroPorFabricante(Carro carro)
    {
        this.mensagem = "";
        List<Carro> listaCarros = new ArrayList<>();
        try
        {
            Connection con = conexao.conectar();
            if (conexao.getMensagem().equals(""))
            {
                String comSql = "select * from carros where fabricante like ?";
                PreparedStatement stmt = con.prepareStatement(comSql);
                stmt.setString(1, carro.getFabricante()+ "%");
                ResultSet resultset = stmt.executeQuery();
                while (resultset.next())
                {
                    Carro carroLista = new Carro();
                    carroLista.setId(resultset.getInt("id"));
                    carroLista.setFabricante(resultset.getString("fabricante"));
                    carroLista.setModelo(resultset.getString("modelo"));
                    carroLista.setCor(resultset.getString("cor"));
                    carroLista.setAno_fabricacao(resultset.getInt("ano_fabricacao"));
                    carroLista.setValor(resultset.getFloat("valor"));
                    listaCarros.add(carroLista);
                }
                conexao.desconectar();
//            this.mensagem = "Pessoa cadastrada com sucesso!";                
            }
            else
            {
                this.mensagem = conexao.getMensagem();
            }
        }
        catch (Exception e)
        {
//            this.mensagem = e.getMessage();
            this.mensagem = "Erro de leitura no BD";
        }
        return listaCarros;
    }
    
        public List<Carro> pesquisarCarroPorModelo(Carro carro)
    {
        this.mensagem = "";
        List<Carro> listaCarros = new ArrayList<>();
        try
        {
            Connection con = conexao.conectar();
            if (conexao.getMensagem().equals(""))
            {
                String comSql = "select * from carros where modelo like ?";
                PreparedStatement stmt = con.prepareStatement(comSql);
                stmt.setString(2, carro.getModelo()+ "%");
                ResultSet resultset = stmt.executeQuery();
                while (resultset.next())
                {
                    Carro carroLista = new Carro();
                    carroLista.setId(resultset.getInt("id"));
                    carroLista.setFabricante(resultset.getString("fabricante"));
                    carroLista.setModelo(resultset.getString("modelo"));
                    carroLista.setCor(resultset.getString("cor"));
                    carroLista.setAno_fabricacao(resultset.getInt("ano_fabricacao"));
                    carroLista.setValor(resultset.getFloat("valor"));
                    listaCarros.add(carroLista);
                }
                conexao.desconectar();
            }
            else
            {
                this.mensagem = conexao.getMensagem();
            }
        }
        catch (Exception e)
        {
//            this.mensagem = e.getMessage();
            this.mensagem = "Erro de leitura no BD";
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
