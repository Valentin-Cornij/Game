public class Item {

    private String ItemName;
    private double price;
    private String type;
    private double str;
    private double dex;
    private int def;

    public Item(String num) {
        switch (num) {
            case "1":
                ItemName = "Dagger C-grade";
                price = 12;
                type = "Оружие";
                str = 12;
                dex = 4;
                break;
            case "2":
                ItemName = "Sword C-grade";
                price = 12;
                type = "Оружие";
                str = 16;
                dex = 0;
                break;
            case "3":
                ItemName = "Hammer C-grade";
                price = 12;
                type = "Оружие";
                str = 22;
                dex = -2;
                break;
            case "4":
                ItemName = "Dagger B-grade";
                price = 24;
                type = "Оружие";
                str = 18;
                dex = 6;
                break;
            case "5":
                ItemName = "Sword B-grade";
                price = 24;
                type = "Оружие";
                str = 24;
                dex = 0;
                break;
            case "6":
                ItemName = "Hammer B-grade";
                price = 24;
                type = "Оружие";
                str = 34;
                dex = -4;
                break;
            case "7":
                ItemName = "Dagger A-grade";
                price = 58;
                type = "Оружие";
                str = 24;
                dex = 8;
                break;
            case "8":
                ItemName = "Sword A-grade";
                price = 58;
                type = "Оружие";
                str = 32;
                dex = 0;
                break;
            case "9":
                ItemName = "Hammer A-grade";
                price = 58;
                type = "Оружие";
                str = 46;
                dex = -6;
                break;
            case "10":
                ItemName = "Leather C-grade";
                price = 8;
                type = "Броня";
                def = 6;
                dex = 2;
                break;
            case "11":
                ItemName = "Heavy C-grade";
                price = 8;
                type = "Броня";
                def = 12;
                dex = -2;
                break;
            case "12":
                ItemName = "Leather B-grade";
                price = 16;
                type = "Броня";
                def = 8;
                dex = 4;
                break;
            case "13":
                ItemName = "Heavy B-grade";
                price = 16;
                type = "Броня";
                def = 16;
                dex = -4;
                break;
            case "14":
                ItemName = "Leather A-grade";
                price = 32;
                type = "Броня";
                def = 10;
                dex = 6;
                break;
            case "15":
                ItemName = "Heavy A-grade";
                price = 32;
                type = "Броня";
                def = 20;
                dex = -6;
                break;
            case "16":
                ItemName = "Shield C-grade";
                price = 6;
                type = "Щит";
                def = 4;
                dex = -2;
                break;
            case "17":
                ItemName = "Shield B-grade";
                price = 10;
                type = "Щит";
                def = 6;
                dex = -3;
                break;
            case "18":
                ItemName = "Shield A-grade";
                price = 18;
                type = "Щит";
                def = 8;
                dex = -4;
                break;
        }
    }

    public Item() {
        ItemName = "NullItem";
        price = 0;
        type = "NullItem";
        str = 0;
        dex = 0;
    }

    @Override
    public String toString() {
        if(type.equals("Оружие")) {
            return type + "\t" + ItemName + "\t(STR: " + str + " | DEX: " + dex + ")";
        } else return type + "\t" + ItemName + "\t(P.Def: " + def + " | DEX: " + dex + ")";
    }

    public String getItemName() {
        return ItemName;
    }
    public String getType() {
        return type;
    }
    public double getStr() {
        return str;
    }
    public double getDex() {
        return dex;
    }
    public int getDef() {
        return def;
    }
    public double getPrice() { return price; }
}
