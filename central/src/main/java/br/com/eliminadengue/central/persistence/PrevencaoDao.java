package br.com.eliminadengue.central.persistence;

import br.com.eliminadengue.central.model.MatrizPrenvencao;
import br.com.eliminadengue.central.model.Endereco;
import br.com.eliminadengue.central.model.Foco;
import br.com.eliminadengue.central.model.Prevencao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Guilherme Alves
 */
@Named
@Perssiste
public class PrevencaoDao implements Dao<Prevencao> {

    @Inject private Connection connection;
    @Inject private FocoDao focoDao;

    public PrevencaoDao() {
    }

    @Override
    public void salvar(Prevencao prevencao) {
        if (encontrar(prevencao.getCodigoCelular(),
                prevencao.getFoco().getCodigo()) == null) {
            inserir(prevencao);
        } else {
            atualizar(prevencao);
        }
    }

    @Override
    public void inserir(Prevencao obj) {
        String sql = "insert into PREVENCAO(cod_celular, "
                + "cod_foco, dat_criacao, dat_prazo, end_bairro, end_cidade, "
                + "end_estado) values(?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, obj.getCodigoCelular());
            statement.setInt(2, obj.getFoco().getCodigo());
            statement.setDate(3, new Date(obj.getDataCriacao().getTime()));
            statement.setDate(4, new Date(obj.getDataPrazo().getTime()));
            statement.setString(5, obj.getEndereco().getBairro());
            statement.setString(6, obj.getEndereco().getCidade());
            statement.setString(7, obj.getEndereco().getEstado());

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PrevencaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Salvar: " + obj.toString());
    }

    @Override
    public void atualizar(Prevencao obj) {
        String sql = "update PREVENCAO set dat_prazo = ?, dat_efetuada = ?,"
                + "end_bairro = ?, end_cidade = ?, "
                + "end_estado = ? "
                + "where cod_celular = ? and cod_foco = ? and dat_efetuada is null";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setDate(1, new Date(obj.getDataPrazo().getTime()));
            statement.setDate(2, obj.getDataEfetuada() != null ? new Date(obj.getDataEfetuada().getTime()) : null);
            statement.setString(3, obj.getEndereco().getBairro());
            statement.setString(4, obj.getEndereco().getCidade());
            statement.setString(5, obj.getEndereco().getEstado());
            statement.setInt(6, obj.getCodigoCelular());
            statement.setInt(7, obj.getFoco().getCodigo());

            int linhasAfetadas = statement.executeUpdate();
            System.out.println("Atualizar: " + obj.toString() + "linhasAfetadas: " + linhasAfetadas);
        } catch (SQLException ex) {
            Logger.getLogger(PrevencaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void excluir(int idCelular, int idFoco) {
        System.out.println("Excluir: " + idCelular + ", " + idFoco);
    }

    @Override
    public Prevencao encontrar(int idCelular, int idFoco) {

        String sql = "select * from PREVENCAO p "
                + "inner join FOCO f on (f.cod_foco = p.cod_foco)"
                + "where p.cod_celular = ? and p.cod_foco = ? and p.dat_efetuada is null";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idCelular);
            statement.setInt(2, idFoco);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                String bairro = result.getString("end_bairro");
                String cidade = result.getString("end_cidade");
                String estado = result.getString("end_estado");

                Endereco endereco = new Endereco(bairro, cidade, estado);

                Integer codigoFoco = result.getInt("cod_foco");
                String nome = result.getString("nom_foco");
                String comoLimpar = result.getString("com_limpar");

                Foco foco = new Foco(codigoFoco, nome, comoLimpar);

                Integer codigoCelular = result.getInt("cod_celular");
                Date dataCriacao = result.getDate("dat_criacao");
                Date dataPrazo = result.getDate("dat_prazo");
                Date dataEfetuada = result.getDate("dat_efetuada");

                Prevencao prevencao = new Prevencao(codigoCelular, foco, dataCriacao, dataPrazo, endereco);
                prevencao.setDataEfetuada(dataEfetuada);

                return prevencao;

            }
        } catch (SQLException ex) {
            Logger.getLogger(PrevencaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Encontrar: " + idCelular + ", " + idFoco);
        return null;
    }

    @Override
    public List<Prevencao> todos() {
        List<Prevencao> prevencoes = new ArrayList<>();

        String sql = "select * from PREVENCAO p "
                + "inner join FOCO f on (f.cod_foco = p.cod_foco)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                String bairro = result.getString("end_bairro");
                String cidade = result.getString("end_cidade");
                String estado = result.getString("end_estado");

                Endereco endereco = new Endereco(bairro, cidade, estado);

                Integer codigoFoco = result.getInt("cod_foco");
                String nome = result.getString("nom_foco");
                String comoLimpar = result.getString("com_limpar");

                Foco foco = new Foco(codigoFoco, nome, comoLimpar);

                Integer codigoCelular = result.getInt("cod_celular");
                Date dataCriacao = result.getDate("dat_criacao");
                Date dataPrazo = result.getDate("dat_prazo");
                Date dataEfetuada = result.getDate("dat_efetuada");

                Prevencao prevencao = new Prevencao(codigoCelular, foco, dataCriacao, dataPrazo, endereco);
                prevencao.setDataEfetuada(dataEfetuada);

                prevencoes.add(prevencao);
            }

            System.out.println("Listar todos. Tamanho: " + prevencoes.size());

            return prevencoes;

        } catch (SQLException ex) {
            Logger.getLogger(PrevencaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public MatrizPrenvencao matrizFocos() {
        MatrizPrenvencao serie = new MatrizPrenvencao();
        List<Prevencao> prevencoes = null;
        
        String sql = "select * from PREVENCAO p "
                + "inner join FOCO f on (f.cod_foco = p.cod_foco)"
                + "where p.cod_foco = ?";
        
        PreparedStatement statement = null;
        ResultSet result = null;
        
        for (Foco focoAux : focoDao.todosFocos()) {
            try {
                statement = connection.prepareStatement(sql);
                statement.setInt(1, focoAux.getCodigo());
                result = statement.executeQuery();
                
                prevencoes = new ArrayList<>();
                
                while (result.next()) {
                    
                    String bairro = result.getString("end_bairro");
                    String cidade = result.getString("end_cidade");
                    String estado = result.getString("end_estado");

                    Endereco endereco = new Endereco(bairro, cidade, estado);

                    Integer codigoFoco = result.getInt("cod_foco");
                    String nome = result.getString("nom_foco");
                    String comoLimpar = result.getString("com_limpar");

                    Foco foco = new Foco(codigoFoco, nome, comoLimpar);

                    Integer codigoCelular = result.getInt("cod_celular");
                    Date dataCriacao = result.getDate("dat_criacao");
                    Date dataPrazo = result.getDate("dat_prazo");
                    Date dataEfetuada = result.getDate("dat_efetuada");

                    Prevencao prevencao = new Prevencao(codigoCelular, foco, dataCriacao, dataPrazo, endereco);
                    prevencao.setDataEfetuada(dataEfetuada);

                    prevencoes.add(prevencao);
                }
                
                if (!prevencoes.isEmpty())
                    serie.add(prevencoes);

            } catch (SQLException ex) {
                Logger.getLogger(PrevencaoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.println("Matriz prevenções por foco: " + serie.tamanho());
        
        return serie;

    }


}
