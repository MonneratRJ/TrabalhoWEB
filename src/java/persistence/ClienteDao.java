package persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Cliente;

public class ClienteDao extends Dao {
	
	public boolean excluir(Cliente c) throws Exception {
		boolean SUCCESS = false;
		try {
			stmt = con.prepareStatement("delete from cliente where email = (?)");
			stmt.setString(1,c.getEmail());
			
			stmt.execute();
			SUCCESS = true;
			
		}catch(SQLException e){
			System.out.println(e.getErrorCode());
			System.out.println(e.getMessage());
		}finally{
			close();
		}
		
		return SUCCESS;		
	}

	public boolean cadastrar(Cliente c) throws Exception{
		boolean SUCCESS = false;
		try{
			open();
			stmt = con.prepareStatement("insert into cliente values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(1,UtilsBanco.converterDataToBanco(c.getDataCadastro()));
			stmt.setString(2,c.getNome());
			stmt.setString(3,c.getEmail());
			stmt.setString(4,c.getCpf());
			stmt.setString(5,c.getTelefone());
			stmt.setString(6,UtilsBanco.converterDataToBanco(c.getDataNascimento()));
			stmt.setString(7,c.getSexo());
			stmt.setString(8,c.getLogradouro());
			stmt.setInt(9,c.getNumero());
			stmt.setString(10,c.getCep());
			stmt.setString(11,c.getComplemento());
			stmt.setString(12,c.getCidade());
			stmt.setString(13,c.getUf());
			stmt.setString(14,c.getObservacoes());
			
			stmt.execute();
			SUCCESS = true;
			
		}catch(SQLException e){
			System.out.println(e.getErrorCode());
			System.out.println(e.getMessage());
		}finally{
			close();
		}
		
		return SUCCESS;		
	}
	
	/* Consultar o cliente pelo CPF */
	public Cliente buscarPorCpf(String cpf)throws Exception{
		Cliente c = null;
		try{
			open();		
			//Jun�ao das tabelas Usu�rio e Cliente
			stmt = con.prepareStatement("select * from cliente where cpf = ?" );
			stmt.setString(1,cpf);
			rs = stmt.executeQuery();
			if(rs.next()){	
				c = new Cliente();
				c.setNome(rs.getString("nome"));
				c.setEmail(rs.getString("email"));
				c.setCpf(rs.getString("cpf"));
				c.setDataNascimento(UtilsBanco.converterTSBancoToGC(rs.getTimestamp("dtnasc")));
				c.setSexo(rs.getString("sexo"));
				c.setTelefone(rs.getString("telefone"));
				c.setLogradouro(rs.getString("logradouro"));
				c.setNumero(rs.getInt("numero"));
				c.setCidade(rs.getString("cidade"));
				c.setCep(rs.getString("cep"));
				c.setUf(rs.getString("uf"));
				c.setComplemento(rs.getString("complemento"));
				c.setObservacoes(rs.getString("observacoes"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close();
		}		
		return c;
	}
	
	/* Consultar o cliente pelo CPF */
	public List<Cliente> listar()throws Exception{
		List<Cliente> listaClientes = new ArrayList<>();
		try{
			open();		
			//Jun�ao das tabelas Usu�rio e Cliente
			stmt = con.prepareStatement("select * from cliente");
			rs = stmt.executeQuery();
			while(rs.next()){	
				Cliente c = new Cliente();
				c.setNome(rs.getString("nome"));
				c.setEmail(rs.getString("email"));
				c.setCpf(rs.getString("cpf"));
				c.setDataNascimento(UtilsBanco.converterTSBancoToGC(rs.getTimestamp("dtnasc")));
				c.setSexo(rs.getString("sexo"));
				c.setTelefone(rs.getString("telefone"));
				c.setLogradouro(rs.getString("logradouro"));
				c.setNumero(rs.getInt("numero"));
				c.setCidade(rs.getString("cidade"));
				c.setCep(rs.getString("cep"));
				c.setUf(rs.getString("uf"));
				c.setComplemento(rs.getString("complemento"));
				c.setObservacoes(rs.getString("observacoes"));
				listaClientes.add(c);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close();
		}		
		return listaClientes;
	}
}
