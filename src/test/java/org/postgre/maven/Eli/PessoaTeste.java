package org.postgre.maven.Eli;

public class PessoaTeste {

	public static void main(String[] args) throws Exception {
		Teste pessoa = new Teste ();
		pessoa.setNome ("Carlos");
		//pessoa.setIdade("35");
		
		
		DaoTeste dao = new DaoTeste();
		System.out.println("Salvando a pessoa: "+pessoa.getNome());
		pessoa = dao.salvar(pessoa);

	}

}
