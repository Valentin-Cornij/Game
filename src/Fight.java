import java.util.Random;
import java.util.Scanner;

public class Fight implements Runnable {
    Heroe heroe;
    Monster monster;
    int sequence;
    Random r = new Random();
    private boolean question;
    private String answer;

    public Fight(Heroe heroe, int place) {
        this.heroe = heroe;
        monster = new Monster(String.valueOf(r.nextInt(2)+1), place);
        sequence = (int)(heroe.getDex() + monster.getDex());
        question = true;
    }

    @Override
    public void run() {
        System.out.print(heroe.getName() +
                "\nLvl:\t" + heroe.getLvl() +
                "\nStr:\t" + heroe.getStr() +
                "\nDex:\t" + Math.round(heroe.getDex()) +
                "\nDef:\t" + heroe.getDef());
        System.out.println("\n  *  *  *");
        System.out.println(monster.getName() + "\t" +
                "\nLvl:\t" + monster.getLvl() +
                "\nStr:\t" + monster.getStr() +
                "\nDex:\t" + Math.round(monster.getDex()) +
                "\nDef:\t" + monster.getDef());
        do {
            int i = r.nextInt(sequence)+1;
            if (i <= heroe.getDex()) hit(heroe, monster);
            else hit(monster, heroe);
            if (heroe.getMaxHp() / 5 > heroe.getHp() && question) {
                System.out.println("\nУ " + heroe.getName() + " осталось менее 20% HP!!!\n" +
                        "1\tВытереть кровь с глаз, выплюнуть зуб на землю и заорать: \"Ну давай, ДО СМЕРТИ!\"\n" +
                        "2\tЗавизжав как школьница, бегажать что есть духу, давясь слезами позора...");
                do {
                    answer = new Scanner(System.in).next();
                    if (answer.equals("2")) {
                        System.out.println("спущенно в штаны " + monster.getLvl() + " опыта");
                        heroe.setExp(heroe.getExp() - monster.getLvl());
                        if (heroe.getExp() < 0) heroe.setExp(0);
                        heroe.recalculateStats(0);
                        return;
                    } else if (answer.equals("1")) question = false;
                    else System.out.println("Нет такого варианта. Попробуй ещё раз");
                } while (!answer.equals("1") && !answer.equals("2"));
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (heroe.getHp() > 0 && monster.getHp() > 0);
        System.out.println("   *  *  *");
        if (heroe.getHp() <= 0) {
            System.out.println(heroe.getName() + " сдох");
            heroe.setDead(true);
        }
        else {
            System.out.println(monster.getName() + " сдох");
            System.out.println("Получен опыт " + (monster.getLvl() * 10));
            heroe.recalculateStats(monster.getLvl() * 10);
            System.out.println("Вы обшарили карманы " + monster.getName() +
                    " и нашли там " + (monster.getLvl() * 2) + " золота");
            heroe.setGold(heroe.getGold() + monster.getLvl() * 2);
        }
    }

    private void hit(Person p1, Person p2) {
        System.out.println("----------------------------------------------");
        if (r.nextInt(99)+1 <= p2.getDex()) {
            System.out.println(p2.getName() + " уворачивается от удара " + p1.getName());
        } else if (r.nextInt(99)+1 <= p1.getDex()) {
            double demage = (p1.getStr() * (p1.getDex() / 20)) * ((100.0 - p2.getEquipped(2).getDef()) / 100);
            p2.setHp(p2.getHp() - demage);
            System.out.printf(p1.getName() + " наносит по " + p2.getName() +
                    " критический урон (%.2f)\n", demage);
        } else {
            p2.setHp(p2.getHp() -
                    p1.getStr() * ((100.0 - p2.getEquipped(2).getDef()) / 100)
            );
            System.out.println(p1.getName() + " наносит по " + p2.getName() +
                    " урон (" + p1.getStr() + ")");
        }
    }
}
