import java.util.ArrayList;
import java.util.List;

public class Heroe extends Person{
    private int gold;
    private final Bag bag;
    private boolean dead;

    public Heroe(String name, String sex) {
        super(sex);
        setName(name);
        gold = 10;
        bag = new Bag();
        dead = false;
        bag.putInBag(new Item("15"));
        bag.putInBag(new Item("8"));
        recalculateStats(0);
    }

    class Bag {
        private final List<Item> items = new ArrayList<>();

        public List<Item> getItems() { return new ArrayList<>(items); }

        public void viewItems() {
            System.out.println("\tЗолотишко:\t" + gold);
            System.out.println("\tВ пакете:");
            if (items.isEmpty()) System.out.println("песок, грязь, старый счёт за электричество..." +
                    "\nНичего дельного тут нет");
            for (int i = 0; i < items.size(); i++) System.out.println("\t" + (i+1) + "\t" + items.get(i));
        }

        public boolean putInBag(Item item) {
            if (bag.items.size() == 4) {
                System.out.println("\tВ моём пакете нет больше места. Надо что-то продать или выбросить.");
                return false;
            } else if (!item.getItemName().equals("NullItem")){
                items.add(item);
                return true;
            } else return true;
        }

        public boolean buyItem(Item item) {
            if (gold < item.getPrice()) {
                System.out.println("\t\tВы: Не могу себе это позволить, не достаточно средств.\n" +
                        "\t\tНадо либо заработать больше, либо выбрать что-то подешевле");
                return false;
            } else if (putInBag(item)) {
                gold -= item.getPrice();
                return true;
            } else return false;
        }

        public void sellItem(int n) {
            gold += bag.items.get(n).getPrice() / 2;
            bag.items.remove(n);
        }

        public void dressOn(int n) {
            if (bag.items.get(n).getItemName().startsWith("Hammer")) {
                if (!getEquipped(0).getItemName().equals("NullItem")
                        && !getEquipped(2).getItemName().equals("NullItem")
                        && bag.items.size() == 4) {
                    System.out.println("Вы: Чтобы сменить " +
                            getEquipped(0).getItemName() + " на двуручный " + bag.items.get(n).getItemName() +
                            ", придётся сложить и " + getEquipped(0).getItemName() +
                            " и " + getEquipped(2).getItemName() +
                            ", а на это у меня в пакете не хватит места.");
                } else {
                    Item item1 = getEquipped(0);
                    Item item2 = getEquipped(2);
                    setEquipped(0, bag.items.get(n));
                    setEquipped(2, new Item());
                    bag.items.remove(n);
                    putInBag(item1);
                    putInBag(item2);
                    recalculateStats(0);
                }
            } else {
                int slot = 0;
                if (bag.items.get(n).getType().equals("Броня")) slot = 1;
                else if (bag.items.get(n).getType().equals("Щит")) slot = 2;
                Item item = getEquipped(slot);
                setEquipped(slot, bag.items.get(n));
                bag.items.remove(n);
                putInBag(item);
                recalculateStats(0);
            }
        }

        public void dressOut(int n) {
            if (n == 2
                    && getEquipped(n).getItemName().equals("NullItem")
                    && getEquipped(0).getItemName().equals("Hammer")) {
                n = 0;
            }
//            if (getEquipped(n).getItemName().equals("NullItem")) {
//                System.out.println("Вы: Тут нечего снимать");
//                return;
//            }
            if (putInBag(getEquipped(n))) {
                setEquipped(n, new Item());
                recalculateStats(0);
            }
        }
    }

    public Bag getBag() { return bag; }
    public int getGold() { return gold; }
    public boolean isDead() { return dead; }
    public void setGold(int gold) { this.gold = gold; }
    public void setDead(boolean dead) { this.dead = dead; }
}