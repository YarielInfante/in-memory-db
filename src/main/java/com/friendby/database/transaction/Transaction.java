package com.friendby.database.transaction;

import com.friendby.database.Executor;
import com.friendby.database.command.GetCommand;
import com.friendby.database.command.NumEqualToCommand;
import com.friendby.database.command.SetCommand;
import com.friendby.database.command.UnSetCommand;
import com.friendby.database.repository.StorageRepository;

import java.util.HashMap;
import java.util.Map;

public class Transaction {

    private static final Map<String, Integer> memory = new HashMap<>();


    public void run(Executor.Command command, String statement) {
        switch (command) {
            case SET: {
                new SetCommand(statement, memory).execute();
                break;
            }
            case GET: {
                Integer result = new GetCommand(statement, memory).execute();
                System.out.println(result);
                break;
            }
            case UNSET: {
                new UnSetCommand(statement, memory).execute();
                break;
            }
            case NUMEQUALTO: {
                Long execute = new NumEqualToCommand(statement, memory).execute();
                System.out.println(execute);
                break;
            }
        }
    }

    public void commit() {
        StorageRepository.merge(memory);
    }


}
