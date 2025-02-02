import java.util.*;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите задачу: 1 - Управление пользователями, 2 - Очередь запросов, 0 - Выход");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                continue;
            }
            if (choice == 1) {
                runTask1();
            } else if (choice == 2) {
                runTask2();
            } else if (choice == 0) {
                break;
            }
        }
    }
    // 1
    public static void runTask1() {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> users = new HashMap<>();
        while (true) {
            System.out.println("Меню:\n1. Добавить нового пользователя\n2. Удалить пользователя\n3. Проверить существование пользователя\n4. Изменить логин\n5. Изменить пароль\n0. Выход");
            int option;
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                continue;
            }
            if (option == 1) {
                System.out.println("Введите логин:");
                String login = scanner.nextLine();
                if (users.containsKey(login)) {
                    System.out.println("Пользователь уже существует");
                } else {
                    System.out.println("Введите пароль:");
                    String password = scanner.nextLine();
                    users.put(login, password);
                    System.out.println("Пользователь добавлен!\nPowered by ak1ne");
                }
            } else if (option == 2) {
                System.out.println("Введите логин:");
                String login = scanner.nextLine();
                if (users.containsKey(login)) {
                    users.remove(login);
                    System.out.println("Пользователь удален\nPowered by ak1ne");
                } else {
                    System.out.println("Пользователь не найден");
                }
            } else if (option == 3) {
                System.out.println("Введите логин:");
                String login = scanner.nextLine();
                if (users.containsKey(login)) {
                    System.out.println("Пользователь найден!");
                } else {
                    System.out.println("Пользователь не существует");
                }
            } else if (option == 4) {
                System.out.println("Введите текущий логин:");
                String oldLogin = scanner.nextLine();
                if (users.containsKey(oldLogin)) {
                    System.out.println("Введите новый логин:");
                    String newLogin = scanner.nextLine();
                    if (users.containsKey(newLogin)) {
                        System.out.println("Этот логин уже занят!");
                    } else {
                        String pwd = users.get(oldLogin);
                        users.remove(oldLogin);
                        users.put(newLogin, pwd);
                        System.out.println("Логин успешно изменен!\nPowered by ak1ne");
                    }
                } else {
                    System.out.println("Пользователь не найден");
                }
            } else if (option == 5) {
                System.out.println("Введите логин:");
                String login = scanner.nextLine();
                if (users.containsKey(login)) {
                    System.out.println("Введите новый пароль:");
                    String newPassword = scanner.nextLine();
                    users.put(login, newPassword);
                    System.out.println("Пароль успешно изменен!\nPowered by ak1ne");
                } else {
                    System.out.println("Пользователь не найден");
                }
            } else if (option == 0) {
                break;
            }
        }
    }

    // 2
    public static void runTask2() {
        Scanner scanner = new Scanner(System.in);
        PriorityQueue<Client> clientQueue = new PriorityQueue<>();
        Queue<RequestStatistic> statisticsQueue = new LinkedList<>();
        while (true) {
            System.out.println("Меню:\n1. Добавить запрос\n2. Обработать следующий запрос\n3. Показать статистику\n0. Выход");
            int option;
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                continue;
            }
            if (option == 1) {
                System.out.println("Введите имя клиента:");
                String name = scanner.nextLine();
                System.out.println("Введите приоритет (целое число):");
                int priority;
                try {
                    priority = Integer.parseInt(scanner.nextLine());
                } catch (Exception e) {
                    System.out.println("Некорректный приоритет!");
                    continue;
                }
                Client client = new Client(name, priority);
                clientQueue.add(client);
                statisticsQueue.add(new RequestStatistic(name, LocalDateTime.now()));
                System.out.println("Запрос добавлен!\nPowered by ak1ne");
            } else if (option == 2) {
                if (clientQueue.isEmpty()) {
                    System.out.println("Очередь пуста!");
                } else {
                    Client client = clientQueue.poll();
                    System.out.println("Обрабатывается запрос от " + client.name + " с приоритетом " + client.priority + "\nPowered by ak1ne");
                }
            } else if (option == 3) {
                if (statisticsQueue.isEmpty()) {
                    System.out.println("Статистика пуста!");
                } else {
                    for (RequestStatistic rs : statisticsQueue) {
                        System.out.println("Клиент: " + rs.username + ", время: " + rs.time);
                    }
                }
            } else if (option == 0) {
                break;
            }
        }
    }

    static class Client implements Comparable<Client> {
        String name;
        int priority;
        Client(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }
        public int compareTo(Client o) {
            return Integer.compare(o.priority, this.priority);
        }
    }

    static class RequestStatistic {
        String username;
        LocalDateTime time;
        RequestStatistic(String username, LocalDateTime time) {
            this.username = username;
            this.time = time;
        }
    }
}
