package SFF_Reader.src;

import SFF_Reader.src.sff_handler.*;

public class App {

    public static void main(String[] args) throws Exception {
        String filename = "/Users/user/Documents/SFF/232AI.sff";
        sff_handler sff_handler = new sff_handler();

        try {
            sff_handler.open(filename);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}
