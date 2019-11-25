package control;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Cliente;
import persistence.ClienteDao;
import persistence.UtilsBanco;

/**
 * Servlet implementation class ClienteControle
 */
@WebServlet({"/ClienteControle", "/cadcliente", 
	"/conscliente", "/altcliente","/excliente","/listarclientes"})
public class ClienteControle extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ClienteControle() {
        super();   
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	execute(request, response);
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}
    
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getServletPath();
		System.out.println(url);
		if(url.equals("/cadcliente")){
			cadastrar(request,response);
		}else if(url.equalsIgnoreCase("/conscliente")){
			consultar(request, response);
		}else if(url.equalsIgnoreCase("/listarclientes")){
			listar(request, response);
		} else if(url.equalsIgnoreCase("/excluircliente")){
			excluir(request, response);
		}
	}
    protected void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try{
    		String email = request.getParameter("email");
    		Cliente c = new Cliente();
    		c.setEmail(email);
    		System.out.println(c);
		
			if(new ClienteDao().excluir(c)){
				request.setAttribute("msg", 
				"<div class='alert alert-success'>Cliente excluído com sucesso</div>");
			}else{
				request.setAttribute("msg", 
				"<div class='alert alert-danger'>Cliente não existe</div>");
			}
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("msg", 
					"<div class='alert alert-danger'>Funcionario não cadastrado</div>");
		}finally{
			request.getRequestDispatcher("listarClientes.jsp").forward(request, response);
		}
    }
    
    protected void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try{
    		String nome = request.getParameter("nome");
    		String email = request.getParameter("email");
    		String cpf = request.getParameter("cpf");
    		String telefone = request.getParameter("tel");
    		String dataNascimento = request.getParameter("dtnasc");
    		String sexo = request.getParameter("rbsexo");
    		String logradouro = request.getParameter("logradouro");
    		String numero = request.getParameter("numero");
    		String cep = request.getParameter("cep");
    		String complemento = request.getParameter("complemento");
    		String cidade = request.getParameter("cidade");
    		String uf = request.getParameter("cbouf");
    		String observacoes = request.getParameter("obs");
    		Cliente c = new Cliente();
    		c.setDataCadastro(new GregorianCalendar());
    		c.setNome(nome);
    		c.setEmail(email);
    		c.setCpf(cpf);
    		c.setTelefone(telefone);
    		c.setDataNascimento(UtilsBanco.converterData(dataNascimento));
    		c.setSexo(sexo);
    		c.setLogradouro(logradouro);
    		c.setNumero(new Integer(numero));
    		c.setCep(cep);
    		c.setComplemento(complemento);
    		c.setCidade(cidade);
    		c.setUf(uf);
    		c.setObservacoes(observacoes);
    		System.out.println(c);
		
			if(new ClienteDao().cadastrar(c)){
				request.setAttribute("msg", 
				"<div class='alert alert-success'>Cliente cadastrado com sucesso</div>");
			}else{
				request.setAttribute("msg", 
				"<div class='alert alert-danger'>Cliente j� possui possui um cadastro no sistema</div>");
			}
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("msg", 
					"<div class='alert alert-danger'>Funcionario n�o cadastrado</div>");
		}finally{
			request.getRequestDispatcher("cadastrarCliente.jsp").forward(request, response);
		}
    }
    
    protected void consultar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try{
    		/* Pegar o cpf digitado na tela */			
    		String cpf = request.getParameter("cpf");
    		/* Instanciar a classe DaoCliente */
    		ClienteDao cd = new ClienteDao();
    		/* Acionar o m�todo da classe Dao Cliente que retorna os dados de um cliente a 
    		 * partir do CPF do cliente*/
    		Cliente cliente = cd.buscarPorCpf(cpf);
    	
    		if(cliente == null){ //Cliente n�o encontrado
    			request.setAttribute("msg", "<div class='alert alert-danger'>Cliente n�o encontrado</div>");
    			request.getRequestDispatcher("consultarCliente.jsp").forward(request, response);
    		}else{
    			request.setAttribute("c", cliente);
    			request.setAttribute("op", "C"); //opera��o = consulta
    			request.getRequestDispatcher("cadastrarCliente.jsp").forward(request, response);
    		}
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    protected void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try{
    		/* Instanciar a classe DaoCliente */
    		ClienteDao cd = new ClienteDao();
    		/* Acionar o m�todo da classe Dao Cliente que retorna os dados de um cliente a 
    		 * partir do CPF do cliente*/
    		List<Cliente> listaClientes = cd.listar();
    	
    		if(listaClientes.size()==0){ // Nenhum cliente encontrado
    			request.setAttribute("msg", "<div class='alert alert-danger'>Nenhum cliente encontrado</div>");
    			request.getRequestDispatcher("listarClientes.jsp").forward(request, response);
    		}else{
    			request.setAttribute("lc",listaClientes);
    			request.getRequestDispatcher("listarClientes.jsp").forward(request, response);
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
}
