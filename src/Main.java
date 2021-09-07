import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Добро пожаловать в Lineage3 !" +
                "\nСоздайте героя:" +
                "\nИмя: ");
        String name = scanner.nextLine();
        System.out.print("Пол: 1 - Женский (быстрее)  |  2 - Мужской (сильнее)  ");
        String sex;
        do {
            sex = scanner.next();
            if(!sex.equals("1") && !sex.equals("2"))
                System.out.println("Трансгендеров тут нет, так что выбирайте из предложенного");
        } while (!sex.equals("1") && !sex.equals("2"));
        Heroe heroe = new Heroe(name, sex);
        System.out.println("\n---------------------------------------------------------------------------------" +
                "\nВы появились прям посреди городской площади." +
                "\nВ трусах, с пустым пакетом и 8ю золотыми (где именно уточнять не будем, но они в сохранности)" +
                "\nВ этом мегополисе есть 1 магазин и всё. Ну и кладбище для таких же героев..." +
                "\nВы вольная птица, вы можете идти куда захотите, вам всё по плечу!" +
                "\nНо если сдохнете, игра закончится. Удачи =)" +
                "\n---------------------------------------------------------------------------------");

        boolean logOff = false;
        do {
            System.out.println("\n1\tМагазин дядюшки Жида" +
                    "\n2\tЗаглянуть в свой пакетик" +
                    "\n3\tПохвастаться статами" +
                    "\n4\tСпуститься в канализацию магазина дядюшки Жида (Lvl 1-3)" +
                    "\n5\tСходить на болото за магазином (Lvl 4-7)" +
                    "\n6\tОтправиться в длёёёёкий лес вот за тем холмом (Lvl 8-10)" +
                    "\n7\tУбиться об стену магазина");
            switch (scanner.next()) {
                case "1":
                    Shop.trader(heroe, scanner);
                    break;
                case "2":
                    heroe.getBag().viewItems();
                    if(!heroe.getBag().getItems().isEmpty()) {
                        do {
                            System.out.println("\t0\t" +
                                    "Свернуть пакетик пока ни кто не спалил богатство");
                            System.out.println("\tЧто бы из этого надеть?");
                            int answer;
                            try {
                                answer = Integer.parseInt(scanner.next());
                                if (answer > 0 && answer <= heroe.getBag().getItems().size()) {
                                    Item item = heroe.getBag().getItems().get(answer-1);
                                    heroe.getBag().dressOn(answer - 1);
                                    System.out.println("\t>>>Надето: " + item + "\n");
                                    if(heroe.getBag().getItems().isEmpty()) break;
                                } else if (answer == 0) {
                                    break;
                                } else throw new NotCorrectAnswer();
                            } catch (NotCorrectAnswer e) {
                                System.out.println(e);
                            } catch (NumberFormatException e) {
                                System.out.println("Это не число. Ещё разок");
                            } finally {
                                heroe.getBag().viewItems();
                            }
                        } while (true);
                    }
                    break;
                case "3":
                    int answer;
                    do {
                        try {
                            int count = heroe.viewFullInf();
                            if (count == 0) {
                                System.out.println("Ничего не надето");
                                break;
                            } else {
                                System.out.println("Снять какой-то предмет?" +
                                        "\n0\tНичего не менять");
                            }
                            answer = Integer.parseInt(scanner.next());
                            if (answer > 0 && answer <= count) {
                                System.out.println(">>>Снят предмет:" + heroe.getEquipped(answer-1) + "\n");
                                heroe.getBag().dressOut(answer - 1);
                            } else if (answer == 0) break;
                            else throw new NotCorrectAnswer();
                        } catch (NotCorrectAnswer e) {
                            System.out.println(e);
                        } catch (NumberFormatException e) {
                            System.out.println("Это не число. Ещё разок");
                        }
                    } while (true);
                    break;
                case "4":
                    Thread thread1 = new Thread(new Fight(heroe, 4));
                    thread1.start();
                    try {
                        thread1.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case "5":
                    Thread thread2 = new Thread(new Fight(heroe, 5));
                    thread2.start();
                    try {
                        thread2.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case "6":
                    Thread thread3 = new Thread(new Fight(heroe, 6));
                    thread3.start();
                    try {
                        thread3.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case "7":
                    logOff = true;
                    break;
                default:
            }
        } while (!heroe.isDead() && !logOff);
    }
}
