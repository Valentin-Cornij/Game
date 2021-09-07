import java.util.Scanner;

class Shop {
    private static String answer;

    public static void trader(Heroe heroe, Scanner scan) {
        System.out.println("\tТорговец Жид: Добро пожаловать в лавочку Жида!\n" +
                "\tЧем могу тебя заинтересовать?" );
        text(scan);
        do {
            switch (answer) {
                case "1" :
                    traderSell(heroe, scan);
                    break;
                case "2" :
                    traderBuy(heroe, scan);
                    break;
                case "3" :
                    break;
                default:
                    System.out.println("\tТорговец Жид: Фуууу... Извините, но я таких услуг не предоставляю.\n" +
                            "\tБыть может чем-то другим могу помочь?");
                    text(scan);
            }
        } while (!answer.equals("3"));
        System.out.println("\tТорговец Жид: Это в соседней деревне. Надеюсь дойдёшь с таким диагнозом. Удачи.");
    }

    private static void traderSell(Heroe heroe, Scanner scan) {
        for (int i = 1; i <= 18; i++) {
            Item item = new Item(String.valueOf(i));
            System.out.println("\t\t" + i + "\t" + item + "\tЦена: " + item.getPrice());
        }
        System.out.println("\t\t19\tЗаказать коктейль \"Кровавая Мэри\"" +
                " (востонавливает жизни до 100%)\tЦена: 1");
        System.out.println("\n\t\tТорговец Жид: Ну как, приглянулось что-нибудь?\n" +
                "\t\t20\tВы: Похожу ещё посмотрю..." );
        answer = scan.next();
        do {
            try {
                if (Integer.parseInt(answer) > 0 && Integer.parseInt(answer) < 19) {
                    if (heroe.getBag().buyItem(new Item(answer)))
                        System.out.println("\t\tТорговец Жид: Отличный выбор!" +
                                " Спасибо за покупку.\n" +
                                "\t\tЧто ещё желаешь прикупить?");
                    else {
                        text(scan);
                        return;
                    }
                    answer = scan.next();
                } else if (answer.equals("19")) {
                    heroe.setHp(heroe.getMaxHp());
                    heroe.setGold(heroe.getGold()-1);
                    System.out.println("\tТорговец Жид: На здоровье. Ещё чем-то могу помочь?");
                    text(scan);
                    break;
                } else if (answer.equals("20")) {
                    System.out.println("\tТорговец Жид: Ну да, ну да... конечно... Ещё что-то надо?");
                    text(scan);
                    break;
                } else throw new NotCorrectAnswer();
            } catch (NotCorrectAnswer e) {
                System.out.println(e);
                answer = scan.next();
            } catch (NumberFormatException e) {
                System.out.println("Это не число. Ещё разок");
                answer = scan.next();
            }
        } while (true);
    }

    private static void traderBuy(Heroe heroe, Scanner scan) {
        do {
            try {
                if (heroe.getBag().getItems().isEmpty()) {
                    System.out.println("\t\tВы: Ой, а пакетик то пуст... Мне нечего тебе продать.");
                    System.out.println("\t\tТорговец Жид: И чё тебе тогда надо?\n");
                    text(scan);
                    return;
                }
                for (int i = 0; i < heroe.getBag().getItems().size(); i++)
                    System.out.println("\t\t" + (i + 1) + "  " + heroe.getBag().getItems().get(i) +
                            "\tЦена: " + heroe.getBag().getItems().get(i).getPrice() / 2);
                System.out.println("\t\tТорговец Жид: Ну как, будешь что-то продовать?");
                System.out.println("\t\t" + (heroe.getBag().getItems().size() + 1) +
                        "  Да я скорее нубам выброшу, чем тебе по этим ценам продам");
                answer = scan.next();
                if (Integer.parseInt(answer) > 0 && Integer.parseInt(answer) <= heroe.getBag().getItems().size()) {
                    heroe.getBag().sellItem(Integer.parseInt(answer) - 1);
                    System.out.println("\t\tТорговец Жид: Славная сделка. Тебе и мне приятно.\n" +
                            "\t\tЕщё что-нибудь продашь?");
                } else if (Integer.parseInt(answer) == heroe.getBag().getItems().size() + 1) {
                    System.out.println("\tТорговец Жид: Эти стервятники и спасибо не скажут!" +
                            " Давай по делу или проваливай!");
                    text(scan);
                    return;
                } else {
                    System.out.println("\t\tТорговец Жид: Воздух можешь идти в лес тупым оркам продавать, " +
                            "а здесь давай пальцем на вещь тыкай");
                }
            } catch (NumberFormatException e) {
                System.out.println("Это не число. Ещё разок");
            }
        } while (true);
    }

    private static void text(Scanner scan) {
        System.out.println("\t1\tХочу пошопиться!\n" +
                        "\t2\tПришёл сбагрить всякий ржавый хлам.\n" +
                        "\t3\tЯ кабинет проктолога ищу... надо щит и алибарду вытащить.");
        answer = scan.next();
    }
}