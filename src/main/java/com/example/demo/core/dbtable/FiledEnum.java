package com.example.demo.core.dbtable;

/**
 * Created by 张帆 on 2018/12/7.
 */
public enum FiledEnum {

    VHARCHAR("varchar",1),INT("int",2),DECIMAL("decimal",3),DATETIME("datetime",4);

    private String name;
    private int index;


    @Override
    public String toString() {
        return super.toString();
    }

    FiledEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    public static String getName(int index){
        for (FiledEnum f :FiledEnum.values()){
            if(f.getIndex()==index){
                return f.getName();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getName(1));
    }
}

