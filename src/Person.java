public abstract class Person {

    private String name = "";
    private String sex;
    private double exp;
    private int lvl;
    private double hp;
    private double maxHp;
    private double str;
    private double dex;
    private int def;
    private Item[] equipped;

    public Person(String sex) {
        this.sex = sex;
        exp = 0;
        equipped = new Item[3];
        equipped[0] = new Item();
        equipped[1] = new Item();
        equipped[2] = new Item();
    }

    public void recalculateStats(double bonusExp) {
        exp += bonusExp;
        lvl = 1;
        if (sex.equals("1")) {
            hp = maxHp = 100;
            str = 5;
            dex = 30;
        } else {
            hp = maxHp = 125;
            str = 8;
            dex = 20;
        }
        def = 0;
        checkLvlUp(0);
        str += (equipped[0].getStr());
        dex += (equipped[0].getDex() + equipped[1].getDex() + equipped[2].getDex());
        def += (equipped[1].getDef() + equipped[2].getDef());
    }

    public void checkLvlUp(double step) {
        double stepNew = (step + (20 * lvl)) * 1.35;
        if (exp > stepNew) {
            lvl++;
            if (sex.equals("1")) {
                hp = maxHp *= 1.3;
                str += 4;
                dex += 2.8;
            } else {
                hp = maxHp *= 1.3;
                str += 5;
                dex += 1.8;
            }
            if (lvl == 10) return;
        } else return;
        checkLvlUp(stepNew);
    }

    public int viewFullInf() {
        System.out.println(name + " (" + (sex.equals("1") ? "Woman)" : "Man)") +
                "\n-------------\n" + "STATS\n" +
                "lvl: " + lvl + "\n" +
                "hp: " + (int)hp + "\n" +
                "str: " + (int)str + "\n" +
                "dex: " + (int)dex + "\n" +
                "def: " + def + "\n" +
                "-------------\n" + "EQUIPPED" );
        int count = 0;
        for(int i = 0; i < equipped.length; i++) {
            if(!equipped[i].getItemName().equals("NullItem")) {
                count++;
                System.out.println((i+1) + "\t" + equipped[i]);
            }
        }
        return count;
    }

    public String getName() { return name; }
    public String getSex() { return sex; }
    public double getExp() { return exp; }
    public double getHp() { return hp; }
    public double getMaxHp() { return maxHp; }
    public double getStr() { return str; }
    public double getDex() { return dex; }
    public int getDef() { return def; }
    public Item getEquipped(int slot) { return equipped[slot]; }
    public int getLvl() { return lvl; }

    public void setName(String name) { this.name = name; }
    public void setHp(double hp) { this.hp = hp; }
    public void setExp(double exp) { this.exp = exp; }
    public void setEquipped(int slot, Item item) { equipped[slot] = item; }
}