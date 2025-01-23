package org.example;

import javax.persistence.*;
import java.util.List;

public class UserDAO {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
    EntityManager em = emf.createEntityManager();

    public UserDAO(EntityManager em) {
        this.em = em;
    }


    //CRUD


    public void salvar(User user){

        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();

    }

    public void editar(int id,String nome, String email, String login, String senha){

        User user = em.find(User.class, id);

        em.getTransaction().begin();

        user.setNome(nome);
        user.setEmail(email);
        user.setLogin(login);
        user.setSenha(senha);

        em.getTransaction().commit();

    }

    public User buscar(int id){
        User user = em.find(User.class,id);
        return user;
    }

    public void excluir(int id){

        User user = em.find(User.class,id);

        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();

    }




    public User Login(String login, String senha){
        try {
            String query = "SELECT u FROM User u WHERE u.login = :login AND u.senha = :senha";

            User user = em.createQuery(query, User.class)
                    .setParameter("login", login)
                    .setParameter("senha", senha)
                    .getSingleResult();

            System.out.println("\nLogin feito com sucesso!");
            System.out.println("\nDados");
            System.out.println("Nome: " + user.getNome());
            System.out.println("Email: " + user.getEmail());

            return user;
        } catch (NoResultException e) {
            System.out.println("\nUsuário não localizado");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<User> listar(){

        List<User> users = null;

        try{

            users = em.createQuery("from User u").getResultList();

        }catch(Exception e){
            e.printStackTrace();
        }

        return users;

    }


}
