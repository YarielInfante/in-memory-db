package com.friendby.database;

import com.friendby.database.command.GetCommand;
import com.friendby.database.command.NumEqualToCommand;
import com.friendby.database.command.SetCommand;
import com.friendby.database.command.UnSetCommand;
import com.friendby.database.transaction.Transaction;

import java.util.Stack;

import static com.friendby.database.Executor.Command.BEGIN;

public class Executor {
    private static final Stack<Transaction> transactions = new Stack<>();

    public enum Command {
        GET,
        SET,
        UNSET,
        NUMEQUALTO,
        END,
        BEGIN,
        ROLLBACK,
        COMMIT
    }


    public static void run(Command command, String statement) {

        if (command.equals(BEGIN)) {
            transactions.add(new Transaction());

        } else if (transactions.isEmpty()) {
            switch (command) {
                case SET: {
                    new SetCommand(statement).execute();
                    break;
                }
                case GET: {
                    Integer result = new GetCommand(statement).execute();
                    System.out.println(result);
                    break;
                }
                case UNSET: {
                    new UnSetCommand(statement).execute();
                    break;
                }
                case NUMEQUALTO: {
                    Long execute = new NumEqualToCommand(statement).execute();
                    System.out.println(execute);
                }
            }
        } else {
            Transaction currentTransaction = transactions.peek();

            if (command.equals(Command.ROLLBACK)) {
                transactions.pop();
            } else if (command.equals(Command.COMMIT)) {
                currentTransaction.commit();
            } else {
                currentTransaction.run(command, statement);
            }

        }

    }
}
