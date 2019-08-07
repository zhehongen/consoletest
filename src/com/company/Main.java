package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Person zambian = new Person();
        zambian.setName("zhangsan");
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(os);
            out.writeObject(zambian);
            byte[] zhansanByte = os.toByteArray();
            ByteArrayInputStream is = new ByteArrayInputStream(zhansanByte);
            ObjectInputStream in = new ObjectInputStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
