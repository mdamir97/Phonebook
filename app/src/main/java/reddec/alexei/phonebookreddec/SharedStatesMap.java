package reddec.alexei.phonebookreddec;

import java.util.LinkedHashMap;

public class SharedStatesMap {

    private static SharedStatesMap instance;
    private LinkedHashMap<String, Object> map;
    /*
    private LinkedHashMap<String, List<ProductStone>> mapProducts;
    private LinkedHashMap<String, ProductStone> mapProduct;
    //private LinkedHashMap<String, Bundle> mapBundle;
    private LinkedHashMap<String, OnViewListener> mapViewListener;
    //private HashMap<String, ViewPager> mapViewPager;
    //public static final String SESSION = "sessionId";
*/

    private SharedStatesMap() {
        map = new LinkedHashMap<String, Object>();
        //map = new WeakReference<HashMap<String, Object>>(HashMap);
        /*
        mapProducts = new LinkedHashMap<String, List<ProductStone>>();
        mapProduct = new LinkedHashMap<String, ProductStone>();
        //mapBundle = new LinkedHashMap<String, Bundle>();
        mapViewListener = new LinkedHashMap<String,OnViewListener>();
        //mapViewPager = new HashMap<String,ViewPager>();
        */
    }

    public static SharedStatesMap getInstance() {
        if (instance == null) {
            instance = new SharedStatesMap();
        }
        return instance;
    }

    public void setKey(String key, Object value) {
        map.put(key, value);
    }

    public String getKey(String key) {
        String name = "";
        if (map.get(key) != null) name = map.get(key).toString();
        return name;
    }
    /*
    public Object getKeyObject(String key) {
        Object name = null;
        if (map.get(key) != null) name = map.get(key);
        return name;
    }
    */
    /*
    public void setViewListener(String key, OnViewListener value) {
        mapViewListener.put(key, value);
    }
    public OnViewListener getKeyViewListener(String key) {
        OnViewListener name = null;
        if (mapViewListener.get(key) != null) name = mapViewListener.get(key);
        return name;
    }
*/

/*
    public void setKeyViewPager(String key, ViewPager value) {
        mapViewPager.put(key, value);
    }
    public ViewPager getKeyViewPager(String key) {
        ViewPager name = null;
        if (mapViewPager.get(key) != null) name = mapViewPager.get(key);
        return name;
    }
*/

/*
    public void setKeyProducts(String key, List<ProductStone> value) {
        mapProducts.put(key, value);
    }

    public List<ProductStone> getKeyProducts(String key) {
        List<ProductStone> name = new ArrayList<>();
        if (mapProducts.get(key) != null) name = mapProducts.get(key);
        return name;
    }


    public void setKeyProduct(String key, ProductStone value) {
        mapProduct.put(key, value);
    }

    public ProductStone getKeyProduct(String key) {
        ProductStone name = null;
        if (mapProduct.get(key) != null) name = mapProduct.get(key);
        return name;
    }
*/
/*
    public void setKeyBundle(String key, Bundle value) {
        mapBundle.put(key, value);
    }

    public Bundle getKeyBundle(String key) {
        Bundle name =null;
        if (mapBundle.get(key) != null) name = mapBundle.get(key);
        return name;
    }
*/
/*
    public Object getKey(String key) {
        return map.get(key);
    }
    */
}