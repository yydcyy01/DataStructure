package cn.yydcyy.hashtable;

import java.util.TreeMap;

/**
 * @author YYDCYY
 * @create 2019-09-05
 * HashTable 的增删改查操作
 *
 * 实现 resize 后, 此方案是 Space Time 间的一个 balance
 */
public class HashTable<K,V>{
    private static final int upperTol = 20;
    private static final int lowerTol = 2;
    private static final int iniCapacity = 7;


    private TreeMap<K, V>[] hashtable;
    private int size;
    private int M;

    public HashTable(int M){
        this.M = M;
        size = 0;
        hashtable = new TreeMap[M];
        for (int i = 0; i < M; i ++)
            hashtable[i] = new TreeMap<>();
    }
    public HashTable(){
        this(iniCapacity); // 默认选择 质数 97
    }

    //去符号 % M
    private int hash(K key){
        return key.hashCode() & 0x7fffffff % M;
    }

    public int getSize(){
        return size;
    }

    //在此优化 : resize()判断
    public void add(K key, V value){
        TreeMap<K, V> map = hashtable[hash(key)];
        //包含, 更新   也可以不处理, 解决注释掉不
        if (map.containsKey(key)){
            map.put(key, value);
        }
        else{
            map.put(key, value);
            size ++;

            //平均单个坑位 > upperTol 进行 扩容
            if (size  / M >= upperTol)
                resize(2 * M);
        }
    }

    // 此处优化, 判断 resize
    public  V remove(K key){
        V ret = null;
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key)){
            ret = map.remove(key);
            size --;

            // m / 2 >= 0; 边界处理. 但是同时也应 >= iniCapacity. 所以直接写成:
            if (size / M < lowerTol && M / 2 >= iniCapacity){
                resize(M / 2);
            }
        }
        return ret;
    }

    public void set(K key, V value){
        TreeMap<K, V> map = hashtable[hash(key)];
        if (!map.containsKey(key)){
            throw new IllegalArgumentException(key + "  doesn't exist!");
        }
        map.put(key, value);
    }

    public boolean contains(K key){
        return hashtable[hash(key)].containsKey(key);
    }

    public V get(K key){
        return hashtable[hash(key)].get(key);
    }

    //此次实现的简单hashtable没有自动转换成RBTree功能,可以自己完善.
    //优化版本可以实现:当hashTable长度到一定时,自动转换成RBTree.是不是很神奇.
    public void resize(int newM){
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i ++){
            newHashTable[i] = new TreeMap();
        }
        int oldM = M;
        for (int j = 0; j < oldM; j ++){
            TreeMap<K, V> map = hashtable[j];
            for (K key : map.keySet()){
                int hash = hash(key);
                V val = map.get(key);
                newHashTable[hash].put(key, val);
            }
        }
        this.M = newM;
        this.hashtable = newHashTable;
    }
}
