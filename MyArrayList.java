package com.lpc.Demo;

/**
 * 手写实现ArrayList
 * 增加remove方法
 */
public class MyArrayList<E> {
    private Object[] elementData;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity){
        if (capacity < 0){
            throw new RuntimeException("容器的容量不能为负数！");
        }else if (capacity == 0){
            elementData = new Object[DEFAULT_CAPACITY];
        }else {
            elementData = new Object[capacity];
        }
    }

    public void add(E element){
        //什么时候扩容?
        if (size == elementData.length){
            //扩容操作
            Object[] newArray = new Object[elementData.length + (elementData.length >> 1)];

            System.arraycopy(elementData,0,newArray,0,elementData.length);
            //新数组地址传给旧数组
            elementData = newArray;
        }
        //添加内容
        elementData[size++] = element;
    }

    public E get(int index){
        checkRange(index);
        return (E) elementData[index];
    }

    public void set(E element,int index){
        //索引合法判断
        checkRange(index);
        elementData[index] = element;
    }

    //数组索引越界判断
    public void checkRange(int index){
        //索引合法判断
        if (index<0 || index > size-1){
            //不合法
            throw new RuntimeException("index不合法");
        }
    }

    public void remove(E element){
        //element，将他和所有的元素挨个比较，获得第一个比较为true的返回
        for (int i=0;i<size;i++){
            if (element.equals(get(i))){
                //将该元素从此处移除
                remove(i);
            }
        }
    }

    public void remove(int index){
        int moved = size-index-1;
        if (moved > 0){
            System.arraycopy(elementData,index+1,elementData,index,moved);
        }
        elementData[--size] = null;

    }

    //容器大小
    public int size(){
        return size;
    }

    //isEmpty
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i=0;i<size;i++){
            sb.append(elementData[i]+",");
        }
        sb.setCharAt(sb.length()-1, ']');
        return sb.toString();
    }

    public static void main(String[] args) {
        MyArrayList s1 = new MyArrayList(20);
//        s1.add("A");
//        s1.add("B");
        for (int i=0;i<40;i++){
            s1.add("git"+i);
        }
        System.out.println(s1.toString());

        s1.set("DDD",10);

        System.out.println(s1.get(10));
        s1.remove(3);
        System.out.println(s1);
        s1.remove("git6");
        System.out.println(s1);
        System.out.println(s1.size());
        System.out.println(s1.isEmpty());
    }
}
