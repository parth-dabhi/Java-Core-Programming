package com.collectionFramework.list.arrayList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Come after, Multi-threading: https://www.scientecheasy.com/2020/09/synchronize-arraylist-in-java.html/

public class SynchronizedArrayList {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        List<Integer> synchronizedList = Collections.synchronizedList(list);
    }
}
