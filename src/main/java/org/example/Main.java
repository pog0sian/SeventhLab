package org.example;


public class Main {
    public static void main(String[] args) {

        Even even = new Even();
        Odd odd = new Odd();

        Thread oddThread = new Thread(odd);

        oddThread.start();
        even.start();



    }
}


