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
                stmt.setInt(4, carro.getAnoFabricao());
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
                String comSql = "Update Carros "
                        + "set fabricante = ?, "
                        + "modelo = ?, "
                        + "cor = ?, "
                        + "ano_fabricacao = ?, "
                        + "valor = ? "
                        + "where id = ?";
                PreparedStatement stmt = con.prepareStatement(comSql);
                stmt.setString(1, carro.getFabricante());
                stmt.setString(2, carro.getModelo());
                stmt.setString(3, carro.getCor());
                stmt.setInt(4, carro.getAnoFabricao());
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
            System.out.println(e);
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
                carro = this.pesquisarCarroPorId(carro);
                if(carro.getFabricante() == null || carro.getFabricante().equals(""))
                {
                    this.mensagem = "Não existe carro para excluir";
                }
                else
                {
                    String comSql = "delete from Carros where id = ?";
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
            System.out.println(e);
//            this.mensagem = e.getMessage();
            this.mensagem = "Erro de gravação no BD";
        }
    }
    
    public Carro pesquisarCarroPorId(Carro carro)
    {
        this.mensagem = "";
        try
        {
            Connection con = conexao.conectar();
            if (conexao.getMensagem().equals(""))
            {
                String comSql = "select * from Carros where id = ?";
                PreparedStatement stmt = con.prepareStatement(comSql);
                stmt.setInt(1, carro.getId());
                ResultSet resultSet = stmt.executeQuery();                      //quando se tem resposta
                if (resultSet.next())                                           //se conseguir dar next significa que conseguiu um id
                {
                    carro.setFabricante(resultSet.getString("fabricante"));
                    carro.setModelo(resultSet.getString("modelo"));
                    carro.setCor(resultSet.getString("cor"));
                    carro.setAnoFabricao(resultSet.getInt("ano_fabricacao"));
                    carro.setValor(resultSet.getFloat("valor"));
                }
                else
                {
                    this.mensagem = "não existe este ID";
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
            this.mensagem = "erro de leitura no BD";                            // para o cliente
            //this.mensagem = e.getMessage();                                   //para desenvolvimento
        }
        return carro;
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
                    carroLista.setAnoFabricao(resultset.getInt("ano_fabricacao"));
                    carroLista.setValor(resultset.getFloat("valor"));
                    listaCarros.add(carroLista);
                }
                conexao.desconectar();
//            this.mensagem = "Carro cadastrada com sucesso!";                
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
                stmt.setString(1, carro.getModelo()+ "%");
                ResultSet resultset = stmt.executeQuery();
                while (resultset.next())
                {
                    Carro carroLista = new Carro();
                    carroLista.setId(resultset.getInt("id"));
                    carroLista.setFabricante(resultset.getString("fabricante"));
                    carroLista.setModelo(resultset.getString("modelo"));
                    carroLista.setCor(resultset.getString("cor"));
                    carroLista.setAnoFabricao(resultset.getInt("ano_fabricacao"));
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
