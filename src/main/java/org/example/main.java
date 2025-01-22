package org.example;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
        EntityManager em = emf.createEntityManager();
        Scanner sc = new Scanner(System.in);
        int opc = 0;

        UserDAO userDAO = new UserDAO(em);
        User user = new User();


        do {

            if (user.getAdministrador()==true) {

                System.out.println("""
                        \nMenu
                        1-Fazer Login
                        2-Fazer Cadastro
                        3-Visualizar usuários
                        4-Excluir
                        
                        """);
            }else {
                System.out.println("""
                        \nMenu
                        1-Fazer Login
                        2-Fazer Cadastro
                        
                        """);
            }

            opc = sc.nextInt();

            switch (opc) {

                case 1:
                    System.out.println("Login: ");
                    sc.nextLine();
                    String login = sc.nextLine();

                    System.out.println("Senha: ");
                    String senha = sc.nextLine();

                    user = userDAO.Login(login,senha);

                    if(user.getAdministrador() == true){
                        System.out.println("Você é admin");
                    }

                    break;
                case 2:

                    System.out.println("Digite seu nome: ");
                    sc.nextLine();
                    String nome = sc.nextLine();

                    System.out.println("Digite seu e-mail: ");
                    String email = sc.nextLine();

                    System.out.println("Digite seu login: ");
                    login = sc.nextLine();

                    System.out.println("Digite sua senha: ");
                    senha = sc.nextLine();

                    user = new User(0, nome, email, login, senha, false);

                    userDAO.salvar(user);

                    System.out.println("Salvo com sucesso!");

                    break;
                case 3:
                    if (user.getAdministrador()==false){
                        System.out.println("Você não tem permissão para executar este comando!");
                    }else{

                        //FAZER FUNÇÃO LISTAR

                        for(User u: userDAO.listar()){
                            System.out.println("\nID: " + u.getId());
                            System.out.println("Nome: " + u.getNome());
                            System.out.println("E-mail: " + u.getEmail());
                            System.out.println("ADM: " + u.getAdministrador());
                        }

                    }

                    break;

                case 4:
                    if (user.getAdministrador()==false){
                        System.out.println("Você não tem permissão para executar este comando!");
                    }else{

                        for(User u: userDAO.listar()){
                            System.out.println("\nID: " + u.getId());
                            System.out.println("Nome: " + u.getNome());
                            System.out.println("E-mail: " + u.getEmail());
                            System.out.println("ADM: " + u.getAdministrador());
                        }

                        System.out.println("Qual usuário deseja excluir? ");
                        int id = sc.nextInt();

                        userDAO.excluir(id);

                    }


            }


        } while (opc != 0);

    }





}
