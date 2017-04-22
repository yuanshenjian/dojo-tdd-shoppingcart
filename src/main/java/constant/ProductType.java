package constant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum ProductType {
    ELECTRON("电子"), FOOD("食品"), COMMODITY("日用品"), LIQUOR("酒类");
    private String name;

    private static String[] electronConstant = {"ipad", "iphone", "显示器", "笔记本电脑", "键盘"};
    private static String[] foodConstant = {"面包", "饼干", "蛋糕", "牛肉", "鱼", "蔬菜"};
    private static String[] commodityConstant = {"餐巾纸", "收纳箱", "咖啡杯", "雨伞"};
    private static String[] liquorConstant = {"啤酒", "白酒", "伏特加"};

    static Map<Enum, String[]> types = new HashMap<>();

    static {
        types.put(ProductType.ELECTRON, electronConstant);
        types.put(ProductType.LIQUOR, liquorConstant);
        types.put(ProductType.COMMODITY, commodityConstant);
        types.put(ProductType.FOOD, foodConstant);
    }


    ProductType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Enum getProductType(String p) {
      Optional result = types.keySet().stream()
              .filter(key-> Arrays.stream(types.get(key))
                      .filter(i->i.equals(p)).findAny()
                      .isPresent()
                    )
              .findAny();
      return  result.isPresent()?(ProductType)result.get():null;
    }

    public static ProductType getProductTypeByName(String p) {
        Optional<ProductType> productType = Arrays.stream(ProductType.values()).
                filter(i -> i.getName().equals(p)).findFirst();
        return productType.isPresent() ? productType.get() : null;

    }

}
