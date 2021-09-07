import java.util.Random;

public class Monster extends Person {

    Random r = new Random();
    double bonusExp;

    public Monster(String sex, int place) {
        super(sex);
        if (place == 4) {
            bonusExp = r.nextInt(88) + 1;
            setEquipped(0, new Item());
            setEquipped(1, new Item());
            setEquipped(2, new Item());
            if (sex.equals("1")) setName("Крыса самка");
            else setName("Крыса самец");

        } else if (place == 5) {
            bonusExp = r.nextInt(1200) + 91;
            setEquipped(0, new Item(String.valueOf(r.nextInt(3) + 4)));
            if (getEquipped(0).getItemName().startsWith("Dagger")) {
                setEquipped(1, new Item("10"));
                setEquipped(2, new Item());
                setName("Болотная флешовидная кикимора");
            } else {
                setEquipped(1, new Item());
                if (getEquipped(0).getItemName().startsWith("Hammer")) {
                    setEquipped(2, new Item());
                    setName("Болотная халковидная кикимора");
                } else {
                    setEquipped(2, new Item("16"));
                    setName("Болотная тонистарковидная кикимора");
                }
            }
        } else {
            bonusExp = r.nextInt(2140) + 2370;
            if (getSex().equals("1")) {
                setEquipped(0, new Item("7"));
                setEquipped(1, new Item("12"));
                setEquipped(2, new Item());
                setName("Асcасин");
            } else {
                setEquipped(0, new Item(String.valueOf(r.nextInt(2) + 8)));
                setEquipped(1, new Item("13"));
                if (getEquipped(0).getItemName().startsWith("Hammer")) {
                    setEquipped(2, new Item());
                    setName("Воин");
                } else {
                    setEquipped(2, new Item(String.valueOf(r.nextInt(2) + 17)));
                    setName("Танк");
                }
            }
        }
        recalculateStats(bonusExp);
    }
}
