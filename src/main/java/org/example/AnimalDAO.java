package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.List;

public class AnimalDAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
    EntityManager em = emf.createEntityManager();

    public AnimalDAO(EntityManager em) {
        this.em = em;
    }

    public void salvarAnimal(Animal a){
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
    }

    public void editarAnimal(int id, String nome, String especie, int idade){
        Animal a = em.find(Animal.class, id);

        em.getTransaction().begin();
        a.setNome(nome);
        a.setEspecie(especie);
        a.setIdade(idade);
        em.getTransaction().commit();

    }

    public Animal buscarAnimal(int id){
        Animal a = em.find(Animal.class, id);
        return a;
    }

    public void excluirAnimal(int id){
        Animal a = em.find(Animal.class, id);

        em.getTransaction().begin();
        em.remove(a);
        em.getTransaction().commit();
    }

    public List<Animal> listarAnimalUsuario(int idUsuario){
        try {

            List<Animal> animais = null;

            String query = "FROM Animal a WHERE a.id_usuario = :id_usuario";

            animais = em.createQuery(query)
                    .setParameter("id_usuario", idUsuario)
                    .getResultList();

            return animais;

        } catch (NoResultException e) {
            System.out.println("\nAnimais não localizado");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
