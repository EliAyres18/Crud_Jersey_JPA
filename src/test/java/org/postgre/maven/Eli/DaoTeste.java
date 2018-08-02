package org.postgre.maven.Eli;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import antlr.collections.List;

public class DaoTeste {
	
	private static DaoTeste instance;
	protected EntityManager entityManager;
	
	public static DaoTeste getInstance(){
		if (instance == null){
			instance =new DaoTeste(); // criando uma instância de EntityManager
		}
		
		return instance;
	}

	DaoTeste(){
		entityManager = getEntityManager();
	}
	
	EntityManager getEntityManager(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHiernatePU");// usa as configurações presentes no arq persistence.xml para criar um instancia em EntityManagerFactory
				if (entityManager == null){ // verifica se nunca foi criado
					entityManager=factory.createEntityManager();// criar uma instancia de EntityManager que é responsavél por realizar as oper de CRUD no banco
				}
		return entityManager; // objeto de gerenciamento e tb objeto principal do CRUD
	}
	
	public Teste getById (final int id){ //aqui passa o tipo da classe o id q deve ser buscado
		return entityManager.find(Teste.class,id);// traz o objeto "Teste" (classe)
	}
	
	@SuppressWarnings("unchecked")//essa anotação impede o compilador de disparar exceções de um determinado tipo, e ainda, é limpar as mensagens de compilação, de forma que outros programadores possam se preocupar apenas com warnings reais 
	public List findAll(){ // faz com q as consultas no sejam feitas como se ele fosse orientado a objetos
		return (List) entityManager.createQuery("FROM" + Teste.class.getCanonicalName());
	}
	
	public void merge (Teste teste){ //merge é para dar um update, ou juntar, ou seja, se o registro não existe no BD ele vai inserir
		try {
			entityManager.getTransaction().begin();//inicia a transação
			entityManager.persist(teste);//salva o objeto
			entityManager.getTransaction().commit();//finaliza a transação
			
		} catch (Exception ex) {
			ex.printStackTrace();// faz uma pilha com os erros informados
			entityManager.getTransaction().rollback();// limpa no projeto, fazendo com que todos os POMs volte ao estado do pré-release
			
		}
	}
	
	public Teste salvar (Teste teste) throws Exception {
		EntityManager entityManager=getEntityManager();
		try {
			entityManager.getTransaction().begin();
			System.out.println("Salvando a pessoa.");
		}
			
		/*	if(teste.getId()==null){
				entityManager.persist(teste);
		} else{
			teste = entityManager.merge(teste);
		}
		entityManager.getTransaction().commit();
				
		}*/finally {
			entityManager.close();
		
		}
		
		return teste;
	}
	
	
	public void remove(Teste teste){
		try {
			entityManager.getTransaction().begin();
			teste= entityManager.find(Teste.class, teste.getId());// recebe o obejto que sera removido
			entityManager.remove(teste);
			entityManager.getTransaction().commit();
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();// limpa e faz com q todas as alterações são desfeitas
		}
	}
	
	
	public void removeById (final int id){
		try {
			Teste teste = getById(id);// recebe direto o id que será removido
			remove(teste);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
