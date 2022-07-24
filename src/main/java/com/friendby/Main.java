package com.friendby;

import com.friendby.database.Executor;

import java.util.regex.Pattern;

public class Main {


    public static void main(String... ags) {
        System.out.println("Welcome!");
        while (true) {
            String command = System.console().readLine().trim();

            if (command.equals(Executor.Command.END.name())) {
                break;
            }

            database(command);

        }
    }

    static void database(String command) {
        if (isSyntaxValid(command)) {

            String program = command.split(" ")[0];

            try {
                Executor.Command commandFound = Executor.Command.valueOf(program);
                Executor.run(commandFound, command);


            } catch (IllegalArgumentException e) {
                System.out.println("Command not available");
            }

        } else {
            System.out.println("syntax NOT valid");
        }
    }

    static boolean isSyntaxValid(String command) {
        Pattern p = Pattern.compile("[A-Za-z0-9]+\\s*[A-Za-z0-9]*\\s*[A-Za-z0-9]*");

        return p.matcher(command).matches();
    }


}
