/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hwhm;

import java.util.Arrays;

/**
 *
 * @author lenfer
 */
public class MyHashMap<K,V> {
    private final int BUCKETS_COUNT = 16;
    private final float LOAD_FACTOR = 0.75f;
    private int size;
    private Object[] buckets;
    
    public MyHashMap() {
        buckets = new Object[BUCKETS_COUNT];
    }
    
    class MapEntry{
        private K key;
        private V value;
        private MapEntry next;
        private int keyHash;

        public MapEntry(K key, V value, MapEntry next) {
            this.key = key;
            this.keyHash = key.hashCode();
            this.value = value;
            this.next = next;
        }

        public int getKeyHash() {
            return keyHash;
        }

        public void setKeyHash(int keyHash) {
            this.keyHash = keyHash;
        }

        public MapEntry getNext() {
            return next;
        }

        public void setNext(MapEntry next) {
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
            this.keyHash = key.hashCode();
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
        
    }
    
    public V get(K key){
        int bucketIndex = Math.abs(key.hashCode()) % buckets.length;
        MapEntry curEntry = (MapEntry) buckets[bucketIndex];
        while(curEntry != null){
            if(curEntry.getKeyHash() == key.hashCode()){
                if(curEntry.getKey().equals(key)){
                    return curEntry.getValue();
                }
            }
            curEntry = curEntry.getNext();
        }
        return null;
    }

    public void remove(K key){
        int bucketIndex = Math.abs(key.hashCode()) % buckets.length;
        MapEntry curEntry = (MapEntry) buckets[bucketIndex];
        MapEntry prevEntry = null;
        while(curEntry != null){
            if(curEntry.getKeyHash() == key.hashCode()){
                if(curEntry.getKey().equals(key)){
                    if (prevEntry != null){
                        prevEntry.setNext(curEntry.getNext());
                    } else {
                        if (curEntry.getNext() != null){
                            buckets[bucketIndex] = curEntry.getNext();
                        } else {
                            buckets[bucketIndex] = null;
                        }
                    }
                    size --;
                    return;
                }
            }
            prevEntry = curEntry;
            curEntry = curEntry.getNext();
        }
    }
    
    private void resizeBuckets(){
        Object[] newBuckets = new Object[buckets.length * 2];
        
        for (Object bucket : buckets) {
            MapEntry curEntry = (MapEntry) bucket;
            while (curEntry != null) {
                int newBucketIndex = curEntry.getKeyHash() % newBuckets.length;
                newBuckets[newBucketIndex] = new MapEntry(curEntry.getKey(), curEntry.getValue(), (MapEntry)newBuckets[newBucketIndex]);
                curEntry = curEntry.getNext();
            }
            
        }
        
        buckets = newBuckets;
    }
    
    public void put(K key, V value){
        int bucketIndex = Math.abs(key.hashCode()) % buckets.length;
        MapEntry curEntry = (MapEntry) buckets[bucketIndex];
        while(curEntry != null){
            if(curEntry.getKeyHash() == key.hashCode()){
                if(curEntry.getKey().equals(key)){
                    curEntry.setValue(value);
                    return;
                }
            }
            curEntry = curEntry.getNext();
        }
        if (getSize() >= (int)(buckets.length * LOAD_FACTOR)){
            resizeBuckets();
        }
        buckets[bucketIndex] = new MapEntry(key, value, (MapEntry)buckets[bucketIndex]);
        size ++;
    }
    
    public int getSize() {
        return size;
    }
    
    @Override
    public String toString() {
        return "MyHashMap{" + "buckets=" + Arrays.toString(buckets) + '}';
    }
    
}
