package org.example;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
        EntityManager em = emf.createEntityManager();
        Scanner sc = new Scanner(System.in);
        int opc = 0;

        UserDAO userDAO = new UserDAO(em);
        User user = null;
        AnimalDAO animalDAO = new AnimalDAO(em);
        Animal animal = null;

        do {

            if(user==null) {

                System.out.println("""
                        \nMenu
                        1-Fazer Login
                        2-Fazer Cadastro
                        0-Sair
                        """);
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

                }

            }else {

                if (user.getAdministrador() == true) {

                    System.out.println("""
                            \nPortal Admin
                            1-Editar Cadastro
                            2-Cadastrar animal
                            3-Visualizar meus animais
                            4-Visualizar usuários
                            5-Excluir usuário
                            9-Voltar
                            
                            """);



                } else {
                    System.out.println("""
                            \nPortal Usuário
                            1-Editar Cadastro
                            2-Cadastrar animal
                            3-Visualizar meus animais
                            9-Voltar
                            
                            """);
                }

                opc=sc.nextInt();

                switch (opc){
                    case 1:
                        //editar

                        System.out.println("Digite seu nome: ");
                        sc.nextLine();
                        String nome = sc.nextLine();

                        System.out.println("Digite seu e-mail: ");
                        String email = sc.nextLine();

                        System.out.println("Digite seu login: ");
                        String login = sc.nextLine();

                        System.out.println("Digite sua senha: ");
                        String senha = sc.nextLine();



                        userDAO.editar(user.getId(), nome, email, login, senha);

                        System.out.println("Salvo com sucesso!");

                        break;

                    case 2:
                        //cadastrar animal

                        System.out.println("Digite o nome do animal: ");
                        sc.nextLine();
                        String nomeAnimal = sc.nextLine();

                        System.out.println("Digite a espécie do animal: ");
                        String especie = sc.nextLine();

                        System.out.println("Digite a idade do animal: ");
                        int idadeAnimal = sc.nextInt();

                        animal = new Animal(0, user.getId(), nomeAnimal, especie, idadeAnimal);

                        animalDAO.salvarAnimal(animal);

                        System.out.println("Salvo!");


                        break;
                    case 3:
                        //visualizar animais deste usuário

                        int id_usuario = user.getId();

                        System.out.println("\nSeus animais");

                        for(Animal a: animalDAO.listarAnimalUsuario(id_usuario)) {
                            System.out.println("\nNome: " + a.getNome());
                            System.out.println("Especie: " + a.getEspecie());
                            System.out.println("Idade: " + a.getIdade());
                        }

                        break;
                    case 4:
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

                    case 5:
                        if (user.getAdministrador()==false){
                            System.out.println("Você não tem permissão para executar este comando!");
                        }else{

                            for(User u: userDAO.listar()){
                                System.out.println("\nID: " + u.getId());
                                System.out.println("Nome: " + u.getNome());
                                System.out.println("E-mail: " + u.getEmail());
                                System.out.println("ADM: " + u.getAdministrador());
                            }

                            System.out.println("\nQual usuário deseja excluir? ");
                            int id = sc.nextInt();

                            userDAO.excluir(id);

                        }
                        break;
                    case 9:
                        user = null;
                        break;

                }

            }











        } while (opc != 0);

    }





}
