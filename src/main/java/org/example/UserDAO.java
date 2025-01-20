package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

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

    public void merge(User user){

        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();

    }

    public void excluir(int id){

        User user = buscar(id);

        if (user != null){
            em.remove(user);
        }

    }

    public User buscar(int id){
        User user = em.find(User.class,id);
        return user;
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


}
